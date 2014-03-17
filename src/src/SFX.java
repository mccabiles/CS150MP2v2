package src;

import javax.sound.sampled.Clip;
import src.Util;

public class SFX extends Thread{
	Clip sfx;
	
	public SFX(String file){
		sfx = Util.createClip(file);
		this.start();
	}
	
	@Override
	public void run(){
		Util.normalSoundPlay(sfx);
	}
	
}
