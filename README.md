# design-pattern-strategy
Show cases usage of strategy design pattern in Spring framework.

The application processes events with different actions. On each action some specific business 
code will be executed. The easiest way to implement this is by using a conditional statement; 
an if-else or a switch-case.

This approach has a couple of disadvantages. The most important one is that it is not cohesive. 
On the other hand it does too much, it's not focused. It knows how to transfer, how to deposit 
and how to withdrawal. So if there is a bug in some part of a system, let's say transfer,
we would change the same class that contains the code for other unrelated operations and 
there are chances that we would introduce new bugs in other parts of the system.

Using strategy design pattern makes the application more coherent and it would be easier to 
maintain and test. Each implementation resides in a separate class. If there's a bug in one 
of the implementations there's no need to change other implementations, so no change in other 
classes means we would not introduce new and unwanted bugs.


Implementation details:

The entry point of the application is Bootstrap interface. Since Java 8 interfaces could have static 
methods so I have decided to use an interface for that instead of a class.

Spring ApplicationContext is initialized there, I have chosen to use AnnotationConfigApplicationContext
since I no longer use xml for configuring beans. It takes a package as an argument, this is the package
that Spring will use to look for annotated classes like @Service, @Repository, @Controller, @Component
or the newer one @RestController. Spring uses java reflections to look for annotated classes, which is 
slow. I usually pass a couple of more specific packages in order to help spring to find them quickly. 
In Spring 5 there's an option to provide the annotated classes to the context, by using that feature
starting up of the application would be faster.

When the Spring context is fully initialized a reference to ActionStrategyContext is acquired from 
context and three different events will be processed.

We have ActionStrategy interface which has been implemented by Transfer, Deposit and Withdrawal classes.
ActionStrategyContext is the main class where we setup everything. ApplicationContextAware is implemented
to have reference to Spring context, On post construct Action enum will be iterated and for every element
the corresponding class will be pushed to declared and instantiated EnumMap.

When processing an event, based on the event's action, the corresponding implementation will be 
invoked to process the event.

To create an Event, I have used Builder design pattern. The Event itself is fully immutable. Once created 
there's no possibility to change its values.

 