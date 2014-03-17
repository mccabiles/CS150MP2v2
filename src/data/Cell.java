package data;

public class Cell {
	
	boolean passable;
	Agent occupant;
	char celltype;
	
	// Metadata for cells.
	// Pag normal cells, 0 lang.
	// Otherwise, may value depende sa type of cell. 
	// For enlistment cells, nakalagay sa metadata yung kung anong subject:
	//	1 - MST
	//	2 - AH
	//	3 - SSP
	//	4 - PE
	//	5 - NSTP
	// 	6 - Vinzon's
	// For jeepney terminals, nakalagay yung order of traversal nila
	int meta = 0;
	int x;
	int y;
	
	public Cell(char value, int x, int y){
		occupant = null;
		celltype = value;
		this.x = x;
		this.y = y;
		passable = true;
	}
	
	public void setMeta(int meta) { this.meta = meta; }
	
	public Agent getOccupant() { return occupant; }
	
	public void Occupy(Agent a) { 
		occupant = a;
		onLand();
	}
	
	public int getMeta() { return meta; }
	
	public void onLand(){}
	
	
}
