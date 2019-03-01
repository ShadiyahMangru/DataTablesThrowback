# DataTablesThrowback
Tabular Team Data via Java SE 8 Concepts.

This code leverages 5 built-in Functional Interfaces: 

(i) the Function/BiFunction Functional Interface turns one/two parameter(s) into a value of a potentially different type and returns this value.

(ii) the Consumer/BiConsumer Functional Interface accepts/manipulates one/two parameter(s), but does not return anything.

(iii) the Predicate/BiPredicate Functional Interface may be used to test a condition (often used when filtering or matching); Predicate returns a boolean value.

(iv) the Supplier Functional Interface may be used to generate or supply values without taking any input.

(v) Comparator – used to specify that you want to use a different order than the object itself provides.  When you want to sort an object that did not implement Comparable, or you want to sort objects in different ways at different times.  Comparator is a functional interface because it has a single abstract method.


The Roster class applies a Singleton Design Pattern, which centralizes the roster data.  The singleton pattern is a creational pattern focused on creating only one instance of the Roster object in memory within the application, sharable by all classes and threads within the application.  All constructors in a singleton class are marked private, which ensures no other class is capable of instantiating another version of the Roster.  

Motivation for use of the Singleton Design Pattern:
Singletons are used in situations where we need access to a single set of data throughout an application.  Singletons may also improve performance by loading reusable data that would otherwise be time consuming to store and reload each time it is needed.  Singletons may also be used to coordinate access to shared resources, such as coordinating write access to a file.
