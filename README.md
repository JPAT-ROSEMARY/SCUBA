# SCUBA
Simple CUstomisable Bytecode Analysis tool

Description
=

A research project aims at learning static and dynamic bytecode analysis techniques using [ASM] (http://asm.ow2.org/) and [BCEL] (https://commons.apache.org/proper/commons-bcel/) frameworks and applying a couple of software engineering principles 
including, MVC architecture paradigm, IoC concept and a couple of Design Patterns. I also practiced TDD with Mockito, JMock & Hamcrest. It is an application on how to make an extensible, reusable & configurable software system.
Moreover, the project was used for a couple of experiments to evaluate and compare both ASM & BCEL frameworks.
The results of the experiments are accompanied under `./org.jpat.scuba.ui/experiments/`.

Why Utilizing Two Bytecode Engineering Frameworks
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

To What Extent
--
Basically, the project started early 2012 for research & learning objectives.


How Running SCUBA is Useful
--
Unfortuantelly, the model resulted from running SCUBA on a bytecode archive is not useful! And the analyses target trivial problems.  
As mentioned, this project is made for different purposes. However, SCUBA demonstrates how well the application of all concepts, techniques and methodologies pursued.

How SCUBA can be Useful
--
Introduction on bytecode engineering and a bunch of practical examples on software engineering and object oriented design and programming principles. 


How to Get Involved
--
There are a couple of Test Case classes in each project. These test cases will give you a complete idea on the structure and logical connection of the project's modules.
I will be so glad to discuss your suggestions and ideas.



How to Run
=

* Execute the command Gradle clean compileJava build run in `./master` project

* In eclipse: consume `./org.jpat.scuba/eclipse.launch/Application.launch` with "Run Configurations..."

Prerequisites
--
SCUBA assumes the machine is configured as follows:
* [1] Java 8
* [2] Gradle 2.4
* [3] Development IDE like eclipse - Kepler with Java 8 support or Luna

Entry Point
--
* Project driver:  `./org.jpat.scuba.ui`
* Main class: `org.jpat.scuba.ui.app.Application`; with the relative path as `./org.jpat.scuba.ui/src/main/java/org/jpat/scuba/ui/app/Application.java`
* Test cases will summarise the entry and internal structure

SCUBA input
-
* [1] Configurations file; check for an example `./org.jpat.scuba.ui/benchmark.examples/user.benchmark.properties`. 
	Also, `./org.jpat.scuba.ui/src/main/resources/org/jpat/scuba/ui/benchmark/benchmark.properties`
	If you run it with Gradle, then it will consume the second properties file automatically
	Those two properties files explain to you the rest of the story.

* [2] Input bytecode archive (only two formats accepted Zip and Jar).
	The prepared properties files above already pick some examples located in `/org.jpat.scuba.ui/benchmark.examples/java` and `/org.jpat.scuba.ui/benchmark.examples/scala`.

Issues
--
* (1) BCEL Framework library used by SCUBA is release version 5.2 which has not been updated yet to consider Java 8.
* (2) Enhancement is required for the Concrete Exceptions in the common project
* (3) Enhancement to the model in general, especially the Stats sub-model part
* (4) A couple of FindBugs rule violations need to be addressed
* (5) More PMD rules need to be covered - provide gradle PMD plugin configurations in `./master/build.gradle`