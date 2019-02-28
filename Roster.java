//The Roster class contains an ArrayList roster field.  This ArrayList accepts HockeyPlayer objects,
//therefore one may also add Skater and Goalie objects -- direct descendants of HockeyPlayer-- to the roster ArrayList.

//setRoster() initializes the roster ArrayList and creates an instance of the LoadData class.  
//Helper Lambdas from the LoadData class faciliate players' data compilation in the roster ArrayList.

//Roster's getter methods PREVENT modification of or direct access to the mutable roster object.
//No references to the roster object are publicly available; 
//instead, getRosterCount() returns the total number of players in roster, 
//and getHockeyPlayer() returns a HockeyPlayer in the roster based on an index value.
//These getters allow one to recreate this roster ArrayList (and access all roster data) 
//WITHOUT modifying the Roster class' roster field directly.

import java.util.*;

public class Roster{
	//fields
	private ArrayList<HockeyPlayer> roster;
	
	//constructor
	public Roster(){
		setRoster();
	}
	
	//setter
	public void setRoster(){
		roster = new ArrayList<>();
		LoadData ld = new LoadData(); //create an instance of the LoadData class
		try{
    			while (ld.getSc().hasNextLine()) {
    				HockeyPlayer hp = ld.setHP.get(); //create a HockeyPlayer object with last name, position and jersey information from the data file specified in the LoadData class
    				if(HockeyPlayer.filterOutGoalies.test(hp)){ //if HockeyPlayer is NOT a Goalie
    					roster.add(ld.setSkater.apply(hp)); //use the the setSkater Lambda provided in the LoadData class to initialize a Skater object (w/input file data) and add this Skater object to the roster
				}
				else{ //if HockeyPlayer is a Goalie
					roster.add(ld.setGoalie.apply(hp)); //use the the setGoalie Lambda provided in the LoadData class to initialize a Goalie object (w/input file data) and add this Goalie object to the roster
				}
				ld.vals.get();   //skip over * input with the vals Lambda provided in the LoadData class
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
}
