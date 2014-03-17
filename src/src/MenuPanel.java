package src;

import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;



import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;

@SuppressWarnings("serial")
public class MenuPanel extends Panel implements Runnable{
	
	ImageIcon bg, playORG, playRO, helpORG, helpRO, creditsORG, creditsRO, exitORG, exitRO, infoIcon[] = new ImageIcon[4];
	Label menubg, infos;
	Button play, help, credits, exit;
	Clip menuSound;
	
	public MenuPanel(){
		super(null, Util.rect(0,0,800,600));
		hidePanel();
		loadImages();
		initComponents();
		addComponents();
	}

	private void addComponents() {
		add(exit);
		add(credits);
		add(help);
		add(play);
		
		add(infos);
		add(menubg);
	}

	private void initComponents() {
		exit = new Button(exitORG, exitRO, Util.rect(575, 472, 142, 76));
		credits = new Button(creditsORG, creditsRO, Util.rect(415, 472, 142, 76));
		help = new Button(helpORG, helpRO, Util.rect(257, 472, 142, 76)); //524
		play = new Button(playORG, playRO, Util.rect(96, 472, 142, 76)); //420
		
		infos = new Label(infoIcon[0], Util.rect(143, 560, 519, 24));
		infos.setVisible(false);
		menubg = new Label(bg, Util.rect(0, 0,800, 600));
		menuSound = Util.createClip("menu");
	}

	private void loadImages() {
		bg = new ImageIcon("images/menu/bg.gif");
		playORG = new ImageIcon("images/menu/main panel buttons/play.png");	
		playRO = new ImageIcon("images/menu/main panel buttons/play_hover.png");
		helpORG = new ImageIcon("images/menu/main panel buttons/help.png");
		helpRO = new ImageIcon("images/menu/main panel buttons/help_hover.png");
		creditsORG = new ImageIcon("images/menu/main panel buttons/credits.png");
		creditsRO = new ImageIcon("images/menu/main panel buttons/credits_hover.png");
		exitORG = new ImageIcon("images/menu/main panel buttons/quit.png");
		exitRO = new ImageIcon("images/menu/main panel buttons/quit_hover.png");
		for(int i=0; i<4; i++)
			infoIcon[i] = new ImageIcon("images/menu/info"+(i+1)+".png");
	}
	
	@Override
	public void run() {
		Util.normalSoundPlay(menuSound);
		Util.loopSoundPlay(menuSound);
	}
	
	public void startMenuSound(){
		new Thread(this).start();
	}
}