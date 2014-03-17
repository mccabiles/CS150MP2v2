package src;
import java.awt.Point;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;


@SuppressWarnings("serial")
public class GamePanel extends Panel implements Runnable{
	//boardsize = 453x600
	
	final int locX=67, locY=2, tileSize=35;
	Point geniusCoords = new Point(10, 0), slackerCoords = new Point(3, 0), athleteCoords = new Point(7, 0),
			richKidCoords = new Point(0, 0);
	ImageIcon bg, pauseRO, pauseORG, player[] = new ImageIcon[4];
	Label bgLbl, athlete, slacker, genius, richKid;
	Button pause;
	
	Clip gameSound, startSound;
	
	public GamePanel(){
		super(null, Util.rect(0, 0, 800, 600));
		hidePanel();
		loadImages();
		initComponents();
		addComponents();
		
	}

	private void loadImages() {
		for(int i=0; i<4; i++)
			player[i] = new ImageIcon("images/play/players/player"+(i+1)+".png");
		pauseORG = new ImageIcon("images/play/pauseORG.png");
		pauseRO = new ImageIcon("images/play/pauseRO.png");
		bg = new ImageIcon("images/play/game board.png");

 		startSound = Util.createClip("game start");
 		gameSound = Util.createClip("gameplay");
	}

	private void addComponents() {
		add(athlete);
		add(genius);
		add(richKid);
		add(slacker);
		
		add(pause);
		add(bgLbl);
	}

	private void initComponents() {

		athlete = new Label(player[0], Util.rect(Xbound(athleteCoords.x), Ybound(athleteCoords.y), tileSize, tileSize));
		genius = new Label(player[2], Util.rect(Xbound(geniusCoords.x), Ybound(geniusCoords.y), tileSize, tileSize));
		richKid = new Label(player[3], Util.rect(Xbound(richKidCoords.x), Ybound(richKidCoords.y), tileSize, tileSize));
		slacker = new Label(player[1], Util.rect(Xbound(slackerCoords.x), Ybound(slackerCoords.y), tileSize, tileSize));
		
		pause = new Button(pauseORG, pauseRO, Util.rect(602, 500, 197, 99));
		bgLbl = new Label(bg, Util.rect(0,0,800,600));
	}
	
	public int Xbound(int x){
		return locX+tileSize*x;
	}
	
	public int Ybound(int y){
		return locY+tileSize*y;
	}
	@Override
	public void run() {
		Util.normalSoundPlay(gameSound);
		Util.loopSoundPlay(gameSound);
	}
	
	public void startGameSound(){
		new Thread(this).start();
	}
}