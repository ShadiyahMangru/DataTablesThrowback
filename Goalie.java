//the Goalie class provides a template for the Goalie object
//b/c Goalie 'is-a' HockeyPlayer, the Goalie class inherits from the HockeyPlayer class
//the Goalie class/object has indirect access to the private lastName, position, jersey, and team fields of the 
//parent HockeyPlayer class/object through inheritance of the getLastName(), getPosition(), getJersey(),
//and getTeam() methods

//the Goalie object also has saves, shots against, wins, and save percentage values.
//one must initialize the Goalie object with a HockeyPlayer object AND values for saves, shots against, and wins
//setters exist to...
//getSaves(), getShotsAgainst(), getWins(), and getSavePercent() exist so that other classes may not have direct access
//to private Goalie class variables.

//the utility Lambdas exist to...
//the utility method provides a static method to print a Goalie Stats data table

import java.util.*;
import java.util.function.*;

class Goalie extends HockeyPlayer{
	//fields
	private int saves;
	private int shotsAgainst;
	private int wins;
	private float savePercent;
	
	//constructor
	public Goalie(HockeyPlayer hp, int saves, int shotsAgainst, int wins){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getTeam());
		this.saves = saves;
		this.shotsAgainst = shotsAgainst;
		this.wins = wins;
		setSavePercent(saves, shotsAgainst);
	}
	
	//setters
	public void setSaves(int saves){
		this.saves = saves;	
	}
	
	public void setShotsAgainst(int shotsAgainst){
		this.shotsAgainst = shotsAgainst;	
	}
	
	public void setWins(int wins){
		this.wins = wins;	
	}
	
	private void setSavePercent(int saves, int shotsAgainst){
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
	
	@Override
	public String toString(){
		return String.format("| %-4s | %-15s | %-4s | %-4s | %-15s | %-7s | %-15s |", getTeam(), getLastName(), getJersey(), wins, shotsAgainst, saves, savePercent);	
	}
}
