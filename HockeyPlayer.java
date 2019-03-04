//the HockeyPlayer class provides a template for a HockeyPlayer object.
//the HockeyPlayer object has a last name, position, jersey number and team
//one must initialize a HockeyPlayer object with a last name, position, and jersey number
//specifying the HockeyPlayer object's team upon object initialization is optional

//setTeam() exists should the HockeyPlayer object's team change during the lifetime of the HockeyPlayer object

//getLastName(), getPosition(), getJersey(), getTeam() exist so that other classes may not have direct
//access to private HockeyPlayer class variables.

//utility Lambdas exist to
//filterOutGoalies / keepGoalies returns true if the HockeyPlayer is not / is a goalie.  The two Lambdas are static.
//evenNumberPlayers returns true if the HockeyPlayer wears an even-numbered jersey
//assignTeam returns the "WSH" String.

import java.util.function.*;

public class HockeyPlayer{
	//fields
	private String lastName;
	private String position;
	private int jersey;
	private String team;

	//constructors
	//initialize HockeyPlayer object without a specific team
	public HockeyPlayer(String lastName, String position, int jersey){
		this.lastName = lastName;
		this.position = position;
		this.jersey = jersey;
		setTeam("undefined");
	}
	
	//initialize HockeyPlayer object with a specific team
	public HockeyPlayer(String lastName, String position, int jersey, String team){
		this.lastName = lastName;
		this.position = position;
		this.jersey = jersey;
		this.team = team;
	}

	//setter
	void setTeam(String team){
		this.team = team;	
	}
	
	//getters
	String getLastName(){
		return lastName;
	}
	
	String getPosition(){
		return position;
	}	
	
	int getJersey(){
		return jersey;
	}
	
	String getTeam(){
		return team;	
	}
	
	@Override
	public String toString(){
		return String.format("| %-4s | %-15s | %-4s | %-7s |", team, lastName, jersey, position);
	}
	
	//utility Lambdas
	//this Lambda accepts a HockeyPlayer parameter and returns true if the HockeyPlayer is not a Goalie
	//a static Lambda b/c it does not require an instance of the HockeyPlayer class
	public static Predicate<HockeyPlayer> filterOutGoalies = hp -> {
		if(!hp.getPosition().equals("Goalie")){
			return true;	
		}
		return false;	
	};
	
	//this Lambda accepts a HockeyPlayer parameter and returns true if the HockeyPlayer is a Goalie
	//a static Lambda b/c does not require an instance of the HockeyPlayer class
	public static Predicate<HockeyPlayer> keepGoalies = hp -> {
		if(hp.getPosition().equals("Goalie")){
			return true;	
		}
		return false;	
	};
	
	//this Lambda accepts a HockeyPlayer parameter and returns true if the HockeyPlayer wears an even-numbered jersey
	public Predicate<HockeyPlayer> evenNumberPlayers = hp -> {
		if(hp.getJersey() % 2 == 0){
			return true;	
		}
		return false;	
	};
	
	//this Lambda takes no parameters and returns the String "WSH".
	public Supplier<String> assignTeam = () -> {
		return "WSH";
	};
}
