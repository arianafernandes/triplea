![TripleAICon](resources/icon_menu.png)

## Software Maintainability
<p align="justify">Every project needs to be optimized and improved periodically. That's what software maintenance and evolution is about, the process of improving and optimizing a project that is already built or is being built. This is a big part of a project's development since it focuses on parts such as error handling, feature improvement, compatibility with a possible change in the user's requirements and possibly many more. Since this is an open project, the development team is the one that focuses on these topics but it is common to see a company hiring another team to fully test the code and help the developers see what they need to evolve or maintain. This can be a very expensive procedure, but since this is an open source project, there are no additional costs.</p>

The quality of TripleA was tested with the [Better Code Hub](https://bettercodehub.com/) tool. This tool has 10 metrics to calculate readability, maintainability and evolution capacity.

![Geral](resources/geral1.png)
<p align="justify">After the tool was run on a small part of our project, TripleA got very good result: 7 approval and only 3 disapprovals.</p>
![automatedTests](resources/automate\ testes.png)
<p align="justify"> As we can see in the image above, this part of the project failed in the automated tests. We do want to say that this result is almost certainly inaccurate. Due to its size, we were unable to test the whole project in the better code hub tool, therefore it was suggested to us that we only tested a small part of the project. We created this repository with only the code we thought would be necessary to implement the feature and only tested this small part.</p>

![architecture](resources/arch.png)
<p align="justify">As we can see in the image above, this small part of the project did not pass the architecture balance test as well. Again, we can't say for certain that the whole project passes this test, but with this result alone we cannot assume it doesn't. This is only one package of the project, therefore it's normal to fail this test. However, we do think how project is well divided into 'small' components, comparing to the size of the project.</p>

![smallInterfaces](resources/interfaces\ small.png)
<p align="justify">As we can see in the image above, this part of the project did not pass the Unit Interfaces test. To improve this, the team must reduce the number of arguments in each function build smaller but important functions that together do the same.</p>

## Evolution process
<p align="justify">Our goal as a feature was to add a "Custom URL" tab where users could input a link and the program would download that map, if the link was actually a map.</p>
<p align="justify">However we could not implement this feature. TripleA has a very complex, extensive and bad commented code and for all those reasons we could not understand where the desired functionalities were needed to be implemented.</p>

## External Links
* HomePage: http://triplea-game.github.io/
* Community Website: http://www.tripleawarclub.org/
* Community Maps Repository: http://github.com/triplea-maps
* Source Forge (legacy): https://sourceforge.net/projects/triplea/
* Bug reports, feature requests: http://github.com/triplea-game/triplea/issues/new

## Group Information

* [Diogo Luís Cerqueira Carneiro da Silva](https://github.com/pingudiogo) (up201405742@fe.up.pt) - <br> 25%
* [Luís Octávio Pais Ferreira Araújo Correia Soares](https://github.com/LuiSoares) (up201406356@fe.up.pt) - <br> 25%
* [Ariana Fernandes](https://github.com/arianafernandes) (up201404789@fe.up.pt) - <br> 25%
* [Tiago Bernardes Almeida](https://github.com/tiagobalm) (up201305665@fe.up.pt) - <br> 25%
