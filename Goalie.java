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
	
	//utility Lambdas
	
	//this Lambda sets team of HockeyPlayer object,
	//casts HockeyPlayer object to Goalie object
	//calculates and sets Goalie object's save percentage
	//returns Goalie object
	public Function<HockeyPlayer, Goalie> HPTeamAndSP = hp -> {
		hp.setTeam(hp.assignTeam.get()); //calls Supplier
		Goalie g = (Goalie)hp; //narrowing cast of HockeyPlayer object to a Goalie object
		return g;
	};
	
	public Consumer<HockeyPlayer> printHPSP = hp -> {
		Goalie g = HPTeamAndSP.apply(hp);
		System.out.println(g);
	};
	
	//utility method
	//this method accepts an ArrayList<HockeyPlayer> team parameter, keeps only Goalies in input stream,
	//then outputs to screen a Goalie Stats data table
	public static void printGoalieStats(ArrayList<HockeyPlayer> team){
		System.out.println("\n***************** Save Percentages of WSH Goalies (since 3/3/2019) *****************\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-4s | %-15s | %-7s | %-15s |", "Team", "Player", "#", "Wins", "Shots Against", "Saves", "Save %"));
		System.out.println("---------------------------------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.keepGoalies :: test) //calls Predicate w/a method reference		
		.forEach(gl -> {Goalie g = (Goalie)gl; g.printHPSP.accept(gl);}); //calls Goalie class' printHPSP
		System.out.println("\n******************************************************************************************");		
	}
}
