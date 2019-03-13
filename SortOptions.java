public interface SortOptions{
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
		return 1;
	}	
	
	public static int compareByGoalsAscThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s1.getGoals() - s2.getGoals() != 0){
				return s1.getGoals() - s2.getGoals();	
			}
			return s1.getLastName().compareTo(s2.getLastName());	
		}
		catch(ClassCastException cce){
			System.out.println("Exception: " + cce);	
		}
		return 1;
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
		return 1;
	}
	
	public static int compareByPointsAscThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s1.getPoints() - s2.getPoints() != 0){
				return s1.getPoints() - s2.getPoints();	
			}
			return s1.getLastName().compareTo(s2.getLastName());	
		}
		catch(ClassCastException cce){
			System.out.println("Exception: " + cce);	
		}
		return 1;
	}
	
	//this method sorts HockeyPlayer objects that may be narrowed to Skater objects in 
	//(i) descending order by shooting percent, and (ii) ascending order by last name of Skater objects with equal shooting percent.
	public static int compareByShPercentThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s2.getShootingPercent() - s1.getShootingPercent() > 0){
				return 1;
			}
			else if(s2.getShootingPercent() - s1.getShootingPercent() < 0){
				return -1;	
			}
			else{
				return s1.getLastName().compareTo(s2.getLastName());		
			}
		}
		catch(ClassCastException cce){
			System.out.println("Exception: " + cce);	
		}
		return 1;
	}
	
	public static int compareByShPercentAscThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s1.getShootingPercent() - s2.getShootingPercent() > 0){
				return 1;
			}
			else if(s1.getShootingPercent() - s2.getShootingPercent() < 0){
				return -1;	
			}
			else{
				return s1.getLastName().compareTo(s2.getLastName());		
			}
		}
		catch(ClassCastException cce){
			System.out.println("Exception: " + cce);	
		}
		return 1;
	}
	
	//this method sorts HockeyPlayer objects in 
	//(i) descending order by games played, and (ii) ascending order by last name of Skater objects with equal games played.
	public static int compareByGPThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s2.getGamesPlayed() - s1.getGamesPlayed() != 0){
				return s2.getGamesPlayed() - s1.getGamesPlayed();	
			}
			return s1.getLastName().compareTo(s2.getLastName());	
		}
		catch(ClassCastException cce){
			Goalie g1 = (Goalie)h1;
			Goalie g2 = (Goalie)h2;
			if(g2.getGamesPlayed() - g1.getGamesPlayed() != 0){
				return g2.getGamesPlayed() - g1.getGamesPlayed();	
			}
			return g1.getLastName().compareTo(g2.getLastName());		
		}
	}
	
	public static int compareByGPAscThenName(HockeyPlayer h1, HockeyPlayer h2){
		try{
			Skater s1 = (Skater)h1;
			Skater s2 = (Skater)h2;
			if(s1.getGamesPlayed() - s2.getGamesPlayed() != 0){
				return s1.getGamesPlayed() - s2.getGamesPlayed();	
			}
			return s1.getLastName().compareTo(s2.getLastName());	
		}
		catch(ClassCastException cce){
			Goalie g1 = (Goalie)h1;
			Goalie g2 = (Goalie)h2;
			if(g1.getGamesPlayed() - g2.getGamesPlayed() != 0){
				return g1.getGamesPlayed() - g2.getGamesPlayed();	
			}
			return g1.getLastName().compareTo(g2.getLastName());		
		}
	}
}
