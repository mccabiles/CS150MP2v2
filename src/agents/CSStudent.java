package agents;

import src.GameLogic;
import src.Util.Label;
import data.Agent;

// Agent: CS STUDENT
//	- Execution Ability: Hacking - Admin powers cool down twice as fast.
//		"There is no spoon. And sleep."

public class CSStudent extends Agent{
	final static String name = "CS Student";
	public CSStudent(GameLogic gameLogic, int startx, int starty, Label label){
		super(gameLogic, 9, "CS Student", startx, starty, label);
	}
	
	@Override
	public void execProcess(){
		execStress();
		execCooldown();
		abil_Hacking();
	}
	
	private void abil_Hacking(){
		if(getStress() <= 6 & getStress() >= 3) 
			execCooldown();
	}
}
