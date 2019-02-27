import java.util.*;
import java.util.function.*;
public class StatsChart{
	//Comparator that sorts HockeyPlayers (who are Not Goalies) by goals then lastname, written with a Java SE 8 method reference
	static Comparator<HockeyPlayer> byGoalsThenName = SkaterComparisons :: compareByGoalsThenName;
	
	//the Function Functional Interface turns a parameter into a value of a potentially different type and returns it
	//this Lambda sets team of HockeyPlayer object,
	//casts HockeyPlayer object to Skater object
	//calculates and sets Skater object's shooting percentage
	//returns Skater object
	public Function<HockeyPlayer, Skater> SKTeamAndShPercent = hp ->{
		hp.setTeam(Lambdas.assignTeam.get()); //calls Supplier
		Skater s = (Skater)hp; //narrowing cast of HockeyPlayer object to a Skater object
		Float calcSP = Lambdas.shootPer.apply(s.getGoals(), s.getShots()); //calls BiFunction
		Lambdas.setShootPer.accept(calcSP, s); //calls BiConsumer
		return s;
	};
	
	//this Lambda sets team of HockeyPlayer object,
	//casts HockeyPlayer object to Goalie object
	//calculates and sets Goalie object's save percentage
	//returns Goalie object
	public Function<HockeyPlayer, Goalie> GTeamAndSavePercent = hp -> {
		hp.setTeam(Lambdas.assignTeam.get()); //calls Supplier
		Goalie g = (Goalie)hp; //narrowing cast of HockeyPlayer object to a Goalie object
		Float calcSP = Lambdas.savePer.apply(g.getSaves(), g.getShotsAgainst()); //calls BiFunction
		Lambdas.setSavePer.accept(calcSP, g); //calls BiConsumer
		return g;
	};
	
	private Consumer<HockeyPlayer> printSKShootPercent = hp -> {
		Skater s = SKTeamAndShPercent.apply(hp);
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", s.getTeam(), s.getLastName(), s.getJersey(), s.getGoals() , s.getShootingPercent()));
	};
	
	private Consumer<HockeyPlayer> printGSavePercent = hp -> {
		Goalie g = GTeamAndSavePercent.apply(hp);
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-15s | %-7s | %-15s |", g.getTeam(), g.getLastName(), g.getJersey(), g.getShotsAgainst(), g.getSaves() , g.getSavePercent()));
	};
	
	//main method that prints (to console) the sorted stat chart of the current roster
	public static void main(String... args){
		Roster r = new Roster();
		ArrayList<HockeyPlayer> team = new ArrayList<>(); //initialize an ArrayList with HockeyPlayer objects of current roster
		for(int i = 0; i< r.getRosterCount(); i++){				
			team.add(r.getHockeyPlayer(i));
		}
		StatsChart st = new StatsChart();
		
		System.out.println("\n******* Goals Scored by and Save Percentages of WSH Forwards and Defense (since 2/26/2019) *******\n");
		System.out.println("*not yet sorted\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-7s | %-15s |", "Team", "Player", "#", "Goals", "Shooting %"));
		System.out.println("---------------------------------------------------------------");
		team.stream()
		.filter(Lambdas.filterOutGoalies :: test) //calls Predicate w/a method reference
		//.filter(Lambdas.evenNumberPlayers :: test) //calls Predicate w/a method reference
		//.sorted(byGoalsThenName) //sort stream with a Comparator!
		.forEach(sk -> st.printSKShootPercent.accept(sk)); //calls printSKShootPercent
		System.out.println("\n****************************************************************");	
		
		System.out.println("\n***************** Save Percentages of WSH Goalies (since 2/26/2019) *****************\n");
		System.out.println(String.format("| %-4s | %-15s | %-4s | %-15s | %-7s | %-15s |", "Team", "Player", "#", "Shots Against", "Saves", "Save %"));
		System.out.println("-------------------------------------------------------------------------------");
		team.stream()
		.filter(Lambdas.keepGoalies :: test) //calls Predicate w/a method reference
		.forEach(gl -> st.printGSavePercent.accept(gl)); //calls printGSavePercent
		System.out.println("\n**********************************************************************************");		
	}
}