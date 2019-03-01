//the Roster class leverages a singleton design pattern.
//the singleton design pattern facilitates creation of only one instance of the Roster object in memory
//a singleton Roster centralizes the data
//the singleton pattern is a creational pattern focused on creating only one instance of an object in memory
//within an application, shareable by all classes and threads within the application.
//Singletons may also improve performance by loading reusable data that would otherwise be 
//time consuming to store and reload each time it is needed. 

//the Roster class stores the file location of input data, and uses this information to 
//initialize a Scanner object.
//Lambdas provide means to:
//(i) read in data with no leading and no trailing whitespace
//(ii) read in data from input file that initializes fields of a HockeyPlayer object
//(iii) read in data from input file that initializes fields of a Skater object
//(iv) read in data from input file that initializes fields of a Goalie object

//The Roster class contains an ArrayList roster field.  This ArrayList accepts HockeyPlayer objects,
//therefore one may also add Skater and Goalie objects -- direct descendants of HockeyPlayer-- to the roster ArrayList.

//setRoster() initializes the roster ArrayList.  Helper Lambdas faciliate players' data compilation in the roster ArrayList.

//Roster's getter methods PREVENT modification of or direct access to the mutable roster object.
//No references to the roster object are publicly available; 
//instead, getRosterCount() returns the total number of players in roster, 
//and getHockeyPlayer() returns a HockeyPlayer in the roster based on an index value.
//These getters allow one to recreate this roster ArrayList (and access all roster data) 
//WITHOUT modifying the Roster class' roster field directly.

import java.io.*;
import java.util.*;
import java.util.function.*;

public class Roster{
	//fields
	private Scanner sc;
	private final String fileLoc = "C:\\Users\\593476\\Desktop\\Java Programs\\TabularHockeyData\\2019WSHStats.txt";
	
	private String name;
	private String position;
	private int jersey;
	private String [] goalsShotsArray;
	private String [] savesShotsAgArray;
	
	private ArrayList<HockeyPlayer> roster;
	
	//PRIVATE constructor
	//all constructors in a singleton class are marked private, which ensures that no other class is capable
	//of instantiating another version of the class
	private Roster(){
		setSc();
		setRoster();
	}
	
	//singletons in Java are created as private static variables within the class, often with the name instance
	private static final Roster instance = new Roster();
	
	//singletons are accessed via a single public static method, often named getInstance(), which returns the reference to the singleton object
	public static Roster getInstance(){
		return instance;	
	}
	
	//setters
	//initialize a new Scanner object that will read input from file specified by the fileLoc field.
	private void setSc(){
		try{
			//.txt file that stores data needed for calculations
			File file = new File(fileLoc); 
    			sc = new Scanner(file); 
		}
		catch(IOException io){ //exception handling when unable to access input file specified
    			System.out.println("Exception: " + io);	
    		}
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}
	
	private void setName(String name){
		this.name = name;
	}
	
	private void setPosition(String position){
		this.position = position;	
	}
	
	private void setJersey(int jersey){
		this.jersey = jersey;	
	}
	
	private void setGoalsShotsArray(String[] goalsShotsArray){
		this.goalsShotsArray = goalsShotsArray;	
	}
	
	public void setRoster(){
		roster = new ArrayList<>();
		try{
    			while (sc.hasNextLine()) {
    				HockeyPlayer hp = setHP.get(); //create a HockeyPlayer object with last name, position and jersey information from the data file specified
    				if(HockeyPlayer.filterOutGoalies.test(hp)){ //if HockeyPlayer is NOT a Goalie
    					roster.add(setSkater.apply(hp)); //use the the setSkater Lambda to initialize a Skater object (w/input file data) and add this Skater object to the roster
				}
				else{ //if HockeyPlayer is a Goalie
					roster.add(setGoalie.apply(hp)); //use the the setGoalie Lambda to initialize a Goalie object (w/input file data) and add this Goalie object to the roster
				}
				vals.get();   //skip over * input with the vals Lambda 
    			}
    		}	
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}
	
	//getters
	public int getRosterCount(){
		return roster.size();	
	}
	
	public HockeyPlayer getHockeyPlayer(int index){
		return roster.get(index);	
	}
	
	//utility Lambdas

	//read in a line of data from input file with no leading and no trailing whitespace
	public Supplier<String> vals = () -> {return sc.nextLine().trim();};
	
	//this helper lambda reads in the name, position, and jersey fields of a HockeyPlayer object 
	//and creates (and returns) this object with these fields initialized
	public Supplier<HockeyPlayer> setHP = () -> {
		setName(vals.get());
    		setPosition(vals.get());
    		setJersey(Integer.parseInt(vals.get()));
    		return new HockeyPlayer(name, position, jersey);
	};
	
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
	
	//this lambda accepts a HockeyPlayer and, after reading-in saves and shotsAg values, initializes and returns a new goalie object 
	public Function <HockeyPlayer, Goalie> setGoalie = hp -> {
		try{ //exception handling of ArrayIndexOutOfBoundsException when colon is missing from input file
			savesShotsAgArray = vals.get().split(":"); //saves at ssa[0] and shotsAgainst at ssa[1]
			Goalie g = new Goalie(hp, Integer.parseInt(savesShotsAgArray[0]), Integer.parseInt(savesShotsAgArray[1]));
    			return g;
		}
		catch(Exception e){
			System.out.println("Exception: " + e);	
			Goalie g = new Goalie(hp, -1, -1);
			return g;
		}
	};
}
