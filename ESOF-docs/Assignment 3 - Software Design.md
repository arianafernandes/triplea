![TripleAICon](resources/icon_menu.png)

## Introduction

<p align="justify"> Software Architecture is the fundamental organization of a system and it includes the most important decisions about it. 
This model is based ono four components: [Logical View](##logical-view), represented by the packages diagram, Implementation View, represented 
by components diagram, Process View, represented by the activity diagram and Deployment View, represented by the deployment 
diagram. With these diagrams it's possible to analyse the evolution and the development of the project. </p>

<p align="justify"> In this case, after a deep analysis of the available code and it's structure we can conclude that there is no definided 
architecture. </p>

## Logical View

![Logical View](resources/Logicalviewf.png)

## Development View

## Deployment View

AS the name suggests, the deployment view describes the environment into wich the system will be deployed.
This view focus on the environment of the system, including the hardware and technical environment required as well as the mapping of the software elements to the runtime that will execute them. To represent this view we used a [deployment diagram](https://en.wikipedia.org/wiki/Deployment_diagram) .

![Deployment View](resources/DeploymentView.png)

We only considered the existence of 3 nodes, the gamers PC, the gamer server and the tripleA server though the gamer server is only considered when the gamer use is computer to alocate the game.

## Process View