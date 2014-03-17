package src;

import javax.swing.ImageIcon;
import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;

@SuppressWarnings("serial")
public class HelpPanel extends Panel{
	
	ImageIcon menuRO, menuORG, help[] = new ImageIcon[3], nxtRO, nxtORG, nxtNOT, prevRO, prevORG, prevNOT;	
	Label bg;
	Button menu, next, prev;
	int num=0;	
	
	public HelpPanel(){
		super(null, Util.rect(0, 0, 800, 600));
		hidePanel();
		
		loadImages();
		initComponenets();
		addComponents();
	}
	
	private void addComponents() {
		add(next);
		add(prev);
		add(menu);
		add(bg);
	}

	private void initComponenets() {
		next = new Button(nxtORG, nxtRO, Util.rect(65, 550, 53, 48));
		prev = new Button(prevNOT, null, Util.rect(10, 550, 53, 48));		
		menu = new Button(menuORG, menuRO, Util.rect(602, 500, 197, 99));
		bg = new Label(help[0], Util.rect(0,0,800,600));
	}

	private void loadImages() {
		for(int i=0; i<3; i++)
			 help[i] = new ImageIcon("images/help/help"+i+".jpg");
		menuRO=new ImageIcon("images/help/menuRo.png");
		menuORG=new ImageIcon("images/help/menuOrg.png");
		nxtORG = new ImageIcon("images/help/nextORG.png");
		nxtRO = new ImageIcon("images/help/nextRO.png");
		nxtNOT = new ImageIcon("images/help/nextNOT.png");
		prevORG = new ImageIcon("images/help/prevORG.png");
		prevRO = new ImageIcon("images/help/prevRO.png");
		prevNOT = new ImageIcon("images/help/prevNOT.png");
	}
}
