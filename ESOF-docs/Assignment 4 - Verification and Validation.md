![TripleAICon](resources/icon_menu.png)

# Verification and Validation

## Introduction

<p align="justify"> Nowadays, leaks in the control of the products quality can have huge unexpected results and, without very strict and systematic testing, it's very easy to make them.
In average, even for an experienced programmer, it's likely to inject around 100 defects/KLOC (Klines of code)*,  so the software testing is fundamental to the development of every product in order to detect and correct its errors and failures. 
The goal of this report is to analyses the verification and validation(V&V) process followed in the development of TripleA.
 We will approach this by describing some new concepts that concern to the software testability, in relation to the actual TripleA system and then present some relevant test statistic . 
Finally, we will select a bug and try to correct it.
</p>

![EsquemaV.V](resources/V&V.png)

<p align="justify">*(Source: Inspiring, enabling and driving the Evolution of Quality at Adobe leveraging
the TSP, Jim Sartain, Senior Director, Quality, TSP Symposium 2009)
</p> 

## Software Testability 
<p align="justify">Software testability is a property that indicates the level of testability that a particular software supports. The higher the test capacity, the easier system failures are detected.
Regarding testability, we ought to assume that TripleA isn’t actually good. Due to its open source and modulus independence situation it's very hard to control the quality of the test, besides, like in other games, it relies on human interaction so the product user interface makes it a challenging area to increase testability. 
It is also important to remind that ,notwithstanding all the testing we can do to TripleA it doesn’t ensure that the software is free of errors.
</p>

### Controllability
<p align="justify">Phase where it is possible to control the state of the component to be tested (CUT), in accordance with the test. It denotes the ability to move a system around in its entire configuration space using only certain admissible manipulation.
It depends a lot on the components that we want to test and how we want to test it. In the TripleA example, we may have more difficult components to control either because they depend on external user files(it's needed testing in all the cases not only the main), or because they depend on more extensive and complex projects from different languages. Therefore, we have to test every combination.
</p>

### Observability
<p align="justify">Phase in which is possible to analyze the intermediate and final results of the tests.</p>

<p align="justify">The results of the tests can be easily obtained and reviewed by running them on Eclipse, Netbeans or Intellij IDEA.
Taking TripleA as an example again, there are a bunch of tests that are mandatory for the project to run after the pull 
request, to ensure this pull gets accepted. This seems very natural, but making sure every step is taken in the 
preparation of the project doesn't have a negative impact in the well functioning of it. Not only this, but it may 
help spot future mistakes.
</p>

### Isolability

<p align="justify">Isolability represents the amount of each component (CUT) that can be tested isolated. 
In TripleA the components communicate with each other, so is  very difficult to isolate one component from the rest. The component under test (CUT) can only be tested in isolation, partially.
</p>


### Separation of Concerns

<p align="justify"> Separation of concerns is the state in which the component to be tested has a unique responsibility which is well defined.

The TripleA has a well-defined concept division. The project is divided in 2 main packages. This division is very important because is the simplest way of avoiding repetition of code and creation of code that is of no use. This code structure method avoids introducing more bugs into the software.
</p>

### Understandability

<p align="justify"> Phase in which the component to be tested is well documented and explainable by itself.
Projects such as TripleA have a large number of contributors so it is necessary for all contributors to follow a certain behavior in order to make the project more consistent and accurate.
</p>

### Heterogeneity
<p align="justify"> Phase where it requires the use of several test methods and tools in parallel.
</p>

## Test Statistics

<p align="justify">The statistics result of representative test cases to estimate a value of a quality metric of software.
They determine the efficiency and the reliability of the system.</p>

![Tests Run](resources/TestsRun.png)

<p align="justify">As we can see, all the tests run were either successfully executed or skipped. This doesn't prove the 
code quality is good, but it does prove that whatever the developers were testing, it executed as expected. Since this is 
a game platform with games included, it's extremely difficult to test every single scenario, due to the complexity of the 
project, so we can't say the project is prepared for every situation, however it can be said it's prepared for a good variety 
of them.</p>

### Code Coverage

![Code Coverage](resources/CodeCoverage.png)

<p align="justify">As we can see in the image above, this project does not have a good code coverage. It is important to 
notice that, being this project a game platform, a good part of the code is GUI development and that part of the code 
cannot be tested with JUnit tests. There are a few packages that have good code coverage, with values between 71% and 96%. 
Those packages are the ones related to the games logic, therefore they can be properly tested.</p>

More information about the code coverage [here](https://htmlpreview.github.io/?https://raw.githubusercontent.com/arianafernandes/triplea/master/index.html).

### System Testing

<p align="justify">As we said in the second report, this project started with a man's simple idea, so there is no evidence 
that there were any requirements at all. Till this day, the team does not have a proper development process and the developers 
specify the requirements themselves. This way, this system cannot be properly tested.</p>

### Acceptance Testing

<p align="justify">As we said before, in this project, the client and the developers are the same. Therefore, there is 
no need for acceptance tests in this project since it is built by the clients, in some way.</p>

### Regression Testing

<p align="justify">For this type of testing, the unit tests are important again. Every time a bug fix or new feature is proposed, usually 
through pull requests, a member of the development team is responsible for testing it and verify if the new contribution 
does not compromise the features already implemented. This can be done by running unit tests.</p>

## Bug

Bugs isn’t something that this project lacks of and we found several interesting ones in the [issues page](http://github.com/triplea-game/triplea/issues/). 
<p align="justify">However, we were incapable of fixing any of them. Our main problem was the complexity of 
the project. Most of the issues we found involved the logic of one of the games. Since we didn’t make these mechanics 
ourselves we have almost no knowledge to help us fix problems related to this matter. There were also problems related 
to this project's GUI. However, these problems cannot be fixed since the graphic’s API this project uses doesn’t allow us
to do so. It has been stated by one of the main developers that solving these kinds of problems would only bring us 
another problem somewhere else. As a solution for this matter, the developers decided to migrate the game’s UI components 
to JavaFx and they are under the impression that the migration will solve the problems. Therefore, any alteration to the 
GUI would be obsolete.</p>

## External Links
* HomePage: http://triplea-game.github.io/
* Community Website: http://www.tripleawarclub.org/
* Community Maps Repository: http://github.com/triplea-maps
* Source Forge (legacy): https://sourceforge.net/projects/triplea/
* Bug reports, feature requests: http://github.com/triplea-game/triplea/issues/new

## Group Information

* [Diogo Luís Cerqueira Carneiro da Silva](https://github.com/pingudiogo) (up201405742@fe.up.pt) - 25%<br>
* [Luís Octávio Pais Ferreira Araújo Correia Soares](https://github.com/LuiSoares) (up201406356@fe.up.pt) - 25%<br>
* [Ariana Fernandes](https://github.com/arianafernandes) (up201404789@fe.up.pt) - 25%<br>
* [Tiago Bernardes Almeida](https://github.com/tiagobalm) (up201305665@fe.up.pt) - 25%<br>

