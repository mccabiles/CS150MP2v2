package src;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Util {
	
	@SuppressWarnings("serial")
	static class Panel extends JPanel{
		
		public Panel(LayoutManager layout, Rectangle bounds){
			setLayout(layout);
			setOpaque(false);
			if(bounds!=null){
				setBounds(bounds);
				setPreferredSize(new Dimension(bounds.getSize()));
			}
			setDoubleBuffered(true);
		}
		
		public void showPanel(){
			setVisible(true);
		}
		
		public void hidePanel(){
			setVisible(false);
		}
	}
	
	@SuppressWarnings("serial")
	static class TextArea extends JTextArea{
		JScrollPane pane;
		public TextArea(Rectangle bounds){
			super();
			pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pane.setBounds(bounds);
			pane.setPreferredSize(bounds.getSize());
			setTabSize(3);
		}
	}
	
/*						Label						*/
	
	@SuppressWarnings("serial")
	public static class Label extends JLabel{
		
		public Label(ImageIcon lblIcon, Rectangle bound){
			super();
			if(lblIcon!=null)
				setIcon(lblIcon);
			else
				setVisible(false);
			setBounds(bound.x, bound.y, bound.width, bound.height);
			setOpaque(false);
		}
	}

/*						Button						*/
	@SuppressWarnings("serial")
	static class Button extends JButton{
		public Button(ImageIcon icon, ImageIcon rolloverIcon, Rectangle bound){
			super();
			if(icon!=null)
				setIcon(icon);
			else
				setVisible(false);
			if(rolloverIcon != null)
				setRolloverIcon(rolloverIcon);
			setBounds(bound.x, bound.y, bound.width, bound.height);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setOpaque(false);
			setFocusable(false);
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
	
/*						Panel						*/
	@SuppressWarnings("serial")
	static class PaintPanel extends NormalPanel{
		
//	    class variables
	    public int bufferWidth;
	    public int bufferHeight;
	    public BufferedImage bufferImage;
	    public Graphics bufferGraphics;
	    
	    public boolean paint = false;
	    
		public PaintPanel(LayoutManager layout, Rectangle bound){
			super(layout, bound);
			setOpaque(true);
			setBackground(Color.BLACK);
		}

		@Override
	    public void paintComponent(Graphics g){
	    	super.paintComponent(g);
	    	Graphics2D g2d = (Graphics2D) g;
	        //    checks the buffersize with the current panelsize
	        //    or initialises the image with the first paint
	        if(bufferWidth!=getSize().width || bufferHeight!=getSize().height || bufferImage==null || bufferGraphics==null)
	            resetBuffer();
	        
	        if(bufferGraphics!=null){
	            //this clears the offscreen image, not the onscreen one
	            bufferGraphics.fillRect(0,0,bufferWidth,bufferHeight);
	            
	            //calls the paintbuffer method with the offscreen graphics as a param
	            paintBuffer(bufferGraphics);

	            //we finally paint the offscreen image onto the onscreen image
	            g2d.drawImage(bufferImage,0,0,null);
	        }

	        if(paint)
	        	repaint();
	        
	        Thread.yield();
	    }

	    private void resetBuffer(){
	        // always keep track of the image size
	        bufferWidth=getSize().width;
	        bufferHeight=getSize().height;

	        //    clean up the previous image
	        if(bufferGraphics!=null){
	            bufferGraphics.dispose();
	            bufferGraphics=null;
	        }
	        if(bufferImage!=null){
	            bufferImage.flush();
	            bufferImage=null;
	        }
	        System.gc();

	        //    create the new image with the size of the panel
	        bufferImage = gc.createCompatibleImage(bufferWidth,bufferHeight);
	        bufferGraphics=bufferImage.getGraphics();
	    }
	    
	    public void paintBuffer(Graphics g){
	        //in classes extended from this one, add something to paint here!
	        //always remember, g is the offscreen graphics
	    }
	}
	
	@SuppressWarnings("serial")
	static class NormalPanel extends JPanel{
		Label cover;
		public NormalPanel(LayoutManager layout, Rectangle bound){
			setBounds(bound.x, bound.y, bound.width, bound.height);
			setLayout(layout);
			setDoubleBuffered(true);
			setOpaque(false);
			cover = new Label(coverIcon[9], new Rectangle(0, 0, frameWidth, frameHeight));
			add(cover);
			cover.setVisible(false);
		}
		
		public void showPanel(){
			setVisible(true);
		}
		
		public void hidePanel(){
			setVisible(false);
		}
	}
	
/**				Image				**/
	static class Image{
		int x, y;
		BufferedImage img;
		boolean visibility;
		
		public Image(String dir, int x, int y){
			try{
				img = ImageIO.read(new File(dir));
			}catch(Exception e){
				System.err.println("Can't read "+dir);
			}
			this.x = x;
			this.y = y;
			visibility = true;
		}
		
		public Image(BufferedImage img, int x, int y){
			this.img = img;
			this.x = x;
			this.y = y;
			visibility = true;
		}
		
		public int getWidth(){
			return img.getWidth();
		}
		
		public int getHeight(){
			return img.getHeight();
		}
		
		public boolean isVisible(){
			return visibility;
		}
		
		public void setVisible(boolean visibility){
			this.visibility = visibility;
		}
		
		public Rectangle getBounds(int img){
			return new Rectangle(x, y, getWidth(), getHeight());
		}
	}
	
	/**		normal bufferedImage	**/
	static BufferedImage Image(String dir){
		BufferedImage img;
		try{
			img = ImageIO.read(new File(dir));
			return img;
		}catch(Exception e){
			System.err.println("Can't read "+dir);
		}
		return null;
	}
	
//	TASK: modifies bufferedImages
	
	public static BufferedImage resizeImage(BufferedImage origImg, int IMG_WIDTH, int IMG_HEIGHT){
		try{
			int type = origImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : origImg.getType();
			BufferedImage resizedImg = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
			Graphics2D g = resizedImg.createGraphics();
			g = render(g);
			g.drawImage(origImg, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.setComposite(AlphaComposite.Src);
			g.dispose();
			return resizedImg;
		}catch(Exception e){
			System.err.println("resizeImage method error: "+e.getLocalizedMessage());
		}
		return origImg;
    }
	
	public static BufferedImage rotateImage(BufferedImage origImg, double angle){
		try{
			int type = origImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : origImg.getType();
			double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
			int width = origImg.getWidth(), height = origImg.getHeight();
		    int newWidth = (int)Math.floor(width*cos+height*sin), newHeight = (int)Math.floor(height*cos+width*sin);
		    BufferedImage rotatedImg = new BufferedImage(newWidth, newHeight, type);
			Graphics2D g = rotatedImg.createGraphics();
			g.translate((newWidth-width)/2, (newHeight-height)/2);
			g = render(g);
		    g.rotate(angle, width/2, height/2);
			g.drawRenderedImage(origImg, null);
			g.setComposite(AlphaComposite.Src);
			g.dispose();
			return rotatedImg;
		}catch(Exception e){
			System.err.println("rotateImage method error: "+e.getLocalizedMessage());
		}
		return origImg;
    }
	
	public static BufferedImage changeImgTransparency(BufferedImage origImg, float transparency){
		try{
			int type = origImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : origImg.getType();
			BufferedImage resultImg = new BufferedImage(origImg.getWidth(), origImg.getHeight(), type);
			Graphics2D g = resultImg.createGraphics();
			Composite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
			g.setComposite(ac);
			g = render(g);
			g.drawImage(origImg, 0, 0, origImg.getWidth(), origImg.getHeight(), null);
			g.dispose();
			return resultImg;
		}catch(Exception e){
			System.err.println("transparency error!\ntransparency: "+transparency);
		}
		return origImg;
	}
	
	public static Graphics2D render(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DEFAULT);
		return g;
	}
	
//	Task: Sleep
	public static void delay(double time){
		try{
			Thread.sleep((long)(time*1000), (int)(((time*1000)-(long)(time*1000))*1000000));
		}catch(InterruptedException e){}
	}
	
//	Task: Gives a random number
	
	public static int randomize(int num){
		return (int) (Math.random()*num); 
	}
	
//	Task: Collects heap
	public static void collectGarbage(){
		for(int i = 0; i < 3; System.gc(), i++);
	}
	
//	gives the index of the number in the array
	public static int indexOf(int num, int[] array){
		int index = 0;
		for(; index < array.length && array[index]!=num; index++);
		return index;
	}

//	fade cover
	static ImageIcon coverIcon[] = new ImageIcon[10];
	static Image coverImg[] = new Image[10];
	static boolean fading = false;
	
	static void initCovers(){
		float transparency = 0.1f;
		for(int i = 0; i < 10; i++, transparency+=0.1f){
			coverImg[i] = new Image("images/intro/cover.png", 0, 0);
			coverImg[i].img = changeImgTransparency(coverImg[i].img, transparency);
			coverIcon[i] = new ImageIcon(coverImg[i].img);
		}
	}
	
	static FadeCover fadeCover;
	static void fadeCover(NormalPanel fromPanel, NormalPanel toPanel){
		fadeCover.fromPanel = fromPanel;
		fadeCover.toPanel = toPanel;
		new Thread(fadeCover).start();
	}
	
	static class FadeCover implements Runnable{
		NormalPanel fromPanel, toPanel;
		
		public void run(){
			if(fromPanel!=null)
				fadeInCover(fromPanel.cover);
			if(toPanel != null){
				toPanel.cover.setVisible(true);
				toPanel.showPanel();
			}
			else 
				System.exit(0);
			if(fromPanel!=null)
				fromPanel.hidePanel();
			fadeOutCover(toPanel.cover);
		}
	}
	
	static void fadeInCover(Label cover){
		fading = true;
		cover.getParent().add(cover, 0);
		cover.setVisible(true);
		for(int i = 0; i < 10; Util.delay(0.05), i++)
			cover.setIcon(coverIcon[i]);
		Util.delay(0.1);
	}
	
	static void fadeOutCover(Label cover){
		cover.setVisible(true);
		Util.delay(0.1);
		for(int i = 9; i >= 0; Util.delay(0.05), i--)
			cover.setIcon(coverIcon[i]);
		cover.setVisible(false);
		cover.getParent().add(cover);
		fading = false;
	}
	
	static void showCoverImg(){
		for(int i = 0; i < 10; coverImg[i].setVisible(true), i++);
	}
	
	static void hideCoverImg(){
		for(int i = 0; i < 10; coverImg[i].setVisible(false), i++);
	}
	
//	Music 
	static File file;
	static AudioInputStream audioIn;
	
	static Clip createClip(String sound){
		Clip clip;
		try{
			file = new File("Sounds/"+sound+".wav");
			audioIn = AudioSystem.getAudioInputStream(file);
			
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			return clip;
		}catch(Exception e){
			System.err.println("Can't read: "+sound);
		}
		return null;
	}
	
	static void normalSoundPlay(Clip sound){
		sound.stop();
		sound.setFramePosition(0);
		sound.start();
	}
	
	static void loopSoundPlay(Clip music){
		music.stop();
		music.setFramePosition(0);
		music.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
/*******************************************************************************/
// Necessary for full screen
	public static GraphicsEnvironment env;
	public static GraphicsDevice device;
	public static DisplayMode mode;	
	public static GraphicsConfiguration gc;
	
//	set the values of the dimensions of the screen
	public static void setEnvironmentValues(){
		env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = env.getDefaultScreenDevice();
		gc = device.getDefaultConfiguration();
		mode = new DisplayMode(frameWidth, frameHeight, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		fadeCover = new FadeCover();
	}
	
//	TASK: adjusts the frame size according to the resolution
	public static int frameWidth = 800, frameHeight = 600;

//	TASK: returns a bound;
	public static Rectangle rect(int x, int y, int width, int height){
		return new Rectangle(x, y, width, height);
	}
}