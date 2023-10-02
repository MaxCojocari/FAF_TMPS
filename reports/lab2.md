# Topic: Creational Design Patterns

## Author: Cojocari-Goncear Maxim

## Theory

Creational design patterns are a category of design patterns in software engineering that focus on the process of object creation. They provide solutions for creating objects in a way that promotes flexibility, efficiency, and maintainability within a software system. These patterns abstract the instantiation process, hiding the details of how objects are created and initialized.

Some well-known creational design patterns include:

- Singleton
- Factory Method
- Abstract Factory
- Builder
- Prototype

Each of these patterns offers a unique solution to specific object creation problems, and they can be applied depending on the requirements and constraints of a given software project.

## Objectives

- Learn and understand Creational Design Patterns;

- Define domain classes/entities and select instantiation methods;

- Apply creational design patterns in a sample project for object creation.

## Implmentation

In this laboratory work were implemented 4 Creational Design Patterns:

1. Singleton
2. Builder
3. Factory Method
4. Prototype

### Singleton

Implementing a Singleton pattern in a blockchain class can be a useful way to ensure that there is only one instance of the blockchain throughout the application. This is typically done to maintain consistency and avoid inconsistencies that might arise from multiple blockchain instances.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/d0ca400d-c1ba-426d-b615-40a4b5fcdf7a)


### Builder

The Builder pattern is a creational design pattern that allows to create complex objects step by step by providing a clear separation between the construction process and the representation of the final object. This pattern is particularly useful when there is need to create objects with many optional parameters or configurations. In the context of creating various blockchain accounts, the Builder pattern can help create these accounts with different configurations in a more readable and maintainable way.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/127a5cbd-03f5-4cab-bb84-8543b4eb8f92)


### Factory Method

The Factory Method pattern is a creational design pattern that provides an interface for creating objects but allows subclasses to alter the type of objects that will be created. In the context of creating transactions, this pattern allows to create different transaction types while abstracting the creation logic.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/aba56c98-7172-4ff2-98ac-8eb718375d16)


### Prototype

The Prototype Design Pattern is a creational design pattern that focuses on creating objects by copying an existing object, known as the prototype, instead of creating objects from scratch. This pattern is particularly useful when object creation is more expensive or complex, and you want to create new objects with similar attributes and behaviors as an existing instance.

![image](https://github.com/MaxCojocari/FAF_TMPS/assets/92053176/e20c3ac7-43df-415a-927c-f312393a94ff)


## Conclusions

The laboratory work on creational design patterns has provided valuable insights and practical experience in tackling object creation complexities in software development. Creational design patterns are a fundamental aspect of design pattern categories, and they address various challenges related to object instantiation, initialization, and configuration.

Throughout this laboratory work, I explored several creational design patterns, including Singleton, Factory Method, Abstract Factory, Builder, and Prototype. Each of these patterns serves a distinct purpose and offers its own advantages in managing object creation and ensuring object consistency.

To summarize, creational design patterns are indispensable tools in software design and development. They help us manage object creation intricacies, enhance code reusability, and improve maintainability. By mastering these patterns, I am better equipped to design robust and efficient software systems while adhering to best practices in object creation. The knowledge gained from this laboratory work serves as a valuable foundation for applying creational design patterns in real-world software projects.
