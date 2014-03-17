package src;

import javax.swing.ImageIcon;

import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;
import src.Util.TextArea;

@SuppressWarnings("serial")
public class Container extends Panel{
	

	ImageIcon buttonRO, buttonORG;//compilerBG; 
	TextArea txtArea;
	Label bg;
	Button execute;
	
	public Container(){
		super(null, Util.rect(0,0,280,200));

		loadImages();
		initComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(txtArea.pane);
		add(execute);
		//add(bg);
	}

	private void loadImages() {
		//compilerBG = new ImageIcon("images/play/compilerBG.png");
		buttonRO = new ImageIcon("images/play/buttonRO.png");
		buttonORG = new ImageIcon("images/play/buttonORG.png");
	}
	
	private void initComponents() {
		txtArea = new TextArea(Util.rect(8, 15, 265, 135));
		execute = new Button(buttonORG, buttonRO, Util.rect(90, 150, 100, 50));
	//	bg = new Label(compilerBG, Util.rect(0, 0, 400, 200));
	}
}