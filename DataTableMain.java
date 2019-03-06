//DataTableMain class proves a main method to run the program and output the Stats Data Tables to the screen
//the utility method references define Comparators to sort Skaters 
//(i) in descending order by goals; when players tie for goals, they are sorted in ascending order by last name
//(ii) in descending order by points; when players tie for points, they are sorted in ascending order by last name
//the next utility method provides a static method to print a Skater Stats data table
import java.util.*;
import java.util.function.*;

public class DataTableMain{
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
	Comparator<HockeyPlayer> byGoalsThenName = DataTableMain :: compareByGoalsThenName;
	
	//Comparator that sorts HockeyPlayers (who are Not Goalies) by points then lastname, written with a Java SE 8 method reference
	Comparator<HockeyPlayer> byPointsThenName = DataTableMain :: compareByPointsThenName;
	
	//utility Lambdas
	//this Lambda sets team of HockeyPlayer object,
	//dynamically casts HockeyPlayer object to Skater/Goalie object
	//returns a HockeyPlayer object
	public Function<HockeyPlayer, HockeyPlayer> HPTeam = hp ->{
		hp.setTeam(hp.assignTeam.get()); //calls Supplier
		return hp;
	};
	
	public Consumer<HockeyPlayer> printHPStats = hp -> {
		if(hp instanceof Skater){
			Skater s = (Skater)HPTeam.apply(hp);
			System.out.println(s);
		}
		else{
			Goalie g = (Goalie)HPTeam.apply(hp);
			System.out.println(g);
		}
	};	
	
	
	//utility method
	//outputs to screen a Skater and Goalie Stats data table
	public void printDataTable(ArrayList<HockeyPlayer> team){
		System.out.println("\n******* Points and Shooting Percentages of WSH Forwards and Defense (since 3/3/2019) *******\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", "Team", "Player", "#", "Points", "Shooting %"));
		System.out.println("---------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.filterOutGoalies :: test) //calls Predicate w/a method reference
		//.filter(Lambdas.evenNumberPlayers :: test) //calls Predicate w/a method reference
		.sorted(byPointsThenName) //sort stream with a Comparator!
		.forEach(sk -> printHPStats.accept(sk)); //calls Skater classes' printHPSP
		System.out.println("\n****************************************************************");	
	
		System.out.println("\n***************** Save Percentages of WSH Goalies (since 3/3/2019) *****************\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-4s | %-15s | %-7s | %-15s |", "Team", "Player", "#", "Wins", "Shots Against", "Saves", "Save %"));
		System.out.println("---------------------------------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.keepGoalies :: test) //calls Predicate w/a method reference		
		.forEach(gl -> printHPStats.accept(gl)); //calls Goalie class' printHPSP
		System.out.println("\n******************************************************************************************");		
	}
	
	//main method that prints (to console) the sorted stat chart of the current roster
	public static void main(String... args){
		//a process that wants to use the Roster singleton first calls getInstance() and then calls the appropriate public method
		Roster r = Roster.getInstance();
		ArrayList<HockeyPlayer> team = new ArrayList<>(); //initialize an ArrayList with HockeyPlayer objects of current roster
		for(int i = 0; i< r.getRosterCount(); i++){				
			team.add(r.getHockeyPlayer(i));
		}
		
		DataTableMain dtm = new DataTableMain();
		dtm.printDataTable(team);
	}
}
