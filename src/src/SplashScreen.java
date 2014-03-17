package src;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow{
	
	GameFrame gameFrame;
	Controller controller;
	
	MenuPanel menupanel;
	GamePanel gamepanel;
	CreditsPanel creditspanel;
	HelpPanel helppanel;
	
	Util.Label splash;
	
	public SplashScreen() {
		super();
		Util.setEnvironmentValues();
		loadComponentsAndImage();
		addComponents();
		setSplashScreen();
		loadGame();
		showFrame();
	}

	private void showFrame() {
		setVisible(false);
		dispose();
		gameFrame.setVisible(true);
		gameFrame.intropanel.startIntro();
	}

	private void loadGame() {
		gameFrame = new GameFrame();
		Util.collectGarbage();
		menupanel = new MenuPanel();
		helppanel = new HelpPanel();
		creditspanel = new CreditsPanel();
		gamepanel = new GamePanel();
	}

	private void setSplashScreen() {
		setLayout(null);
		setSize(400, 250);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addComponents() {
		add(splash);
	}

	private void loadComponentsAndImage() {
		splash = new Util.Label(new ImageIcon("images/splash.gif"), Util.rect(0, 0, 400, 250));
	}
}
