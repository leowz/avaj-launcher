# Avaj-Launcher Java

This is the first Java project in the cursus of school 42. Here is my understand and what I have learned in the process of doing this project.

### Tasks to do:

1. Implement classes and architecture from the UML class diagram provided.
2. Implement the design pattern: Singleton, Factory, Observer pattern.
3. Execute the program should behave like the log provided.

---

[Documentation](https://docs.oracle.com/javase/tutorial/java/](https://docs.oracle.com/javase/tutorial/java/)

## javac

Java Application Compiler.  JDK 

example: `javac classname.java`

## java

Java Application Launcher, Java run time (JRE)

example: `java classname`

## Package **Naming Conventions**

Package names are written in all lower case to avoid conflict with the names of classes or interfaces.

Companies use their reversed Internet domain name to begin their package names—for example, `com.example.mypackage`for a package named `mypackage`created by a programmer at `example.com`

whenever java encounters `import abc.xyz.ClassName;` it tries to resolve `abc/xyz/ClassName`
 with respect to the `classpath` or current working directory.

**Default package**: Classes which does not have package declaration.  Classes that belong to no package will belong to the default package.

### Modifier Order

1. `public`
2. `protected`
3. `private`
4. `abstract`
5. `default`
6. `static`
7. `sealed`
8. `non-sealed`
9. `final`
10. `transient`
11. `volatile`
12. `synchronized`
13. `native`
14. `strictfp`

---

# UML (Unified Modelling language)

[UML Tutorial](https://www.javatpoint.com/uml-building-blocks)

[chart ](https://www.lucidchart.com/pages/uml-class-diagram)

UML is composed of three main building blocks. Things, relationships, and diagrams. Building blocks generate one complete UML model diagram by rotation around several different blocks. It plays an essential role in developing UML diagrams.

- Things
- Relationships
- Diagrams

## Things

Anything that is a real world entity or object is termed as Things.  The object in the diagram.

It can be divided into different categories:

- Structural things: Nouns that depicts the static behaviour of a model is a structural thing. Include: class, object, interface , node. etc.
- Behavioural things: Verbs that encompass the dynamic parts of a model.
- Grouping Things: package is the only grouping thing.
- Annotation Things: Comment(Note) in the UML.

## RelationShips:

- Dependency: Source element is dependent on the target element.   - - - - >
- Association: It tells how many elements are actually taking part in forming that reationship.
- Generalisation: Used to describe the concept of inheritance.
- Realisation: Used in the implementation of an interface.

## Diagrams:

Diagrams are graphical implementation of the models that incorporate symbols and text. UML diagrams are classified into three categories:

- Structural Diagram:  Class diagram, Object diagram, Package diagram, Component diagram, Deployment diagram
- Behavioural Diagram: Activity diagram, State machine diagram
- Interaction Diagram: Timing diagram, sequence diagram

## Class Diagram:

- Upper Section: name of the class
- Middle Section: Attributes of the class. public (+), private(-), protected(#), package(~).
- Lower Section: contain methods of the class.

### Relationships:

- Dependency:  Between two or more classes where a change in one class cause changes in another class. For example: Student_Name is dependent on the Student_Id

- Generalisation: A relationship between a parent class and a child class. For example, the Current Account, Saving Account, and Credit Account are the generalised from Bank Account

- Association: Describes a static or physical connection between two or more objects. It depicts how many objects are there in the relationship. For example, a department is associated with the college.

- Multiplicity: Defines a specific range of allowable instances of attributes. In case if a range is not specified, one is considered as a default multiplicity. For example, multiple patients are admitted to one hospital.

- Aggregation: An aggregation is a subset of association, which represents has a relationship. It is more specific than association. It defines a part-whole or part-of relationship. For example, the company encompasses a number of employees and even if one employee resigns, the company still exists.

- Composition: Composition is a subset of aggregation. IT portrays the dependency between the parent and its child, means if one part is deleted, the other part also gets discarded. it represents a whole-part relationship. A contact book consists of multiple contacts, and if you delete the contact book, all the contacts will be lost.

- Generalisation: For Inheritance

- Realisation: for interface realisation

### Abstract Classes

The notation of the abstract class is similar to the of class; the only difference is that the name of the class is written in italics. 

---

# Design Pattern

[Book to read by GOF](https://en.wikipedia.org/wiki/Design_Patterns)

[Tutorial](https://www.javatpoint.com/design-patterns-in-java)

Design pattern are well-proved solution for solving the specific problem. (good code writing method)

### Core Java Design Patterns:

- Creational Design Pattern: factory pattern, singleton pattern, prototype pattern, builder pattern
- Structural Design Pattern: adapter pattern, bridge pattern, composite pattern, decorator pattern, facade pattern, flyweight pattern, proxy pattern.
- Behavioural Design Pattern: chain of responsibility pattern, command pattern, interpreter pattern, iterator pattern, mediator pattern, memento pattern, observer pattern, state pattern, strategy pattern, template pattern, visitor pattern

### Singleton pattern

Define a class that has only one instance and provides a global point of access to it

Singleton pattern is mostly used in multi-threaded and database applications. It is used in logging, caching, thread pools, configuration settings etc.

**How to create singleton pattern?**

Static member, private constructor, static factor method.

```java
package refactoring_guru.singleton.example.non_thread_safe;

public final class Singleton {
    private static Singleton instance;
    public String value;

    private Singleton(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
```

### Factory pattern

A factory pattern says that just define an interface or abstract class for creating an object but let the subclasses decide which class to instantiate. Subclasses are responsible to create the instance of the class. In short, a class is used to create other class instances.

### Observer Pattern

Define a one to one dependency so that so that when one object changes state, all its dependents are notified and updated automatically. It describes the coupling between the objects and the observer. It provides the support for broadcast-type communication.
