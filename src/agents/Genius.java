package agents;

import src.GameLogic;
import src.Util.Label;
import data.Agent;

//Agent: Genius
//	- Action Ability: Advanced Study - filler.
//		"80% smarts, 1% luck, 19% caffeine."

public class Genius extends Agent{
	final static String name = "Genius";
	
	public Genius(GameLogic gameLogic, int startx, int starty, Label label) {
		super(gameLogic, 10, name, startx, starty, label);
	}

	@Override
	public void actionProcess(){
		setAP(5);
		execBuffs();
		recover();
		abil_AdvancedStudy();
	}
	
	private void abil_AdvancedStudy(){
		if(getStress() <= 3){ System.out.println("G: Ability Advanced Study"); addAP(2); }
	}
}
