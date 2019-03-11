import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

/**
* The Roster class leverages a singleton design pattern to centralize the data.  The singleton pattern 
* is a creational pattern focused on creating only one instance of the Roster object in memory
* within the application, shareable by all classes (and threads) within the application.
* Singletons may also improve performance by loading reusable data that would otherwise be 
* time consuming to store and reload each time it is needed. 
<p>
* The Roster class stores the file location of input data, and uses this information to initialize a Path object.
<p>
* Lambdas provide means to:
* (ii) read in data from input file that initializes fields of a HockeyPlayer object (Supplier Lambda)
* (iii) read in data from input file that initializes fields of a Skater object (Function Lambda)
* (iv) read in data from input file that initializes fields of a Goalie object (Function Lambda)
<p>
* The Roster class contains an ArrayList roster field.  This ArrayList accepts HockeyPlayer objects; therefore one may 
* also add Skater and Goalie objects -- direct descendants of HockeyPlayer-- to the roster ArrayList.
<p>
* setRoster() initializes the roster ArrayList.  Helper Lambdas faciliate players' data compilation in the roster ArrayList.
<p>
* Roster's getter methods PREVENT modification of or direct access to the mutable roster object.  
* No references to the roster object are publicly available; instead, getRosterCount() returns the total number of players in roster, 
* and getHockeyPlayer() returns a HockeyPlayer in the roster based on an index value.  These getters allow one to 
* recreate this roster ArrayList (and access all roster data) WITHOUT modifying the Roster class' roster field directly.

* @author  Shadiyah Mangru
* @since   2019
*/

public class Roster{
	//fields
	private List<String> rosterData;
	private final String fileLoc = "C:\\Users\\593476\\Desktop\\Java Programs\\TabularHockeyData\\2019WSHStats.txt";
	private static int counter;
	private ArrayList<HockeyPlayer> roster;
	
	//PRIVATE constructor
	//all constructors in a singleton class are marked private, which ensures that no other class is capable
	//of instantiating another version of the class
	private Roster(){
		counter = 0;
		setRosterData();
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
	
	//setters
	//read all lines of the input roster data text file, and store this input data in an ordered List of String values.
	private void setRosterData(){
		rosterData = new ArrayList<>(); //this line avoids a null pointer exception if input file does not exist
		try{
			//.txt file that stores data needed for calculations 
			Path path = Paths.get(fileLoc);
    			rosterData = Files.readAllLines(path);
		}
		catch(IOException io){ //exception handling when unable to access input file specified
    			System.out.println("Exception1: " + io);	
    		}
    		catch(Exception e){
    			System.out.println("Exception2: " + e);
    		}
	}
	
	/**
	* This setter method consolidates the input file data into a roster instance variable.
	*/
	public void setRoster(){
		roster = new ArrayList<>();
			try{
				while (counter < rosterData.size()) {
					HockeyPlayer hp = setHP.get(); //create a HockeyPlayer object with last name, position and jersey information from the data file specified
					if(HockeyPlayer.filterOutGoalies.test(hp)){ //if HockeyPlayer is NOT a Goalie
						roster.add(setSkater.apply(hp)); //use the the setSkater Lambda to initialize a Skater object (w/input file data) and add this Skater object to the roster
					}
					else{ //if HockeyPlayer is a Goalie
						roster.add(setGoalie.apply(hp)); //use the the setGoalie Lambda to initialize a Goalie object (w/input file data) and add this Goalie object to the roster
					}
					counter++; //skip over * input (and proceed to processing next input line)
				}
			}	
			catch(Exception e){
				System.out.println("Exception3: " + e + counter);
			}
    		}
	
	//getters
	/**
	* This getter method returns the total number of players in roster
	* @return int
	*/
	public int getRosterCount(){
		return roster.size();	
	}
	
	/**
	* This getter method returns a HockeyPlayer in the roster based on an index value
	* @param index 
	* @return HockeyPlayer
	*/
	public HockeyPlayer getHockeyPlayer(int index){
		return roster.get(index);	
	}
	
	//utility Lambdas
	///**
	//* This helper lambda returns a line of data from the input file with no leading and no trailing whitespace
	//* @return String
	//*/
	//public Supplier<String> vals = () -> {return sc.nextLine().trim();};
	
	/**
	* This helper lambda reads in the name, position, and jersey fields of a HockeyPlayer object 
	* and creates (and returns) this object with these fields initialized.
	* @return HockeyPlayer object
	*/
	public Supplier<HockeyPlayer> setHP = () -> {
		String name = rosterData.get(counter).trim();
		counter++;
    		String position = rosterData.get(counter).trim();
    		counter++;
    		int jersey = Integer.parseInt(rosterData.get(counter).trim());
    		counter++;
    		return new HockeyPlayer(name, position, jersey);
	};
	
	/**
	* This lambda accepts a HockeyPlayer object and, after reading-in goals, assists, 
	* and shots values, initializes and returns a new Skater object. 
	* @param HockeyPlayer object
	* @return Skater object
	*/
	public Function<HockeyPlayer, Skater> setSkater = hp ->{
		try{ 
			String[] skaterStats = rosterData.get(counter).trim().split(":"); //goals at sS[0], assists at sS[1], and shots at sS[2]
			counter++;
			Skater s = new Skater(hp, Integer.parseInt(skaterStats[0]), Integer.parseInt(skaterStats[1]), Integer.parseInt(skaterStats[2]));
			return s;
    		}
    		catch(ArrayIndexOutOfBoundsException aiobe){ //exception handling of ArrayIndexOutOfBoundsException when colon is missing from input file
    			System.out.println("Exception4: " + aiobe);	
    			Skater s = new Skater(hp, -1, -1, -1);
    			return s;
    		}
	};
	
	/**
	* This lambda accepts a HockeyPlayer object and, after reading-in saves, shots against, 
	* and wins values, initializes and returns a new Goalie object. 
	* @param HockeyPlayer object
	* @return Goalie object
	*/
	public Function <HockeyPlayer, Goalie> setGoalie = hp -> {
		try{ 
			String[] goalieStats = rosterData.get(counter).trim().split(":"); //saves at gS[0], shotsAgainst at gS[1], wins at gS[2]
			counter++;
			Goalie g = new Goalie(hp, Integer.parseInt(goalieStats[0]), Integer.parseInt(goalieStats[1]), Integer.parseInt(goalieStats[2]));
    			return g;
		}
		catch(ArrayIndexOutOfBoundsException aiobe){ //exception handling of ArrayIndexOutOfBoundsException when any colon missing from input file
			System.out.println("Exception5: " + aiobe);	
			Goalie g = new Goalie(hp, -1, -1, -1);
			return g;
		}
	};
}
