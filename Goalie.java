//the Goalie class provides a template for the Goalie object
//b/c Goalie 'is-a' HockeyPlayer, the Goalie class inherits from the HockeyPlayer class
//the Goalie class/object has indirect access to the private lastName, position, jersey, and team fields of the 
//parent HockeyPlayer class/object through inheritance of the getLastName(), getPosition(), getJersey(),
//and getTeam() methods

//the Goalie object also has saves, shots against, and save percentage values.
//one must initialize the Goalie object with a HockeyPlayer object AND values for saves and shots against
//setters exist to...
//getShotsAgainst(), getGoals(), getSavePercent() exist so that other classes may not have direct access
//to private Goalie class variables.

//the utility Lambdas calculate and set goalie's save percentage, respectively.
//the utility method provides a static method to print a Goalie Stats data table

import java.util.*;
import java.util.function.*;

class Goalie extends HockeyPlayer{
	//fields
	private int shotsAgainst;
	private int saves;
	private float savePercent;
	
	//constructor
	public Goalie(HockeyPlayer hp, int saves, int shotsAgainst){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getTeam());
		this.saves = saves;
		this.shotsAgainst = shotsAgainst;
		this.savePercent = (float)0;
	}
	
	//setters
	public void setShotsAgainst(int shotsAgainst){
		this.shotsAgainst = shotsAgainst;	
	}
	
	public void setSaves(int saves){
		this.saves = saves;	
	}
	
	private void setSavePercent(float savePercent){
		this.savePercent = savePercent;	
	}
	
	//getters
	public int getShotsAgainst(){
		return shotsAgainst;	
	}
	
	public int getSaves(){
		return saves;	
	}
	
	public float getSavePercent(){
		return savePercent;	
	}
	
	//utility Lambdas
	//this Lambda accepts a goalie's saves and shotsAg as parameters, and returns the goalie's save percentage
	public BiFunction<Integer, Integer, Float> sP = (s, sA) -> {
		if(sA == 0){
			return (float)0;
		}
		return ((float)s / (float)sA);		
	};
	
	//this Lambda accepts a save percentage and a goalie, and sets that goalie object's save percentage field
	public BiConsumer<Float, Goalie> setSP = (sp, g) -> {
		g.setSavePercent(sp);	
	};
	
	//this Lambda sets team of HockeyPlayer object,
	//casts HockeyPlayer object to Goalie object
	//calculates and sets Goalie object's save percentage
	//returns Goalie object
	public Function<HockeyPlayer, Goalie> HPTeamAndSP = hp -> {
		hp.setTeam(hp.assignTeam.get()); //calls Supplier
		Goalie g = (Goalie)hp; //narrowing cast of HockeyPlayer object to a Goalie object
		Float calcSP = g.sP.apply(g.getSaves(), g.getShotsAgainst()); //calls BiFunction
		g.setSP.accept(calcSP, g); //calls BiConsumer
		return g;
	};
	
	public Consumer<HockeyPlayer> printHPSP = hp -> {
		Goalie g = HPTeamAndSP.apply(hp);
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-15s | %-7s | %-15s |", g.getTeam(), g.getLastName(), g.getJersey(), g.getShotsAgainst(), g.getSaves() , g.getSavePercent()));
	};
	
	//utility method
	//this method accepts an ArrayList<HockeyPlayer> team parameter, keeps only Goalies in input stream,
	//then outputs to screen a Goalie Stats data table
	public static void printGoalieStats(ArrayList<HockeyPlayer> team){
		System.out.println("\n***************** Save Percentages of WSH Goalies (since 2/26/2019) *****************\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-15s | %-7s | %-15s |", "Team", "Player", "#", "Shots Against", "Saves", "Save %"));
		System.out.println("-------------------------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.keepGoalies :: test) //calls Predicate w/a method reference		
		.forEach(gl -> {Goalie g = (Goalie)gl; g.printHPSP.accept(gl);}); //calls Goalie class' printHPSP
		System.out.println("\n**********************************************************************************");		
	}
}
