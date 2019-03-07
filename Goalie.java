/**
* The Goalie class provides a template for the Goalie object.  Goalie 'is-a' HockeyPlayer, 
* therefore the Goalie class inherits from the HockeyPlayer class. 
* <p>
* A Goalie object has indirect access to the private lastName, position, jersey, and team 
* instance variables of its parent HockeyPlayer object.  This access is through inheritance of the 
* getLastName(), getPosition(), getJersey(), and getTeam() methods.
* <p>
* A Goalie object also has saves, shots against, wins, and save percentage values.  Getter methods
* return the current values of these for the Goalie object to which it is tethered.  A setter method calculates
* save percentage (from saves and shots against).
* <p>
* One must initialize a Goalie object with its corresponding HockeyPlayer object 
* AND values for saves, shots against, and wins.
* 
* @author  Shadiyah Mangru
* @since   2019 
*/

public class Goalie extends HockeyPlayer{
	//fields
	private int saves;
	private int shotsAgainst;
	private int wins;
	private float savePercent;
	
	//constructor
	/**
	* The Goalie constructor requires this Goalie's corresponding HockeyPlayer object, 
	* saves, shots against, and wins values to initialize a new Goalie object.
	* @param hp the corresponding HockeyPlayer object
	* @param saves 
	* @param shotsAgainst 
	* @param wins 
	*/
	public Goalie(HockeyPlayer hp, int saves, int shotsAgainst, int wins){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getTeam());
		this.saves = saves;
		this.shotsAgainst = shotsAgainst;
		this.wins = wins;
		setSavePercent(saves, shotsAgainst);
	}
	
	//setters
	/**
	* The setSavePercent method calculates this Goalie object's save percentage
	* from this Goalie object's saves and shots against values.
	* @param saves 
	* @param shotsAgainst 
	*/
	public void setSavePercent(int saves, int shotsAgainst){
		if(shotsAgainst == 0){
			savePercent = (float)0;
		}
		savePercent = ((float)saves / (float)shotsAgainst);			
	}
	
	//getters
	public int getSaves(){
		return saves;	
	}
	
	public int getShotsAgainst(){
		return shotsAgainst;	
	}
	
	public int getWins(){
		return wins;	
	}
	
	public float getSavePercent(){
		return savePercent;	
	}
	
	/**
	* toString() returns the team, last name, jersey, wins, shots against, saves, and save percentage values of a Goalie object, formatted for a data table.
	* @return data table formatting of a Goalie object's instance variable values (in the form of a String)
	*/
	@Override
	public String toString(){
		return String.format("| %-4s | %-15s | %-4s | %-4s | %-15s | %-7s | %-15s |", getTeam(), getLastName(), getJersey(), wins, shotsAgainst, saves, savePercent);	
	}
}
