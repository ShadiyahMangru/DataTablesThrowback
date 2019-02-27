import java.util.*;

public class Roster{
	//fields
	private ArrayList<HockeyPlayer> roster;
	
	//constructor
	public Roster(){
		setRoster();
	}
	
	//setter
	public void setRoster(){
		roster = new ArrayList<>();
		LoadData ld = new LoadData();
		try{
    			while (ld.getSc().hasNextLine()) {
    				HockeyPlayer hp = ld.setHP.get();
    				if(Lambdas.filterOutGoalies.test(hp)){
    					roster.add(ld.setSkater.apply(hp));
				}
				else{
					roster.add(ld.setGoalie.apply(hp));
				}
				ld.vals.get();   //skip *
    			}
    		}	
    		catch(Exception e){
    			System.out.println("Exception: " + e);
    		}
	}
	
	//getters
	public int getRosterCount(){
		return roster.size();	
	}
	
	public HockeyPlayer getHockeyPlayer(int index){
		return roster.get(index);	
	}
}