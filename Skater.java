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
	
	public void setShootingPercent(int goals, int shots){
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
}
