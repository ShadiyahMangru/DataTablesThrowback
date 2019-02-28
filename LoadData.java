//the LoadData class stores the file location of input data, and uses this information to 
//initialize a Scanner object.
//Lambdas provide means to:
//(i) read in data with no leading and no trailing whitespace
//(ii) read in data from input file that initializes fields of a HockeyPlayer object
//(iii) read in data from input file that initializes fields of a Skater object
//(iv) read in data from input file that initializes fields of a Goalie object

import java.io.*;
import java.util.*;
import java.util.function.*;

public class LoadData{
	//field
	private Scanner sc;
	private final String fileLoc = "C:\\Users\\593476\\Desktop\\Java Programs\\TabularHockeyData\\2019WSHStats.txt";
	
	//constructor
	public LoadData(){
		setSc();	
	}
	
	//setter
	//initialize a new Scanner object that will read input from file specified by the fileLoc field.
	public void setSc(){
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
	
	//getter
	public Scanner getSc(){
		return sc;	
	}
	
	//utility Lambdas

	//read in a line of data from input file with no leading and no trailing whitespace
	public Supplier<String> vals = () -> {return sc.nextLine().trim();};
	
	//this helper lambda reads in the name, position, and jersey fields of a HockeyPlayer object 
	//and creates (and returns) this object with these fields initialized
	public Supplier<HockeyPlayer> setHP = () -> {
		String name = vals.get();
    		String position  = vals.get();
    		int jersey = Integer.parseInt(vals.get());
    		return new HockeyPlayer(name, position, jersey);
	};
	
	//this lambda accepts a HockeyPlayer and, after reading-in goals and shots values, initializes and returns a new skater object 
	public Function<HockeyPlayer, Skater> setSkater = hp ->{
		try{ //exception handling of ArrayIndexOutOfBoundsException when colon is missing from input file
			String [] goalsShotsArray = {"", ""};
			goalsShotsArray = vals.get().split(":"); //goals at gs[0] and shots at gs[1]
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
			String [] savesShotsAgArray = {"", ""};
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
