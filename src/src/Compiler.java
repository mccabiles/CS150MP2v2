package src;
import org.apache.commons.lang.StringUtils;


//Call errorCheck() first; if true, create new window (confirmation: "your total, cost, yes/no")
//						   else, create new instance of input box for same player (re-input commands until they're valid)
//Call parser() to get the commands in proper format (array of strings)

public class Compiler {
	/*
	 	public static void main(String args[]){
		String input = "move1(up)\nmove2(down)\nmove3(right)\nitem(2,target)\nPOweR(string,string)\ninteract()\n";
		System.out.println("ERROR Check: " + errorCheck(input));
		System.out.println("Commands: ");
		String [] commands = parser(input);
		for(int i = 0; i < commands.length; i++) System.out.println(commands[i]);
	}
	*/
	
	// Returns TRUE if input is valid
	// else, returns false
	public static boolean errorCheck(String input){
		boolean isCorrect = false;
		String tokens[] = input.split("\n");
		String command[];
		String args[];
		for(int i = 0; i < tokens.length & i < 10; i++){
			tokens[i] = tokens[i].trim();
			if(tokens[i].equals("")) isCorrect = true;
			if((StringUtils.countMatches(tokens[i], "(") == 1) && (StringUtils.countMatches(tokens[i], ")") == 1)){
				command = tokens[i].split("\\(");
				command[0]= command[0].trim();
				if(command[0].equalsIgnoreCase("move.1") || command[0].equalsIgnoreCase("move.2") || command[0].equalsIgnoreCase("move.3")){
					command[1] = command[1].replace(")", "");
					command[1] = command[1].trim();
					if(command[1].equalsIgnoreCase("up") || command[1].equalsIgnoreCase("down") 
						|| command[1].equalsIgnoreCase("left") || command[1].equalsIgnoreCase("right")) isCorrect = true;
					else return false;
				}
				else if(command[0].equalsIgnoreCase("item")){
					if((StringUtils.countMatches(command[1], ",") == 1)){
						command[1] = command[1].replace(")", "");
						args = command[1].split(",");
						if(args.length != 2) return false;
						else{
							args[0] = args[0].trim();
							args[1] = args[1].trim();
							if(args[0].equals("1") || args[0].equals("2") && !(args[1].equals(""))) isCorrect = true;
							else return false;
						}
					}
					else return false;
				}
				else if(command[0].equalsIgnoreCase("interact")){
					command[1] = command[1].replace(")", "");
					command[1] = command[1].trim();
					
					if(command[1].equals("")) isCorrect = true;
					else return false;
				}
				else if(command[0].equalsIgnoreCase("power")){
					if((StringUtils.countMatches(command[1], ",") == 1)){
						command[1] = command[1].replace(")", "");
						args = command[1].split(",");
						if(args.length != 2) return false;
						else{
							args[0] = args[0].trim();
							args[1] = args[1].trim();
							if(!(args[0].equals("")) && !(args[1].equals(""))) isCorrect = true;
							else return false;
						}
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		return isCorrect;
	}
	
	
	// Returns array of commands in CORRECT FORMAT (no extra spaces, all lower case)
	public static String [] parser(String input){
		String [] commands = input.split("\n");
		for(int i = 0; i < commands.length & i < 10; i++){
			commands[i] = commands[i].trim();
			commands[i] = commands[i].replace(" ", "");
			commands[i] = commands[i].replace("(", ".");
			commands[i] = commands[i].replace(")", "");
			commands[i] = commands[i].toLowerCase();
		}
		return commands;
	}
}