/**
* The Skater class provides a template for the Skater object.  Skater 'is-a' HockeyPlayer, 
* therefore the Skater class inherits from the HockeyPlayer class. 
* <p>
* A Skater object has indirect access to the private lastName, position, jersey, and team 
* instance variables of its parent HockeyPlayer object.  This access is through inheritance of the 
* getLastName(), getPosition(), getJersey(), and getTeam() methods.
* <p>
* A Skater object also has goals, assists, points, shots, and shooting percent values.
* <p>
* One must initialize a Skater object with its corresponding HockeyPlayer object 
* AND values for goals, assists and shots.
* 
* @author  Shadiyah Mangru
* @since   2019 
*/


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
	
	/**
	* toString() returns the team, last name, jersey, points, and shooting percent values of a Skater object, formatted for a data table.
	* @return data table formatting of a Skater object's instance variable values (in the form of a String)
	*/
	@Override
	public String toString(){
		return String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", getTeam(), getLastName(), getJersey(), points, shootingPercent);
	}
}
