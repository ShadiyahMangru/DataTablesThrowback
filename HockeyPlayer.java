//the HockeyPlayer class provides a template for a HockeyPlayer object.
//the HockeyPlayer object has a last name, position, jersey number and team
//one must initialize a HockeyPlayer object with a last name, position, and jersey number
//specifying the HockeyPlayer object's team upon object initialization is optional

//setTeam() exists should the HockeyPlayer object's team change during the lifetime of the HockeyPlayer object

//getLastName(), getPosition(), getJersey(), getTeam() exist so that other classes may not have direct
//access to private HockeyPlayer class variables.

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
	public void setTeam(String team){
		this.team = team;	
	}
	
	//getters
	public String getLastName(){
		return lastName;
	}
	
	public String getPosition(){
		return position;
	}	
	
	public int getJersey(){
		return jersey;
	}
	
	public String getTeam(){
		return team;	
	}
}