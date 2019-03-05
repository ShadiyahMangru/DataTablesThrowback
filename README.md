# DataTablesThrowback
## This application generates Tabular Team Data via Java SE 8 Concepts.

________________________________________________________________________________________________________________________________________
### This application leverages 5 built-in Functional Interfaces (interfaces with a single abstract method) to streamline code production: (i) Function, (ii) Consumer, (iii) Predicate, (iv) Supplier, (v) Comparator.
- (i) The Skater class leverages the **Function Functional Interface** to accept a HockeyPlayer object, set its team field, and cast it to a Skater object that it then returns.  
```
//this Lambda sets team of HockeyPlayer object,
//casts HockeyPlayer object to Skater object
//returns Skater object
private Function<HockeyPlayer, Skater> HPTeamAndSP = hp ->{
	hp.setTeam(hp.assignTeam.get()); //calls Supplier
	Skater s = (Skater)hp; //narrowing cast of HockeyPlayer object to a Skater object
	return s;
};
```
**Motivation for use:** Functions turn one parameter into a value of a potentially different type and return this value.

- (ii) The Skater class leverages the **Consumer Functional Interface** to accept a HockeyPlayer object, set its team field, cast it to a Skater object, and print this Skater object to the console in the format defined by toString() of the Skater class.  
```
public Consumer<HockeyPlayer> printHPSP = hp -> {
	Skater s = HPTeamAndSP.apply(hp);
	System.out.println(s);
};
```
**Motivation for use:** Consumers accept/manipulate one parameter, but return nothing.
- (iii) The HockeyPlayer class leverages the **Predicate Functional Interface** to determine if a HockeyPlayer object is a goalie. The boolean value returned dictates whether a narrowing cast would be to a Skater object, or to a Goalie object.
```
/**
* This Lambda accepts a HockeyPlayer parameter and returns true if this HockeyPlayer is NOT a Goalie.
* This lambda is static and therefore may be called without being tethered to a specific instance of the HockeyPlayer class.
* @return T/F response to 'this HockeyPlayer object is NOT a goalie' (in the form of a boolean)
*/
public static Predicate<HockeyPlayer> filterOutGoalies = hp -> {
	if(!hp.getPosition().equals("Goalie")){
		return true;	
	}
	return false;	
};
```
**Motivation for use:** Predicates return a boolean value and may be used to test a condition (often used when filtering or matching).
- (iv) The HockeyPlayer class leverages the **Supplier Functional Interface** to provide a value – “WSH” – that may be used to set the team field of the HockeyPlayer object. 
```
/**
* This lambda takes no parameters and returns the String "WSH".
* @return the team WSH (in the form of a String)
*/
public Supplier<String> assignTeam = () -> {
	return "WSH";
};
```
**Motivation for use:** Suppliers may be used to generate or supply values without taking any input.
- (v) The Skater class leverages the **Comparator Functional Interface** to sort Skater objects in descending order by goals scored; when players tie for goals, they are sorted in ascending order by last name. 
```
//this method sorts HockeyPlayer objects that may be narrowed to Skater objects in 
//(i) descending order by goals scored, and (ii) ascending order by last name of Skater objects with equal goals.
public static int compareByGoalsThenName(HockeyPlayer h1, HockeyPlayer h2){
	try{
		Skater s1 = (Skater)h1;
		Skater s2 = (Skater)h2;
		if(s2.getGoals() - s1.getGoals() != 0){
			return s2.getGoals() - s1.getGoals();	
		}
		return s1.getLastName().compareTo(s2.getLastName());	
	}
	catch(ClassCastException cce){
		System.out.println("Exception: " + cce);	
	}
	return 0;
}	
```
```
//Comparator that sorts HockeyPlayers (who are Not Goalies) by goals then lastname, written with a Java SE 8 method reference
static Comparator<HockeyPlayer> byGoalsThenName = Skater :: compareByGoalsThenName;
```
**Motivation for use:** Comparator is used to specify that (i) you want to use a different order than the object itself provides, (ii) you want to sort an object that did not implement Comparable, or (iii) you want to sort objects in different ways at different times.
________________________________________________________________________________________________________________________________________
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

________________________________________________________________________________________________________________________________________
### This application leverages a code-flexibility strategy: (i) Polymorphism
- (i) The different definitions of toString() in the HockeyPlayer, Skater, and Goalie classes demonstrate the principle of **Polymorphism** by printing stats output that is formatted depending on object type.  **Motivation for use:** The HockeyPlayer class, the Skater class, and the Goalie class each provide overrides of the toString() method originally defined in the Object class (from which all classes in Java inherit).  In this application, Skater (pictured in 1st code block below) and Goalie (pictured in 2nd code block below) stats are output in data table format.  
```
public Consumer<HockeyPlayer> printHPSP = hp -> {
	Skater s = HPTeamAndSP.apply(hp);
	System.out.println(s);
};
```
```
public Consumer<HockeyPlayer> printHPSP = hp -> {
	Goalie g = HPTeamAndSP.apply(hp);
	System.out.println(g);
};
```
When the System.out.println() parameter is a Goalie object, the Goalie class toString() method is called.  Similarly, when the System.out.println() parameter is a Skater object, the Skater class toString() method is called.  Through the Polymorphism achieved by class inheritance and method overriding, we may perform a single action – e.g., output an object in String format – but accomplish this in a context-relevant way.
________________________________________________________________________________________________________________________________________
### This application leverages 4 strategies to avoid program failure at the time the program is run: (i) Exception Handling via try-catch, (ii) Exception Handling via if-statements, (iii) Generics, (iv) @Override annotation
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

- (ii) The ```setShootingPercent(int goals, int shots)``` in the Skater class uses an **if-statement implicit exception handling strategy** to avoid an ArithmeticException at the time the program is run.
```
public void setShootingPercent(int goals, int shots){
	if(shots == 0){
		shootingPercent = (float)0;
	}
	shootingPercent = ((float)goals / (float)shots)*100;	
}
``` 
**Motivation for use:** The JVM throws an ArithmeticException when code attempts to divide by zero.  At the time the application is run, a Skater object’s shooting percent value is calculated after the application accepts roster data from the input file.  This setter method accepts a Skater's current goals and shots values as parameters – values not known until the application is run – then divides goals by shots to calculate an individual Skater's shooting percentage.  Since it *is possible* that the Skater's shots value is zero, an initial if-statement 'short-circuits' the method before it attempts a problematic division-by-zero.

- (iii) The Roster class uses **Generics** in the roster field declaration to enable one roster of different player types, provided that each roster member is either a HockeyPlayer object or a direct descendant of a HockeyPlayer object.  

``` private ArrayList<HockeyPlayer> roster; ```

**Motivation for use:**  Java SE 8 allows programmers to add different types of objects to an ArrayList.  Specifying that only HockeyPlayer objects (in this case the most general accepted arrayList element type) or the direct descendants of HockeyPlayer objects (Skater objects and Goalie objects) be permitted in the roster arrayList, ensures that code that interacts with roster elements will know in advance the element types it will face.  This is a precaution against runtime mishaps.

- (iv) The Skater class uses an **annotated override of the toString() method** to facilitate compile-time-safe output that adheres to the data table format. 

```
@Override
public String toString(){
	return String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", getTeam(), getLastName(), getJersey(), goals, shootingPercent);
}
```

**Motivation for use:** The @Override annotation signals to the compiler that the programmer intends for the annotated method to override a superclass method or implement an interface method.  If the contract for method overriding is not fulfilled, a compiler error prevents the application from running.  Since Java automatically calls toString() when printing out an object, ensuring that the toString() override was successful guarantees that any call to print a Skater object in this application will produce output that is compatible with the data table format.
