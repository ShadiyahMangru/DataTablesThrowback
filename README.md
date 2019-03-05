# DataTablesThrowback
## This application generates Tabular Team Data via Java SE 8 Concepts.

______________________________________________________________________________________________________________________________________

### This application leverages 5 built-in *Functional Interfaces* (interfaces with a single abstract method): (i) Function, (ii) Consumer, (iii) Predicate, (iv) Supplier, (v) Comparator.

- (i) **(REVISE LATER)** The Skater class leverages the **BiFunction Functional Interface** to accept a Skater’s goals and shots values and, following some calculations, return this Skater’s shooting percentage.  **Motivation for use:** the Function/BiFunction Functional Interface turns one/two parameter(s) into a value of a potentially different type and returns this value.


- (ii) **(REVISE LATER)** The Skater class leverages the **BiConsumer Functional Interface** to accept a shooting percentage value and a Skater object, then set the Skater object’s shooting percentage field.  **Motivation for use:** Consumers accept/manipulate one/two parameter(s), but do not return anything.


- (iii) The HockeyPlayer class leverages the **Predicate Functional Interface** to determine if a HockeyPlayer object is a goalie.  The boolean value returned dictates whether a narrowing cast would be to a Skater object, or to a Goalie object.  **Motivation for use:** Predicates return a boolean value and may be used to test a condition (often used when filtering or matching).


- (iv) The HockeyPlayer class leverages the **Supplier Functional Interface** to provide a value – “WSH” – that may be used to set the team field of the HockeyPlayer object.  **Motivation for use:** the Supplier Functional Interface may be used to generate or supply values without taking any input.


- (v) The Skater class leverages the **Comparator Functional Interface** to sort Skater objects in descending order by goals scored; when players tie for goals, they are sorted in ascending order by last name.  **Motivation for use:** Comparator is used to specify that (i) you want to use a different order than the object itself provides, (ii) you want to sort an object that did not implement Comparable, or (iii) you want to sort objects in different ways at different times.

______________________________________________________________________________________________________________________________________
### This application leverages a performance-optimizing strategy: (i) Singleton Design Pattern
- (i) The Roster class applies a **Singleton Design Pattern** to effectively/optimally manage access to a single set of data throughout an application.  

```
//PRIVATE constructor
//all constructors in a singleton class are marked private, which ensures that no other class is capable
//of instantiating another version of the class
private Roster(){
	setSc();
	setRoster();
}
	
//singletons in Java are created as private static variables within the class, often with the name instance
private static final Roster instance = new Roster();
	
/**
* This method, which is part of the Singleton Design Pattern, returns a reference to the Roster instance.
* @return Roster
*/
//singletons are accessed via a single public static method, often named getInstance(), which returns the reference to the singleton object
public static Roster getInstance(){
	return instance;	
}
```
**Motivation for use:** The Singleton Design Pattern enables creation of only one instance of the Roster object in memory within the application.  This single instance is shared among all classes (and threads) within the application.  Since all constructors in a singleton class are marked private, no other class can instantiate another version of the Roster.  Singletons may improve application performance by loading reusable data that would otherwise be time consuming to store and reload each time needed.  Coordination of access to shared resources, such as coordinating write access to a file, may also be achieved through Singletons.

______________________________________________________________________________________________________________________________________
### This application leverages compile-time safety strategies: (i) Generics, (ii) @Override annotation

- (i) The Roster class uses **Generics** in the roster field declaration:   `private ArrayList<HockeyPlayer> roster;`
**Motivation for use:** Java SE 8 allows programmers to add *different types of objects* to an ArrayList.  Thanks to Generics, the benefits outweigh the pitfalls of this feature.  Specifically, this application’s code acts upon HockeyPlayer objects and their direct descendants (Skater objects and Goalie objects).  It is prudent to use Generics in the roster ArrayList declaration to ensure all roster elements be of (or be a direct subclass of) type HockeyPlayer to avoid runtime errors.  Generics in an object declaration alerts a programmer to type mismatch(es) *at compile time* – provides compile-time type safety.  In other words, generics in object declaration prevents runtime mishaps.

- (ii) The Skater class uses an **annotated override of the toString() method** to facilitate compile-time-safe output that adheres to the data table format. 

```
@Override
public String toString(){
	return String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", getTeam(), getLastName(), getJersey(), goals, shootingPercent);
}
```

**Motivation for use:** The @Override annotation signals to the compiler that the programmer intends for the annotated method to override a superclass method or implement an interface method.  If the contract for method overriding is not fulfilled, a compiler error prevents the application from running.  Since Java automatically calls toString() when printing out an object, ensuring that the toString() override was successful guarantees that any call to print a Skater object in this application will produce output that is compatible with the data table format.

______________________________________________________________________________________________________________________________________
### This application leverages 3 *code-reusability and code-flexibility* strategies: (i) Generics, (ii) Method Overriding, (iii) Polymorphism

- (i) The roster field in the Roster class uses **Generics** to accept HockeyPlayer objects, a more general type of the Skater objects and Goalie objects currently found in the roster ArrayList.  **Motivation for use:**  Using the superclass or interface type of roster ArrayList objects allows us to have one roster comprised of different player types.


- (ii) **(REVISE THIS LATER)** The direct descendants of the HockeyPlayer class (Skater and Goalie) override the printHPSP lambda defined in the HockeyPlayer parent class. 
```
public Consumer<HockeyPlayer> printHPSP = hp -> {
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s |", hp.getTeam(), hp.getLastName(), hp.getJersey(), hp.getPosition()));
	};
```

```
public Consumer<HockeyPlayer> printHPSP = hp -> {
		Skater s = HPTeamAndSP.apply(hp);
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", s.getTeam(), s.getLastName(), s.getJersey(), s.getGoals() , s.getShootingPercent()));
	};
```
 
``` 
public Consumer<HockeyPlayer> printHPSP = hp -> {
		Goalie g = HPTeamAndSP.apply(hp);
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-15s | %-7s | %-15s |", g.getTeam(), g.getLastName(), g.getJersey(), g.getShotsAgainst(), g.getSaves() , g.getSavePercent()));
	};
```

Through **Method Overriding,** output is formatted based on the class/object calling the method/lambda.  **Motivation for use:** A Java object may be accessed using (i) a reference with the same type as the object, (ii) a reference that is a superclass of the object, or (iii) a reference that defines an interface the object implements, either directly or through a superclass.  The type of the object determines which properties exist within the object in memory.  The type of the reference to the object determines which methods and variables are accessible to the Java program.  

The Skater class calls the printHPSP lambda defined in the Skater class.  
```
.forEach(sk -> {Skater s = (Skater)sk; s.printHPSP.accept(s);}); //calls Skater classes' printHPSP
```

The Goalie class calls the printHPSP lambda defined in the Goalie class.
```
.forEach(gl -> {Goalie g = (Goalie)gl; g.printHPSP.accept(gl);}); //calls Goalie class' printHPSP
```


- (iii) **(REVISE LATER)** The different definitions of the sP lambda in the Skater and Goalie classes demonstrate the principle of **Polymorphism** by facilitating a player stat calculation depending on context.    

``` 
//this Lambda accepts a player's goals and shots as parameters, and returns the player's shooting percentage
	public BiFunction<Integer, Integer, Float> sP = (g, s) -> {
		if(s == 0){
			return (float)0;
		}
		return ((float)g / (float)s)*100;		
	};
  ```

```
//this Lambda accepts a goalie's saves and shotsAg as parameters, and returns the goalie's save percentage
	public BiFunction<Integer, Integer, Float> sP = (s, sA) -> {
		if(sA == 0){
			return (float)0;
		}
		return ((float)s / (float)sA);		
	};
```

When a Skater object calls the sP lambda, a shooting percentage is calculated.  When a Goalie object calls the sP lambda, a save percentage is calculated.  **Motivation for use:** through the Polymorphism achieved through class inheritance and methods/lambdas with the same name but different definitions depending on the class to which they belong, we may perform a single action – e.g., calculate – but go about this in a certain relevant way depending on context / object type.

______________________________________________________________________________________________________________________________________
### This application leverages strategies to avoid program failure at the time the program is run: (i) Exception Handling via try-catch, (ii) Exception Handling via if-statements
- (i) The Roster class leverages **Exception Handling via a try-catch block** to avoid program failure should any expected colons not exist in the stats-input line of each player entry of the input file.

```
/**
* This lambda accepts a HockeyPlayer object and, after reading-in goals, assists, 
* and shots values, initializes and returns a new Skater object. 
* @param HockeyPlayer object
* @return Skater object
*/
public Function<HockeyPlayer, Skater> setSkater = hp ->{
	try{ 
		String[] skaterStats = vals.get().split(":"); //goals at sS[0], assists at sS[1], and shots at sS[2]
		Skater s = new Skater(hp, Integer.parseInt(skaterStats[0]), Integer.parseInt(skaterStats[1]), Integer.parseInt(skaterStats[2]));
		return s;
	}
    	catch(ArrayIndexOutOfBoundsException aiobe){ //exception handling of ArrayIndexOutOfBoundsException when colon is missing from input file
    		System.out.println("Exception: " + aiobe);	
    		Skater s = new Skater(hp, -1, -1, -1);
    		return s;
    	}
};
```

**Motivation for use:**  In a Java try statement, the code stops running at the line that throws an exception and execution immediately diverts to the catch block.  Specifically, there is potential for program failure at this line: 
```
String[] skaterStats = vals.get().split(":"); //goals at sS[0], assists at sS[1], and shots at sS[2]
```
This line *will* throw an exception if any of the expected colon-stats-separators do not exist on the stats-input line of each player entry of the input file.  In this event, program flow will immediately transfer to the catch block designed to handle an ArrayIndexOutOfBoundsException.  A new Skater object will still be initialized.  The returned new Skater object will contain inaccurate goals, assists, and shots values of -1, respectively, but the entire application will NOT fail due to one or more missing colon(s) in the input file.

- (ii) **(REVISE LATER)** The Bifunction Lambda sP in the Skater class uses an **if-statement implicit exception handling strategy** to avoid an ArithmeticException at the time the program is run.  

```
//this Lambda accepts a player's goals and shots as parameters, and returns the player's shooting percentage
	public BiFunction<Integer, Integer, Float> sP = (g, s) -> {
		if(s == 0){
			return (float)0;
		}
		return ((float)g / (float)s)*100;		
	};
```

**Motivation for use:** The JVM throws an ArithmeticException when code attempts to divide by zero.  The sP lambda, which accepts a Skater's current goals and shots values as parameters, divides goals by shots while calculating a Skater's shooting percentage.  Since it is possible that the Skater's shots value is zero, an initial if-statement 'short-circuits' the method before a problematic division-by-zero is attempted. 
