package agents;

import src.GameLogic;
import src.Util.Label;
import data.Agent;

//Agent: Rich Kid
//	- Init Ability: Bourgeoisie - Starts the game with 50 pesos instead of 35.
//		"I can buy you, your friends, and that jeep. WAIT LANG MANONG PARAH POH."

public class RichKid extends Agent{
	final static String name = "Rich Kid";
	
	public RichKid(GameLogic gameLogic, int startx, int starty, Label label) {
		super(gameLogic, 10, name, startx, starty, label);
		abil_Bour();
	}
	
	private void abil_Bour(){
		this.addCash(15);
	}
}
