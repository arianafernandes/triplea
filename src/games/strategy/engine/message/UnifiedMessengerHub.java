package games.strategy.engine.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import games.strategy.engine.message.unifiedmessenger.HasEndPointImplementor;
import games.strategy.engine.message.unifiedmessenger.NoLongerHasEndPointImplementor;
import games.strategy.engine.message.unifiedmessenger.UnifiedMessenger;
import games.strategy.net.GUID;
import games.strategy.net.IConnectionChangeListener;
import games.strategy.net.IMessageListener;
import games.strategy.net.IMessenger;
import games.strategy.net.INode;
import games.strategy.net.IServerMessenger;
import games.strategy.util.ThreadUtil;

public class UnifiedMessengerHub implements IMessageListener, IConnectionChangeListener {
  private final static Logger s_logger = Logger.getLogger(UnifiedMessengerHub.class.getName());
  private final UnifiedMessenger m_localUnified;
  // the messenger we are based on
  private final IMessenger m_messenger;
  // maps end points to a list of nodes with implementors
  private final Map<String, Collection<INode>> m_endPoints = new HashMap<>();
  // changes to the list of endpoints, or reads to it, should be made
  // only while holding this lock
  private final Object m_endPointMutex = new Object();
  // the invocations that are currently in progress
  private final Map<GUID, InvocationInProgress> m_invocations = new ConcurrentHashMap<>();

  public UnifiedMessengerHub(final IMessenger messenger, final UnifiedMessenger localUnified) {
    m_messenger = messenger;
    m_localUnified = localUnified;
    m_messenger.addMessageListener(this);
    ((IServerMessenger) m_messenger).addConnectionChangeListener(this);
  }

  private void send(final Serializable msg, final INode to) {
    if (m_messenger.getLocalNode().equals(to)) {
      m_localUnified.messageReceived(msg, m_messenger.getLocalNode());
    } else {
      m_messenger.send(msg, to);
    }
  }

  @Override
  public void messageReceived(final Serializable msg, final INode from) {
    if (msg instanceof HasEndPointImplementor) {
      synchronized (m_endPointMutex) {
        final HasEndPointImplementor hasEndPoint = (HasEndPointImplementor) msg;
        Collection<INode> nodes = m_endPoints.get(hasEndPoint.endPointName);
        if (nodes == null) {
          nodes = new ArrayList<>();
          m_endPoints.put(hasEndPoint.endPointName, nodes);
        }
        if (nodes.contains(from)) {
          throw new IllegalStateException(
              "Already contained, new" + from + " existing, " + nodes + " name " + hasEndPoint.endPointName);
        }
        nodes.add(from);
      }
    } else if (msg instanceof NoLongerHasEndPointImplementor) {
      synchronized (m_endPointMutex) {
        final NoLongerHasEndPointImplementor hasEndPoint = (NoLongerHasEndPointImplementor) msg;
        final Collection<INode> nodes = m_endPoints.get(hasEndPoint.endPointName);
        if (nodes != null) {
          if (!nodes.remove(from)) {
            throw new IllegalStateException("Not removed!");
          }
          if (nodes.isEmpty()) {
            m_endPoints.remove(hasEndPoint.endPointName);
          }
        }
      }
    } else if (msg instanceof HubInvoke) {
      final HubInvoke invoke = (HubInvoke) msg;
      final Collection<INode> endPointCols = new ArrayList<>();
      synchronized (m_endPointMutex) {
        if (m_endPoints.containsKey(invoke.call.getRemoteName())) {
          endPointCols.addAll(m_endPoints.get(invoke.call.getRemoteName()));
        }
      }
      // the node will already have routed messages to local invokers
      endPointCols.remove(from);
      if (s_logger.isLoggable(Level.FINEST)) {
        s_logger.log(Level.FINEST, "Forwarding invocation:" + msg + " to:" + endPointCols);
      }
      if (endPointCols.isEmpty()) {
        if (invoke.needReturnValues) {
          final RemoteMethodCallResults results =
              new RemoteMethodCallResults(new RemoteNotFoundException("Not found:" + invoke.call.getRemoteName()));
          send(new SpokeInvocationResults(results, invoke.methodCallID), from);
        } else {
          // no end points, this is ok, we
          // we are a channel with no implementors
        }
      } else {
        invoke(invoke, endPointCols, from);
      }
    } else if (msg instanceof HubInvocationResults) {
      final HubInvocationResults results = (HubInvocationResults) msg;
      results(results, from);
    }
  }

  private void results(final HubInvocationResults results, final INode from) {
    final GUID methodID = results.methodCallID;
    final InvocationInProgress invocationInProgress = m_invocations.get(methodID);
    final boolean done = invocationInProgress.process(results, from);
    if (done) {
      m_invocations.remove(methodID);
      final HubInvoke hubInvoke = invocationInProgress.getMethodCall();
      if (s_logger.isLoggable(Level.FINER)) {
        s_logger.log(Level.FINER, "Method returned:" + hubInvoke.call.getMethodName() + " for remote name:"
            + hubInvoke.call.getRemoteName() + " with id:" + hubInvoke.methodCallID);
      }
      if (invocationInProgress.shouldSendResults()) {
        sendResultsToCaller(methodID, invocationInProgress);
      }
    }
  }

  private void sendResultsToCaller(final GUID methodID, final InvocationInProgress invocationInProgress) {
    final RemoteMethodCallResults result = invocationInProgress.getResults();
    final INode caller = invocationInProgress.getCaller();
    final SpokeInvocationResults spokeResults = new SpokeInvocationResults(result, methodID);
    send(spokeResults, caller);
  }

  private void invoke(final HubInvoke hubInvoke, final Collection<INode> remote, final INode from) {
    if (hubInvoke.needReturnValues) {
      if (remote.size() != 1) {
        throw new IllegalStateException("Too many nodes:" + remote + " for remote name " + hubInvoke.call);
      }
      final InvocationInProgress invocationInProgress =
          new InvocationInProgress(remote.iterator().next(), hubInvoke, from);
      m_invocations.put(hubInvoke.methodCallID, invocationInProgress);
      if (s_logger.isLoggable(Level.FINER)) {
        s_logger.log(Level.FINER, "Waiting for method:" + hubInvoke.call.getMethodName() + " for remote name:"
            + hubInvoke.call.getRemoteName() + " with id:" + hubInvoke.methodCallID);
      }
    }
    // invoke remotely
    final SpokeInvoke invoke =
        new SpokeInvoke(hubInvoke.methodCallID, hubInvoke.needReturnValues, hubInvoke.call, from);
    for (final INode node : remote) {
      send(invoke, node);
    }
  }

  /**
   * Wait for the messenger to know about the given endpoint.
   *
   * @param endPointName
   * @param timeout
   */
  public void waitForNodesToImplement(final String endPointName, long timeoutMS) {
    // dont use Long.MAX_VALUE since that will overflow
    if (timeoutMS <= 0) {
      timeoutMS = Integer.MAX_VALUE;
    }
    final long endTime = timeoutMS + System.currentTimeMillis();
    while (System.currentTimeMillis() < endTime && !hasImplementors(endPointName)) {
      ThreadUtil.sleep(50);
    }
  }

  public boolean hasImplementors(final String endPointName) {
    synchronized (m_endPointMutex) {
      return m_endPoints.containsKey(endPointName) && !m_endPoints.get(endPointName).isEmpty();
    }
  }

  @Override
  public void connectionAdded(final INode to) {}

  @Override
  public void connectionRemoved(final INode to) {
    // we lost a connection to a node
    // any pending results should return
    synchronized (m_endPointMutex) {
      for (final Collection<INode> nodes : m_endPoints.values()) {
        nodes.remove(to);
      }
    }
    final Iterator<InvocationInProgress> waitingIterator = m_invocations.values().iterator();
    while (waitingIterator.hasNext()) {
      final InvocationInProgress invocation = waitingIterator.next();
      if (invocation.isWaitingOn(to)) {
        final RemoteMethodCallResults results =
            new RemoteMethodCallResults(new ConnectionLostException("Connection to " + to.getName() + " lost"));
        final HubInvocationResults hubResults = new HubInvocationResults(results, invocation.getMethodCallID());
        results(hubResults, to);
      }
    }
  }
}
