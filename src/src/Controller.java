package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Controller implements ActionListener, MouseListener{

	GameFrame frame;
	GameLogic logic;
	Object lock;
	Clip buttonSounds = Util.createClip("click"), exitSound = Util.createClip("exit button");

	public Controller(GameFrame frame){
		this.frame = frame;
		this.lock = new Object();
		frame.addListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		frame.menupanel.infos.setVisible(true);
		checkSource(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		frame.menupanel.infos.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	public void checkSource(MouseEvent e) {
		if(e.getSource() == frame.menupanel.play){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[0]);
		}

		else if(e.getSource() == frame.menupanel.help){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[1]);
		}

		else if(e.getSource() == frame.menupanel.credits){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[2]);
		}

		else if(e.getSource() == frame.menupanel.exit){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[3]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == frame.menupanel.play){
			Util.normalSoundPlay(buttonSounds);
			
			frame.menupanel.menuSound.stop();
			Util.delay(.5);
			Util.normalSoundPlay(frame.gamepanel.startSound);
			
			frame.menupanel.hidePanel();
			frame.gamepanel.showPanel();

			JOptionPane.showMessageDialog( null  , "Game is starting!");
			
			frame.gamepanel.startGameSound();

			logic = new GameLogic(frame, lock);
			logic.start();
		}

		else if(event.getSource() == frame.menupanel.help){
			Util.normalSoundPlay(buttonSounds);
			
			frame.menupanel.hidePanel();
			frame.helppanel.showPanel();
		}

		else if(event.getSource() == frame.menupanel.credits){
			Util.normalSoundPlay(buttonSounds);
			
			frame.menupanel.hidePanel();
			frame.creditspanel.showPanel();
		}

		else if(event.getSource() == frame.menupanel.exit){
			Util.normalSoundPlay(exitSound);
			
			System.gc();
			System.exit(0);
		}

		else if(event.getSource() == frame.creditspanel.menu){
			Util.normalSoundPlay(buttonSounds);
			
			frame.creditspanel.hidePanel();
			frame.menupanel.showPanel();
		}

		else if(event.getSource() == frame.helppanel.menu){
			Util.normalSoundPlay(buttonSounds);
			
			frame.helppanel.hidePanel();
			frame.menupanel.showPanel();
			
			frame.helppanel.num=0;
			frame.helppanel.bg.setIcon(frame.helppanel.help[0]);
			frame.helppanel.prev.setIcon(frame.helppanel.prevNOT);
			frame.helppanel.prev.setRolloverEnabled(false);
			frame.helppanel.next.setIcon(frame.helppanel.nxtORG);
			frame.helppanel.next.setRolloverIcon(frame.helppanel.nxtRO);
		}

		else if(event.getSource() == frame.gamepanel.pause){
			Util.normalSoundPlay(buttonSounds);
			frame.gamepanel.gameSound.stop();
			frame.menupanel.startMenuSound();
			
			logic.interrupt();
			GameFrame.getWindow().hideWindow();

			frame.gamepanel.hidePanel();
			frame.menupanel.showPanel();
		}

		else if(event.getSource() == frame.container.execute){
			String input = frame.container.txtArea.getText();

			if(Compiler.errorCheck(input)){
				logic.getCommands(input);
				GameFrame.getWindow().hideWindow();
				synchronized(lock){
					lock.notify();
				}
			}
			//else {
				//print error
			//}
		}
		
		else if(event.getSource() == frame.helppanel.next){
			 			if(frame.helppanel.num==0){
			 				frame.helppanel.prev.setIcon(frame.helppanel.prevORG);
			 				frame.helppanel.prev.setRolloverIcon(frame.helppanel.prevRO);
			 				
			 				frame.helppanel.next.setIcon(frame.helppanel.nxtORG);
			 				frame.helppanel.next.setRolloverIcon(frame.helppanel.nxtRO);
			 				frame.helppanel.bg.setIcon(frame.helppanel.help[1]);
			 				frame.helppanel.num++;
			 			}
			 			else if(frame.helppanel.num==1){
			 				frame.helppanel.prev.setIcon(frame.helppanel.prevORG);
			 				frame.helppanel.prev.setRolloverIcon(frame.helppanel.prevRO);
			 				
			 				frame.helppanel.next.setIcon(frame.helppanel.nxtNOT);
			 				frame.helppanel.next.setRolloverEnabled(false);
			 				frame.helppanel.bg.setIcon(frame.helppanel.help[2]);
			 				frame.helppanel.num++;
			 			}
			 			else if(frame.helppanel.num==2){
			 				frame.helppanel.prev.setIcon(frame.helppanel.prevORG);
			 				frame.helppanel.prev.setRolloverIcon(frame.helppanel.prevRO);
			 				
			 				frame.helppanel.next.setIcon(frame.helppanel.nxtNOT);
			 			}
			 		}
			 		
			 		else if(event.getSource() == frame.helppanel.prev){
			 			if(frame.helppanel.num==0){
			 				frame.helppanel.next.setIcon(frame.helppanel.nxtORG);
			 				frame.helppanel.next.setRolloverIcon(frame.helppanel.nxtRO);
			 				
			 				frame.helppanel.prev.setIcon(frame.helppanel.prevNOT);
			 			}
			 			else if(frame.helppanel.num==1){
			 				frame.helppanel.next.setIcon(frame.helppanel.nxtORG);
			 				frame.helppanel.next.setRolloverIcon(frame.helppanel.nxtRO);
			 				
			 				frame.helppanel.prev.setIcon(frame.helppanel.prevNOT);
			 				frame.helppanel.prev.setRolloverEnabled(false);
			 				frame.helppanel.bg.setIcon(frame.helppanel.help[0]);
			 				frame.helppanel.num--;
			 			}
			 			else if(frame.helppanel.num==2){
			 				frame.helppanel.next.setIcon(frame.helppanel.nxtORG);
			 				frame.helppanel.next.setRolloverIcon(frame.helppanel.nxtRO);
			 				
			 				frame.helppanel.prev.setIcon(frame.helppanel.prevORG);
			 				frame.helppanel.prev.setRolloverIcon(frame.helppanel.prevRO);
			 				frame.helppanel.bg.setIcon(frame.helppanel.help[1]);
			 				frame.helppanel.num--;
			 			}
			 		}
	}

}