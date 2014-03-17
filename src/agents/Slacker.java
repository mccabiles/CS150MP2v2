package agents;

import src.GameLogic;
import src.Util.Label;
import data.Agent;

//Agent: Slacker
//	- Execution Ability: Chill - reduces incoming stress per turn by 1.
//		"I make procrastinating an art form, and this is my masterpiece."

public class Slacker extends Agent{
	final static String name = "Slacker";
	
	public Slacker(GameLogic gameLogic, int startx, int starty, Label label) {
		super(gameLogic, 10, name, startx, starty, label);
	}
	
	@Override
	public void execProcess(){
		abil_Chill();
		execStress();
		execCooldown();
	}
	
	private void abil_Chill(){
		if(getIncStress() >= 1) addStress(-1);
	}
}
