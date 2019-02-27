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
	
	public void setSavePercent(float savePercent){
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
}