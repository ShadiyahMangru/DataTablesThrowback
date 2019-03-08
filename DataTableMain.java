import java.util.*;
import java.util.function.*;
import java.io.Console;

/**
* DataTableMain class provides a main method to run the program and output the Stats Data Tables to the screen.
*<p>
* compareByGoalsThenName() sorts method parameters in descending order by goals; 
* when players tie for goals, they are sorted in ascending order by last name.
* compareByPointsThenName() sorts method parameters in descending order by points; 
* when players tie for points, they are sorted in ascending order by last name.
*<p>
* HPTeam is a utility Lambda that sets a HockeyPlayer's team to 'WSH'.
* printHPStats is a utility Lambda that dynamically prints a HockeyPlayer's position-dependent data table stats entry. 
* printDataTable outputs the roster's Stats Data Table to the screen.
*
* @author  Shadiyah Mangru
* @since   2019
*/

public class DataTableMain{
	//field
	private String lastUpdated;
	private Comparator<HockeyPlayer> sortBy;
	
	//constructor
	public DataTableMain(String lastUpdated){
		this.lastUpdated = lastUpdated;	
		sortBy = DataTableMain :: compareByPointsThenName; //the DEFAULT sort by method for the data table
	}
	
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
	
	public <HockeyPlayer> Predicate<HockeyPlayer> not(Predicate<HockeyPlayer> h) {
		return h.negate();
	}
	
	//utility method
	//outputs to screen a Skater and Goalie Stats data table
	public void printDataTable(ArrayList<HockeyPlayer> team, Comparator<HockeyPlayer> sortBy){	
		System.out.println("\n******* Stats of WSH Forwards and Defense (since " + lastUpdated + ") *******\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-7s | %-15s |", "Team", "Player", "#", "Goals", "Points", "Shooting %"));
		System.out.println("----------------------------------------------------------------------------");
		team.stream()
		.filter(not(HockeyPlayer.keepGoalies :: test)) //calls Predicate w/a method reference
		.sorted(sortBy) //sort stream with a Comparator!
		.forEach(hp -> printHPStats.accept(hp)); //calls printHPStats
		System.out.println("\n***********************************************************************************");	
	
		System.out.println("\n***************** Stats of WSH Goalies (since " + lastUpdated + ") *****************\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-4s | %-15s | %-7s | %-15s |", "Team", "Player", "#", "Wins", "Shots Against", "Saves", "Save %"));
		System.out.println("---------------------------------------------------------------------------------------");
		team.stream()
		.filter(HockeyPlayer.keepGoalies :: test) //calls Predicate w/a method reference		
		.forEach(hp -> printHPStats.accept(hp)); //calls printHPStats
		System.out.println("\n******************************************************************************************");		
	}
	
	public void mainMenu(ArrayList<HockeyPlayer> team){
		System.out.println("*******************************************************");
		System.out.println(String.format("%-52s %2s", "**** Welcome to Hockey Stats Data Table Wizard! ****", "*"));
		System.out.println(String.format("%-52s %2s", "", "*"));
		System.out.println(String.format("%-52s %2s", "Make a Sort By Selection", "*"));
		System.out.println(String.format("%-52s %2s", "1.) Points", "*"));
		System.out.println(String.format("%-52s %2s", "2.) Goals", "*"));
		System.out.println(String.format("%-52s %2s", "", "*"));
		System.out.println(String.format("%-52s %2s", "3.) Exit", "*"));
		System.out.println(String.format("%-52s %2s", "", "*"));
		System.out.println("*******************************************************");
		Console console = System.console();
		String userInput = "";
		if(console != null){
			userInput = console.readLine();
			console.writer().println("Your selection: " + userInput);
		}
		if(userInput.equals("3")){
			System.exit(0);
		}
		else if(userInput.equals("2")){
			sortBy = DataTableMain :: compareByGoalsThenName; //the sort by method for the data table	
		}
		//else it defaults to sorting by points
		printDataTable(team, sortBy);
		mainMenu(team);
	}
	
	//main method that prints (to console) the sorted stat chart of the current roster
	public static void main(String... args){
		//a process that wants to use the Roster singleton first calls getInstance() and then calls the appropriate public method
		Roster r = Roster.getInstance();
		ArrayList<HockeyPlayer> team = new ArrayList<>(); //initialize an ArrayList with HockeyPlayer objects of current roster
		for(int i = 0; i< r.getRosterCount(); i++){				
			team.add(r.getHockeyPlayer(i));
		}
		
		DataTableMain dtm = new DataTableMain("3/6/2019");
		dtm.mainMenu(team);
	}
}
