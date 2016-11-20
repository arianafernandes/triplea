![TripleAICon](resources/icon_menu.png)

## Introduction

<p align="justify"> Software Architecture is the fundamental organization of a system and it includes the most important decisions about it. 
This model is based ono four components: Logical View, represented by the packages diagram, Implementation View, represented 
by components diagram, Process View, represented by the activity diagram and Deployment View, represented by the deployment 
diagram. With these diagrams it's possible to analyse the evolution and the development of the project. </p>

<p align="justify"> In this case, after a deep analysis of the available code and it's structure we can conclude that there is no definided 
architecture. </p>

## Logical View

![Logical View](resources/Logicalviewf.png)

## Development View

AS the name suggests, the deployment view describes the environment into wich the system will be deployed.
This view focuses on the environment of the system, including the hardware and technical environment required as well as the mapping of the software elements to the runtime that will execute them. To represent this view we used a [deployment diagram](http://www.conceptdraw.com/examples/deployment-diagram) .

![Deployment View](resources/DeploymentView.png)

We only considered the existence of 3 nodes and only pointed the type of physical connection:
Gamer Server ->In case the user wants to alocate the game in his computer he becames the server. 
Gamer PC -> The user that will only connect to the TripleA server or GsamerServer.
TripleA Server -> The tripleA Server where the game is stored and other info related touser account, backup games, etc.


## Process View
