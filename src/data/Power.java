package data;

public class Power{
	
	// Exhaust(target): 3 turn cooldown
	//	- Reduces target AP by 2.
	public static int exhaust(Agent a){
		a.addAP(-2);
		return 3;
	}
	
	// Caffeine(target): 3 turn cooldown
	//	- Target gains 2 ap.
	public static int caffeine(Agent a){
		a.addAP(2);
		return 3;
	}
	
	// Anxiety(target): 3 turn cooldown
	//	- Target gets 2 stress
	public static int anxiety(Agent a){
		a.addStress(2);
		return 3;
	}
	
	// Refresh(target): 3 turn cooldown
	//	- Reduces stress of target by 3.
	public static int refresh(Agent a){
		a.addStress(-3);
		return 3;
	}
	
	// Carillon(): 5 turn cooldown
	//	- Increases stress of all players by 3.
	public static int carillon(Agent[] list){
		for(int i=0; i<4; i++){
			list[i].addStress(3);
		}
		return 5;
	}
	
	// Flash(target, distance, direction): 
	public static int flash(Agent a, int distance, int direction){
		
		return 5;
	}
	
}