//the Skater class provides a template for the Skater object
//b/c Skater 'is-a' HockeyPlayer, the Skater class inherits from the HockeyPlayer class
//the Skater class/object has indirect access to the private lastName, position, jersey, and team fields of the 
//parent HockeyPlayer class/object through inheritance of the getLastName(), getPosition(), getJersey(),
//and getTeam() methods

//the Skater object also has goals, shots, and shooting percent values.
//one must initialize the Skater object with a HockeyPlayer object AND values for goals and shots
//setters exist to...
//getGoals(), getShots(), getShootingPercent() exist so that other classes may not have direct access
//to private Skater class variables.

//the utility Lambdas calculate and set skater's shooting percentage, respectively.
//the utility method sorts skaters by goals scored; players with equal goals are sorted by last name.

//the utility method reference defines a Comparator to sort Skaters in descending order by goals; 
//when players tie for goals, they are sorted in ascending order by last name
//the next utility method provides a static method to print a Skater Stats data table

import java.util.*;
import java.util.function.*;

public class Skater extends HockeyPlayer{
	//fields
	private int goals;
	private int shots;
	private float shootingPercent;
	
	//constructor
	public Skater(HockeyPlayer hp, int goals, int shots){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getTeam());
		this.goals = goals;
		this.shots = shots;
		this.shootingPercent = (float)0;
	}
	
	//setters
	public void setGoals(int goals){
		this.goals = goals;	
	}
	
	public void setShots(int shots){
		this.shots = shots;	
	}
	
	private void setShootingPercent(float shootingPercent){
		this.shootingPercent = shootingPercent;	
	}
	
	//getters
	public int getGoals(){
		return goals;	
	}
	
	public int getShots(){
		return shots;	
	}
	
	public float getShootingPercent(){
		return shootingPercent;	
	}
	
	//utility Lambdas
	//this Lambda accepts a player's goals and shots as parameters, and returns the player's shooting percentage
	public BiFunction<Integer, Integer, Float> sP = (g, s) -> {
		if(s == 0){
			return (float)0;
		}
		return ((float)g / (float)s)*100;		
	};
	
	//this accepts a shooting percentage and a skater, and sets that skater object's shooting percentage field
	public BiConsumer<Float, Skater> setSP = (sp, sk) -> {
		sk.setShootingPercent(sp);	
	};
	
	//this Lambda sets team of HockeyPlayer object,
	//casts HockeyPlayer object to Skater object
	//calculates and sets Skater object's shooting percentage
	//returns Skater object
	private Function<HockeyPlayer, Skater> HPTeamAndSP = hp ->{
		hp.setTeam(hp.assignTeam.get()); //calls Supplier
		Skater s = (Skater)hp; //narrowing cast of HockeyPlayer object to a Skater object
		Float calcSP = s.sP.apply(s.getGoals(), s.getShots()); //calls BiFunction
		s.setSP.accept(calcSP, s); //calls BiConsumer
		return s;
	};
	
	public Consumer<HockeyPlayer> printHPSP = hp -> {
		Skater s = HPTeamAndSP.apply(hp);
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", s.getTeam(), s.getLastName(), s.getJersey(), s.getGoals() , s.getShootingPercent()));
	};
	
	//utility method
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
	
	//utility method reference
	//Comparator that sorts HockeyPlayers (who are Not Goalies) by goals then lastname, written with a Java SE 8 method reference
	static Comparator<HockeyPlayer> byGoalsThenName = Skater :: compareByGoalsThenName;
	
	//utility method
	//this method accepts an ArrayList<HockeyPlayer> team parameter, removes Goalies from input stream, sorts stream by goals then name,
	//then outputs to screen a Skater Stats data table
	public static void printSkaterStats(ArrayList<HockeyPlayer> team){
		System.out.println("\n******* Goals Scored by and Shooting Percentages of WSH Forwards and Defense (since 2/26/2019) *******\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", "Team", "Player", "#", "Goals", "Shooting %"));
		System.out.println("---------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.filterOutGoalies :: test) //calls Predicate w/a method reference
		//.filter(Lambdas.evenNumberPlayers :: test) //calls Predicate w/a method reference
		.sorted(byGoalsThenName) //sort stream with a Comparator!
		.forEach(sk -> {Skater s = (Skater)sk; s.printHPSP.accept(s);}); //calls Skater classes' printHPSP
		System.out.println("\n****************************************************************");	
	}
}
