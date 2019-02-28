//DataTableMain class proves a main method to run the program and output the Stats Data Tables to the screen

import java.util.*;

public class DataTableMain{
	//main method that prints (to console) the sorted stat chart of the current roster
	public static void main(String... args){
		Roster r = new Roster();
		ArrayList<HockeyPlayer> team = new ArrayList<>(); //initialize an ArrayList with HockeyPlayer objects of current roster
		for(int i = 0; i< r.getRosterCount(); i++){				
			team.add(r.getHockeyPlayer(i));
		}
		
		Skater.printSkaterStats(team);
		Goalie.printGoalieStats(team);
	}
}