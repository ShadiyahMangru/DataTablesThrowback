# DataTablesThrowback
## This application generates Tabular Team Data via Java SE 8 Concepts.

______________________________________________________________________________________________________________________________________

### This application leverages 5 built-in *Functional Interfaces* (interfaces with a single abstract method): (i) Function, (ii) Consumer, (iii) Predicate, (iv) Supplier, (v) Comparator.

- (i) The Skater class leverages the **BiFunction Functional Interface** to accept a Skater’s goals and shots values and, following some calculations, return this Skater’s shooting percentage.  **Motivation for use:** the Function/BiFunction Functional Interface turns one/two parameter(s) into a value of a potentially different type and returns this value.


- (ii) The Skater class leverages the **BiConsumer Functional Interface** to accept a shooting percentage value and a Skater object, then set the Skater object’s shooting percentage field.  **Motivation for use:** Consumers accept/manipulate one/two parameter(s), but do not return anything.


- (iii) The HockeyPlayer class leverages the **Predicate Functional Interface** to determine if a HockeyPlayer object is a goalie.  The boolean value returned dictates whether a narrowing cast would be to a Skater object, or to a Goalie object.  **Motivation for use:** Predicates return a boolean value and may be used to test a condition (often used when filtering or matching).


- (iv) The HockeyPlayer class leverages the **Supplier Functional Interface** to provide a value – “WSH” – that may be used to set the team field of the HockeyPlayer object.  **Motivation for use:** the Supplier Functional Interface may be used to generate or supply values without taking any input.


- (v) The Skater class leverages the **Comparator Functional Interface** to sort Skater objects in descending order by goals scored; when players tie for goals, they are sorted in ascending order by last name.  **Motivation for use:** Comparator is used to specify that (i) you want to use a different order than the object itself provides, (ii) you want to sort an object that did not implement Comparable, or (iii) you want to sort objects in different ways at different times.

______________________________________________________________________________________________________________________________________
### This application leverages 1 *Design Pattern* (an established general solution to a commonly occurring software development problem): (i) Singleton.

- (i) The Roster class applies a **Singleton Design Pattern**, which centralizes the roster data.  The singleton pattern is a creational pattern focused on creating only one instance of the Roster object in memory within the application, sharable by all classes and threads within the application.  All constructors in a singleton class are marked private, which ensures no other class is capable of instantiating another version of the Roster.  **Motivation for use:** Singletons are used in situations where we need access to a single set of data throughout an application.  Singletons may also improve performance by loading reusable data that would otherwise be time consuming to store and reload each time it is needed.  Singletons may also be used to coordinate access to shared resources, such as coordinating write access to a file.

______________________________________________________________________________________________________________________________________
### This application leverages a compile-time safety strategy: (i) Generics

- (i) The Roster class uses **Generics** in the roster field declaration:   `private ArrayList<HockeyPlayer> roster;`
**Motivation for use:** Java SE 8 allows programmers to add *different types of objects* to an ArrayList.  Thanks to Generics, the benefits outweigh the pitfalls of this feature.  Specifically, this application’s code acts upon HockeyPlayer objects and their direct descendants (Skater objects and Goalie objects).  It is prudent to use Generics in the roster ArrayList declaration to ensure all roster elements be of (or a direct subclass of) type HockeyPlayer to avoid runtime errors.  Generics in object declaration alerts programmer to type mismatch(es) *at compile time* – provides compile-time type safety.  In other words, generics in object declaration prevents runtime mishaps.
