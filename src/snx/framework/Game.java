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
	
	public boolean numbersVisible;
	private int frameCount;
	private float frameTime;
	private int frameRate;
	
	private ContentManager content;
	
	private BufferedImage imageBuffer;
	private Graphics2D graphicsDevice;
	
	private GameState gameState;
	
	public Game(){
		running = false;
		numbersVisible = false;
		
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
	 * Called when content needs to be loaded. Might need to be called to later, but is mainly used to be overridden by Game1
	 */
	public void loadContent(){
		
	}
	
	/**
	 * Called when content needs to be unloaded. Might need to be called to later, but is mainly used to be overridden by Game1
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
				currentStartTime = System.currentTimeMillis();//reset the start of the new iteration
				
				long timeDifference = currentStartTime - lastStartTime; //time between each iteration of the loop
				//System.out.println(timeDifference);
				
				GameTime time = new GameTime(currentStartTime - timeStartedRunning, timeDifference);//create GameTime
				
				//call to update
				update(time);
				
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
	 * @param time The current GameTime as of the time the method is called
	 */
	public void update(GameTime time){
		
	}
	
	/**
	 * calls to draw, and implements double buffering to prevent screen tearing
	 * also adds a frame rate counter, which is nice
	 * @param gameTime The current GameTime as of the time the method is called
	 */
	public void refreshScreen(GameTime gameTime){
		
		draw(gameTime);//calls to the draw method
		//draw the contents of the buffer to the contentPane
		graphicsDevice.setColor(Color.BLACK);
		if (numbersVisible){
			graphicsDevice.drawString("FPS: "+ frameRate, 0, 10);//draw framerate
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
	 * @param time The current GameTime as of the time the method is called
	 */
	public void draw(GameTime time){
		
	}
	
	/**
	 * Kills the game
	 */
	public void exit(){
		System.exit(0);
	}
	
	//encapsulation hooooooo
	
	/**
	 * Returns the window
	 * @return The JFrame the game is running in
	 */
	public JFrame getWindow(){
		return window;
	}
	
	/**
	 * Returns the content pane of the game window
	 * @return The window's content pane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	
	/**
	 * Returns whether the game is running
	 * @return Whether the game is running
	 */
	public boolean isRunning(){
		return running;
	}
	
	/**
	 * Returns the graphics device the game is drawing on
	 * @return The game's graphics device
	 */
	public Graphics2D getGraphicsDevice(){
		return graphicsDevice;
	}
	
	/**
	 * Returns the content manager that is operating for this instance of the game
	 * @return The game's content manager
	 */
	public ContentManager getContentManager(){
		return content;
	}

	/**
	 * The state of the game
	 * @return The gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * Sets the state of the game
	 * @param newState The GameState to be set to
	 */
	public void setGameState(GameState newState){
		if (newState.getParent() == null){
			if (gameState != null){
				gameState.unload();
			}
			gameState = newState;
			newState.setParent(this);
			newState.initialize();
		} else {
			throw new RuntimeException("Cannot add a GameStart that is already a child to another Game");
		}
	}
	
}
