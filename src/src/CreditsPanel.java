package src;

import javax.swing.ImageIcon;

import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;

@SuppressWarnings("serial")
public class CreditsPanel extends Panel{

	ImageIcon bg, menuRO, menuORG;
	Label cBg;
	Button menu;
	
	public CreditsPanel(){
		super(null, Util.rect(0,0,800,600));
		hidePanel();
		
		loadImages();
		initComponenets();
		addComponents();
	}

	private void addComponents() {
		add(menu);
		add(cBg);
	}

	private void initComponenets() {
		menu = new Button(menuORG, menuRO, Util.rect(10, 550, 123, 40));
		cBg = new Label(bg, Util.rect(0,0,800,600));
	}

	private void loadImages() {
		bg=new ImageIcon("images/credits/creditsBG.png");
		menuRO=new ImageIcon("images/menuRo.png");
		menuORG=new ImageIcon("images/menuOrg.png");
	}
}
