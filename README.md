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
**Motivation for use:** Java SE 8 allows programmers to add *different types of objects* to an ArrayList.  Thanks to Generics, the benefits outweigh the pitfalls of this feature.  Specifically, this application’s code acts upon HockeyPlayer objects and their direct descendants (Skater objects and Goalie objects).  It is prudent to use Generics in the roster ArrayList declaration to ensure all roster elements be of (or be a direct subclass of) type HockeyPlayer to avoid runtime errors.  Generics in object declaration alerts programmer to type mismatch(es) *at compile time* – provides compile-time type safety.  In other words, generics in object declaration prevents runtime mishaps.

______________________________________________________________________________________________________________________________________
### This application leverages 3 *code-reusability and code-flexibility* strategies: (i) Generics, (ii) Method Overriding, (iii) Polymorphism

- (i) The roster field in the Roster class uses **Generics** to accept HockeyPlayer objects, a more general type of the Skater objects and Goalie objects currently found in the roster ArrayList.  **Motivation for use:**  Using the superclass or interface type of roster ArrayList objects allows us to have one roster comprised of different player types.


- (ii) The direct descendants of the HockeyPlayer class (Skater and Goalie) override the printHPSP lambda defined in the HockeyPlayer parent class. 
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


- (iii) The different definitions of the sP lambda in the Skater and Goalie classes demonstrate the principle of **Polymorphism** by facilitating a player stat calculation depending on context.    

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
### This application leverages a strategy to avoid program failure at the time the program is run: (i) Exception Handling

- (i) The Roster class leverages **Exception Handling,** via a try-catch block, to avoid program failure should the expected colon not exist in the stats-input line of each player entry of the input file.  

```
//this lambda accepts a HockeyPlayer and, after reading-in goals and shots values, initializes and returns a new skater object 
	public Function<HockeyPlayer, Skater> setSkater = hp ->{
		try{ //exception handling of ArrayIndexOutOfBoundsException when colon is missing from input file
			setGoalsShotsArray(vals.get().split(":")); //goals at gs[0] and shots at gs[1]
			Skater s = new Skater(hp, Integer.parseInt(goalsShotsArray[0]), Integer.parseInt(goalsShotsArray[1]));
			return s;
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);	
    			Skater s = new Skater(hp, -1, -1);
    			return s;
    		}
	};
```
**Motivation for use:**  In a Java try statement, *the code stops running at the line that throws an exception and execution goes to the catch block* (which, in this case, uses the generalized *Exception e* should there be more mishaps in this try statement than those that can be caught by an ArrayIndexOutOfBoundsException).  

Specifically, the programmer notices the potential for program failure at this line:
`
setGoalsShotsArray(vals.get().split(":")); //goals at gs[0] and shots at gs[1]
`
Since this line *will* throw an exception if the input file does not contain a colon on the stats-input line of each player entry, in this event program flow will immediately transfer to the catch block.  A new Skater object will be initialized (and later returned) with inaccurate goals and shots values of -1, respectively, but the entire application will not fail due to the missing colon.
