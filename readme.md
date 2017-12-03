SCUBA
=====

Simple CUstomisable Bytecode Analysis tool

Description
-----------

A research project aims at learning static and dynamic bytecode analysis techniques using [ASM](http://asm.ow2.org) and [BCEL](https://commons.apache.org/proper/commons-bcel/) frameworks.

Motivations and Objectives
--------------------------

To evaluate bytecode frameworks, apply a couple of software engineering principles including, MVC architecture paradigm, IoC concept and a couple of Design Patterns. To practice Test-Driven Development and Unit Testing with Mockito, JMock and Hamcrest. 
To get rid of being afraid of understanding JVM, Java language specifications and bytecode artifacts and also to make an application on how to produce an extensible, reusable and configurable software system.

Experiments
-----------

Evaluating ASM and BCEL frameworks by SCUBA was a joyful part as I conducted a couple of experiments to evaluate and relatively compare them.

Benchmark settings and the relative performance of each framework are published [here](./org.jpat.scuba.ui/experiments/Framework.Evaluation-ASM.vs.BCEL_in.Static.&.Dynamic.analysis.settings.Mar2014.xls.pdf).

Programming against both frameworks' APIs showed me how easy and less error-prone is to utilise ASM than BCEL.
Also relatively comparing the results and having all SCUBA's analysers produce the same output is a nice thing to make sure that both ASM and BCEL are being correctly utilised. 
What is left is a higher level of assertion to check that my analyses do absolutely produce the correct result. Sometimes I used open source tools which analyse the bytecode instructions and produce different stats on Java programs to compare my results. I still find some differences between results. I got to dig more in bytecode engineering area.      

### SCUBA Analysers
Two static and two dynamic analysers, makes a total of four analysers. Each analyser is generic. Let's say I want to use Static ASM Analyser, then this analyser can be extended to check certain facts about subject source code. For example, cyclic dependencies among classes. And for instrumentation one can define certain instructions to be instrumented using the generic SCUBA Dynamic ASM Analyser. The same can be done for the other two - SCUBA BCEL Analysers.  

Why Utilizing Two Bytecode Engineering Frameworks
-------------------------------------------------

The goal was in the first place to compare those two frameworks to each other. I learned by experimenting with SCUBA, analysing and instrumenting many Java open source projects, that ASM is about 3 times faster than BCEL in average. 
Moreover, the performance of ASM in instrumentation processes is also more than 3 times better than BCEL.
> Conclusion on Benchmarking and Experiments results is published for both static and dynamic settings [here](./org.jpat.scuba.ui/experiments/Framework.Evaluation-ASM.vs.BCEL_in.static.analysis.setting.doc.pdf).

I wanted to use only one business logic and program against both frameworks' APIs reusing the same core engine that I built as a platform.
I got more greedy and extended the capability to analyse Scala bytecode files. 
I consider my knowledge and experience is still moderate as I did not do much with bytecode engineering, as said it is only a simple, but customisable and extensible tool.
  
Future Work
-----------

Take the potentials of ASM Framework to its maximum to check the inter-communication patterns of Object Oriented software systems against certain criterion.
Another feature could be added to SCUBA is the ability to check software code structure against certain Architecture Paradigm like MVC. This feature is not simple and could be divided to a couple of related features, for example, locating the code sites where rules of referenced architecture are violated. During my master's thesis research I noticed that not all Object Oriented inter-communication patterns have the same weights or dependency value, and therefore every dependency should be studied very well before marking it a violation if that appears to be against certain architecture rule. 

To What Extent
--------------

Basically, the project started in April 2012 for research & learning objectives. I am publishing it to support my profile.

How Running SCUBA is Useful
---------------------------

Unfortunately, the model resulted from running SCUBA on a bytecode archive is not useful! And the analyses target trivial problems.  
As mentioned, this project is made for different purposes. However, SCUBA demonstrates how well the application of all concepts, techniques and methodologies pursued.

How SCUBA can be Useful
-----------------------

Introduction on bytecode engineering and a bunch of practical examples on software engineering and object oriented design and programming principles. 

How to Get Involved
-------------------

There are a couple of Test Case classes in each project. These test cases will give you a complete idea on the structure and logical connection of the project's modules.
I will be so glad to discuss your suggestions and ideas.

How to Run
----------

### Gradle way

```
    Using `gradle run` in project folder `./master`
```

### In eclipse

```
  Using `./org.jpat.scuba/eclipse.launch/Application.launch` with "Run Configurations..."
```

### Prerequisites

```
SCUBA assumes the machine is configured as follows:
 [1] Java 8
 [2] Gradle 2.4
 [3] Development IDE like eclipse - Kepler with Java 8 support or Luna
````

### Program Input

* [1] Configurations file; check for an example `./org.jpat.scuba.ui/benchmark.examples/user.benchmark.properties`. 
	Also, `./org.jpat.scuba.ui/src/main/resources/org/jpat/scuba/ui/benchmark/benchmark.properties`
	If you run it with Gradle, then it will consume the second properties file automatically.
	Those two properties files explain to you the rest of the story.

* [2] Input bytecode archive (only two formats accepted Zip and Jar).
	The prepared properties files above already pick some examples located in `/org.jpat.scuba.ui/benchmark.examples/java` and `/org.jpat.scuba.ui/benchmark.examples/scala`.


Development Entry Point
----------------

* Project driver:  `./org.jpat.scuba.ui`
* Main class: `org.jpat.scuba.ui.app.Application`; with the relative path as `./org.jpat.scuba.ui/src/main/java/org/jpat/scuba/ui/app/Application.java`
* Test cases will summarise the entry and internal structure

Issues
------

* (1) BCEL Framework library used by SCUBA is release version 5.2 which has not been updated yet to consider Java 8.
* (2) Enhancement is required for the Concrete Exceptions in the common project
* (3) Enhancement to the model in general, especially the Stats sub-model part
* (4) A couple of FindBugs rule violations need to be addressed
* (5) More PMD rules need to be covered - provide gradle PMD plugin configurations in `./master/build.gradle`
