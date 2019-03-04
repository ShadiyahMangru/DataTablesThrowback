//the Skater class provides a template for the Skater object
//b/c Skater 'is-a' HockeyPlayer, the Skater class inherits from the HockeyPlayer class
//the Skater class/object has indirect access to the private lastName, position, jersey, and team fields of the 
//parent HockeyPlayer class/object through inheritance of the getLastName(), getPosition(), getJersey(),
//and getTeam() methods

//the Skater object also has goals, assists, points, shots, and shooting percent values.
//one must initialize the Skater object with a HockeyPlayer object AND values for goals, assists and shots
//setters exist to...
//getGoals(), getAssists(), getPoints(), getShots(), getShootingPercent() exist so that other classes may not have direct access
//to private Skater class variables.

//the utility Lambdas exists to...

//the utility method references define Comparators to sort Skaters 
//(i) in descending order by goals; when players tie for goals, they are sorted in ascending order by last name
//(ii) in descending order by points; when players tie for points, they are sorted in ascending order by last name
//the next utility method provides a static method to print a Skater Stats data table

import java.util.*;
import java.util.function.*;

public class Skater extends HockeyPlayer{
	//fields
	private int goals;
	private int assists;
	private int points;
	private int shots;
	private float shootingPercent;
	
	//constructor
	public Skater(HockeyPlayer hp, int goals, int assists, int shots){
		super(hp.getLastName(), hp.getPosition(), hp.getJersey(), hp.getTeam());
		this.goals = goals;
		this.assists = assists;
		this.shots = shots;
		setPoints(goals, assists);
		setShootingPercent(goals, shots);
	}
	
	//setters
	public void setGoals(int goals){
		this.goals = goals;	
	}
	
	public void setAssists(int assists){
		this.assists = assists;	
	}
	
	public void setPoints(int goals, int assists){
		points = goals+assists;	
	}
	
	public void setShots(int shots){
		this.shots = shots;	
	}
	
	private void setShootingPercent(int goals, int shots){
		if(shots == 0){
			shootingPercent = (float)0;
		}
		shootingPercent = ((float)goals / (float)shots)*100;	
	}
	
	//getters
	public int getGoals(){
		return goals;	
	}
	
	public int getAssists(){
		return assists;	
	}
	
	public int getPoints(){
		return points;	
	}
	
	public int getShots(){
		return shots;	
	}
	
	public float getShootingPercent(){
		return shootingPercent;	
	}
	
	@Override
	public String toString(){
		return String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", getTeam(), getLastName(), getJersey(), points, shootingPercent);
	}
	
	//utility Lambdas
	
	//this Lambda sets team of HockeyPlayer object,
	//casts HockeyPlayer object to Skater object
	//returns Skater object
	private Function<HockeyPlayer, Skater> HPTeamAndSP = hp ->{
		hp.setTeam(hp.assignTeam.get()); //calls Supplier
		Skater s = (Skater)hp; //narrowing cast of HockeyPlayer object to a Skater object
		return s;
	};
	
	public Consumer<HockeyPlayer> printHPSP = hp -> {
		Skater s = HPTeamAndSP.apply(hp);
		System.out.println(s);
	};
	
	//utility methods
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
	
	//this method sorts HockeyPlayer objects that may be narrowed to Skater objects in 
	//(i) descending order by points, and (ii) ascending order by last name of Skater objects with equal points.
	public static int compareByPointsThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s2.getPoints() - s1.getPoints() != 0){
				return s2.getPoints() - s1.getPoints();	
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
	
	//Comparator that sorts HockeyPlayers (who are Not Goalies) by points then lastname, written with a Java SE 8 method reference
	static Comparator<HockeyPlayer> byPointsThenName = Skater :: compareByPointsThenName;
	
	//utility method
	//this method accepts an ArrayList<HockeyPlayer> team parameter, removes Goalies from input stream, sorts stream by goals then name,
	//then outputs to screen a Skater Stats data table
	public static void printSkaterStats(ArrayList<HockeyPlayer> team){
		System.out.println("\n******* Points and Shooting Percentages of WSH Forwards and Defense (since 3/3/2019) *******\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", "Team", "Player", "#", "Points", "Shooting %"));
		System.out.println("---------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.filterOutGoalies :: test) //calls Predicate w/a method reference
		//.filter(Lambdas.evenNumberPlayers :: test) //calls Predicate w/a method reference
		.sorted(byPointsThenName) //sort stream with a Comparator!
		.forEach(sk -> {Skater s = (Skater)sk; s.printHPSP.accept(s);}); //calls Skater classes' printHPSP
		System.out.println("\n****************************************************************");	
	}
}
