package data;

public class Buff {
	int duration;	// kung ilang turns active yung buff
	int value;		// modifier ng AP at the start of every turn
	String id;		// unique buff id; refer to Buff_table
	String name;	// name of buff
	String tooltip;	// text displayed to player
	
	public boolean countdown(){
		duration--;
		if(duration <= 0) return true;
		return false;
	}
}
