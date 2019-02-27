import java.util.function.*;

public interface Lambdas{
	//the BiFunction Functional Interface turns two parameters into a value of a potentially different type and returns it
	//this method accepts a player's goals and shots as parameters, and returns the player's shooting percentage
	public static BiFunction<Integer, Integer, Float> shootPer = (g, s) -> {
		if(s == 0){
			return (float)0;
		}
		return ((float)g / (float)s)*100;		
	};
	
	//this method accepts a goalie's saves and shotsAg as parameters, and returns the goalie's save percentage
	public static BiFunction<Integer, Integer, Float> savePer = (s, sA) -> {
		if(sA == 0){
			return (float)0;
		}
		return ((float)s / (float)sA);		
	};
	
	//the BiConsumer Functional Interface accepts/manipulates two parameters, but does not return anything
	//this method accepts a shooting percentage and a skater, and sets that skater object's shooting percentage field
	public static BiConsumer<Float, Skater> setShootPer = (sp, sk) -> {
		sk.setShootingPercent(sp);	
	};
	
	//this method accepts a save percentage and a goalie, and sets that goalie object's save percentage field
	public static BiConsumer<Float, Goalie> setSavePer = (sp, g) -> {
		g.setSavePercent(sp);	
	};
	
	//the Predicate Functional Interface may be used to test a condition (often used when filtering or matching)
	//this method accepts a HockeyPlayer parameter and returns true if the HockeyPlayer is not a Goalie
	public static Predicate<HockeyPlayer> filterOutGoalies = hp -> {
		if(!hp.getPosition().equals("Goalie")){
			return true;	
		}
		return false;	
	};
	
	//this method accepts a HockeyPlayer parameter and returns true if the HockeyPlayer is a Goalie
	public static Predicate<HockeyPlayer> keepGoalies = hp -> {
		if(hp.getPosition().equals("Goalie")){
			return true;	
		}
		return false;	
	};
	
	//this method accepts a HockeyPlayer parameter and returns true if the HockeyPlayer wears an even-numbered jersey
	public static Predicate<HockeyPlayer> evenNumberPlayers = hp -> {
		if(hp.getJersey() % 2 == 0){
			return true;	
		}
		return false;	
	};
	
	//the Supplier Functional Interface may be used to generate or supply values without taking any input
	//this method takes no parameters and returns the String "WSH".
	public static Supplier<String> assignTeam = () -> {
		return "WSH";
	};
}