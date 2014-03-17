package agents;

import src.GameLogic;
import src.Util.Label;
import data.Agent;

//Agent: Athlete
//	- Action Ability: Trackstar - Gets 2 AP when stress is below 3.
//		"Point A to point B, 50 meters, 3.8 seconds."

public class Athlete extends Agent{
	final static String name = "Athlete";
	
	public Athlete(GameLogic gameLogic, int startx, int starty, Label label) {
		super(gameLogic, 10, name, startx, starty, label);
	}
	
	@Override
	public void actionProcess(){
		setAP(5);
		execBuffs();
		recover();
		abil_Trackstar();
	}
	
	private void abil_Trackstar(){
		if(getStress() <= 3){ System.out.println("A: Ability Trackstar");  addAP(2);
		}}
}
