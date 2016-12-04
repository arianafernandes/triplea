![TripleAICon](resources/icon_menu.png)

# Verification and Validation

## Introduction

<p align="justify">  Nowadays is very difficult to control the quality of products because there are too many standards and reviews. So this is the major reason why software tests are fundamental to every product. With them we can detect errors and failures in order to improve our work. </p>


## Software Testability 
<p align="justify">Software testability is a property that indicates the level of testability that a particular software supports. The higher the test capacity, the more easily system failures are detected.
</p>

### Controllability
<p align="justify">Phase where is possible to control the state of the component to be tested (CUT) in accordance with the test.
POR COMPLETAR
</p>

### Observability
<p align="justify">Phase in which is possible to analyze the intermediate and final results of the tests.
POR COMPLETAR
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

<p align="justify"> The statistics result of representative test cases to estimate a value of a quality metric of software.
They determine the efficiency and the reliability of the system. </p>

![Tests Run](resources/TestsRun.png)

<p align="justify"> As we can see, all the tests run were either successfully executed or skipped. This doesn't prove the 
code quality is good, but it does prove that whatever the developers were testing, it executed as expected. Since this is 
a game platform with games included, it's extremely difficult to test every single scenario, due to the complexity of the 
project, so we can't say the project is prepared for every situation, however it can be said it's prepared for a good variety 
of them.</p>

### Code Coverage

![Code Coverage](resources/CodeCoverage.png)

<p align="justify"> As we can see in the image above, this project does not have a good code coverage. It is important to 
notice that, being this project a game platform, a good part of the code is GUI development and that part of the code 
cannot be tested with JUnit tests. There are a few packages that have good code coverage, with values between 71% and 96%. 
Those packages are the ones related to the games logic, therefore they can be properly tested.</p>

More information about the code coverage [here](https://htmlpreview.github.io/?https://raw.githubusercontent.com/arianafernandes/triplea/master/index.html).

### System Testing

<p align="justify"> As we said in the second report, this project started with a man's simple idea, so there is no evidence 
that there were any requirements at all. Till this day, the team does not have a proper development process and the developers 
specify the requirements themselves. This way, this system cannot be properly tested. </p> 

### Acceptance Testing

<p align="justify"> As we said before, in this project, the client and the developers are the same. Therefore, there is 
no need for acceptance tests in this project since it is built by the clients, in some way. </p>

## Bug

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

