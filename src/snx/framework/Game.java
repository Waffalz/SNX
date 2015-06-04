package snx.framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import snx.framework.content.ContentManager;
import snx.framework.input.*;


/**
 * By Waffalz
 * The base class for games, including game looping and drawing
 */
public class Game {
	
	public static final int UPDATE_FREQUENCY = 60; //the number of times a game should loop per second
	
	private JFrame window;
	private JPanel contentPane;
	
	private JPanel drawPanel;
	
	private boolean running; //whether the game loop is running
	
	public boolean framerateVisible;
	private int frameCount;
	private float frameTime;
	private int frameRate;
	
	private ContentManager content;
	
	private BufferedImage imageBuffer;
	private Graphics2D graphicsDevice;
	
	public Game(){
		running = false;
		framerateVisible = false;
		
		//creating the window
		window = new JFrame();
		window.setBounds(100, 100, 1080, 720);
		window.setLayout(null);
		
		//set contentPane
		contentPane = (JPanel)window.getContentPane();
		contentPane.setSize(window.getSize());
		contentPane.setLayout(null);
		
		//set the panel for drawing things to
		drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBounds(contentPane.getBounds());
		contentPane.add(drawPanel);
		
		window.setTitle("SNX");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		imageBuffer = (BufferedImage) drawPanel.createImage(drawPanel.getWidth(), drawPanel.getHeight());
		graphicsDevice = (Graphics2D)imageBuffer.getGraphics();
		graphicsDevice.setClip(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
		
		content = new ContentManager();
		
		Mouse.initialize();
		Mouse.setWindowHandle(window);
		
		Keyboard.initialize();
		Keyboard.setWindowHandle(window);
	}
	
	/**
	 * Called to start up important game things, separate from the constructor
	 */
	public void initialize(){
		
		
		loadContent();
	}
	
	/**
	 * 
	 */
	public void loadContent(){
		
	}
	
	/**
	 * 
	 */
	public void unloadContent(){
		
	}
	
	/**
	 * Starts the game loop
	 */
	public void run(){
		
		if (running == false){
			
			initialize();
			
			long currentStartTime = System.currentTimeMillis(); //the time of the newest starting of the new iteration
			long lastStartTime = currentStartTime; //represents the last time things were updated
			
			long TIME_GAP = (long)(((float)1)/UPDATE_FREQUENCY*1000);
			
			final long timeStartedRunning = currentStartTime;
			
			running = true;
			while(running){
				//TODO: put the game loop in a separate Tick method
				currentStartTime = System.currentTimeMillis();//reset the start of the new iteration
				
				long timeDifference = currentStartTime - lastStartTime; //time between each iteration of the loop
				//System.out.println(timeDifference);
				
				GameTime time = new GameTime(currentStartTime - timeStartedRunning, timeDifference);//create GameTime
				
				//call to update
				update(time);
				
				//TODO: if update takes longer than it should, record overtime and update when accumulated overtime adds up
				
				//TODO: implement draw skipping when needed
				//draw to the screen
				refreshScreen(time);
				
				//the amount of time it took to update and/or draw
				long updateTime = System.currentTimeMillis() - currentStartTime;
				
				//the difference between the maximum update time and updateTime
				long timeLeft = TIME_GAP - updateTime;
				
				if (timeLeft > 0) {
					try{
						Thread.sleep(timeLeft);//wait out the remainder of the time
					} catch (Exception e){
						System.out.println("IT'S A TRAP!");
						e.printStackTrace();
					}
				}
				//reset the last time the loop restarted
				lastStartTime = currentStartTime;
			}
		} else {
			//TODO: throw an exception if game is already running
		}
	}
	
	/**
	 * Called to a number of times every second to maintain... stuff
	 * Overridden by subclasses to do their own update things
	 */
	public void update(GameTime time){
		
	}
	
	/**
	 * calls to draw, and implements double buffering to prevent screen tearing
	 * also adds a framerate counter, which is nice
	 */
	public void refreshScreen(GameTime gameTime){
		
		draw(gameTime);//calls to the draw method
		//draw the contents of the buffer to the contentPane
		graphicsDevice.setColor(Color.BLACK);
		if (framerateVisible){
			graphicsDevice.drawString("FPS: "+ frameRate, 20, 20);//draw framerate
		}
		
		drawPanel.getGraphics().drawImage(imageBuffer, 0, 0, drawPanel);//draw the graphics buffer to drawPanel
		
		
		frameTime += gameTime.getElapsedTime().getTimeSeconds(); //increment the amount of time between fps updates
		
		//refresh the fps counter after a second has elapsed
		if (frameTime > 1){
			frameTime = 0;
			frameRate = frameCount;
			frameCount = 0;
		}
		
		frameCount++;//increment frame count
		
		
	}
	
	/**
	 * called to when graphics are to be handled
	 * 
	 */
	public void draw(GameTime time){
		
	}
	
	public void exit(){
		System.exit(0);
	}
	
	//properties hoooooo
	
	public JFrame getWindow(){
		return window;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public Graphics2D getGraphicsDevice(){
		return graphicsDevice;
	}
	
	public ContentManager getContentManager(){
		return content;
	}
	
}
