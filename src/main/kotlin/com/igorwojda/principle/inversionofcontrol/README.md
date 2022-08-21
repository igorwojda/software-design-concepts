# Inversion Of Control Principle (IoC)

Design principle that transfers (inverts) different kinds of controls from one object to another.

The control refer to any additional responsibilities a class has, other than its main responsibility. This includes
control over the flow of an application, and control over the flow of an object creation.

## Metaphor

Bob drives a car to the work place. This means that Bob controls the car. The Inversion Of Control Principle
suggests to invert the control. Instead of driving the car himself, Bob hires a driver that will drive the car.
Control is inverted from Bob the driver.

## Pros

- Allows to switch between different implementations of a particular class at runtime
- Increases the modularity of the program
- Manages an object’s life-cycle (some objects can be singletons, while we can create others per
  request)

##

We can achieve Inversion of Control through various mechanisms such as: Strategy design pattern, Service Locator
pattern, , and Dependency Injection (DI).

We're going to look at DI next.

The IoC in object-oriented programming can be applied in many ways. Some of which are:

- [Dependency injection](../../pattern/dependencyinjection/README.md) pattern - object creation control is transferred
  from class to dependency injector
- [Strategy](../../pattern/strategy/README.md) pattern - class behaviour control is transferred from class to the
  strategy implementation
- [Service locator](../../pattern/servicelocator/README.md) pattern - service retrieval control is transferred
  from class to the service locator
- [Factory](../../pattern/factory/README.md) pattern - object creation control is transferred
  from class to a factory
- Event based UI - application flow is transferred from code to the UI (flow is event driven) 
