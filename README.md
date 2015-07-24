# SCUBA
Simple CUstomisable Bytecode Analysis tool

Description
=

A research project aims at learning static and dynamic bytecode analysis techniques using ASM & BCEL frameworks and applying a couple of software engineering principles 
including, MVC architecture paradigm, IoC concept and a couple of Design Patterns plus to TDD with Mockito, JMock & Hamcrest. It is an application on how to make an extensible, reusable & configurable software system.
MoreoverThe project is used for a couple of experiments to evaluate and compare both ASM & BCEL frameworks. 
The results of the experiments are accompanied under `./org.jpat.scuba.ui/experiments/`.

Why utilizing two Bytecode Engineering Frameworks
--

The goal was in the first place to compare those two frameworks to each other. I learned by experimenting with SCUBA, analysing and instrumenting many Java open source projects, that ASM is about 3 times faster than BCEL in average. 
Moreover, the performance of ASM in instrumentation processes is also more than 3 times better than BCEL.
I wanted to use only one business logic and program against both frameworks' APIs reusing the same core engine that I built as a platform.
I got more greedy and extended the capability to analyse Scala bytecode files. 
I consider my knowledge and experience is still moderate as I did not do much with bytecode engineering, as said it is only a simple, but customisable and extensible tool.
  
Future Work
--
Take the potentials of ASM Framework to its maximum to check the intercommunications patterns of Object Oriented software systems against certain criterion.
Another feature is to check the source code structure against certain Architecture paradigm like MVC. 

To what extent
--
Basically, the project started early 2012 for research & learning objectives.


How Running SCUBA is Useful
--
Unfortuantelly, the model resulted from running SCUBA on a bytecode archive is not useful! And the analyses target trivial problems.  
As mentioned, this project is made for different purposes. However, SCUBA demonstrates how well the application of all concepts, techniques and methodologies pursued.

How SCUBA can be Useful
--
Introduction on bytecode engineering and a bunch of practical examples on software engineering and object oriented design and programming. 


How to get involved
--
There are a couple of Test Case classes in each project. These test cases will give you a complete idea on the structure and logical connection of the project's modules.
I will be so glad to discuss your suggestions and ideas.



How To run
--

* Execute the command Gradle clean compileJava build run in `./master` project.

* Eclipse, consume `./org.jpat.scuba/eclipse.launch/Application.launch` with "Run Configurations...".

Issues
--
(1) BCEL Framework used is 5.2 which has not been updated yet to consider Java 8.
(2) Enhancement is required for the Concrete Exceptions in the common project
(3) FinBugs tool integration is not yet provided with gradle build

