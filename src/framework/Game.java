package framework;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import framework.input.*;


/**
 * By Waffalz
 * The base class for games, including game looping and drawing
 */
public class Game {
	
	public static final int UPDATE_FREQUENCY = 60; //the number of times a game should loop per second
	
	private JFrame window;
	private JPanel contentPane;
	
	private boolean running; //whether the game loop is running
	
	private int frameCount;
	private float frameTime;
	private int frameRate;
	
	public Game(){
		running = false;
		
		//creating the window
		window = new JFrame();
		window.setBounds(100, 100, 1080, 720);
		
		//set contentPane
		contentPane = (JPanel)window.getContentPane();
		
		window.setTitle("SNX");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		Mouse.initialize();
		Mouse.setWindowHandle(window);
		
		Keyboard.initialize();
		Keyboard.setWindowHandle(window);
		
		initialize();
	}
	
	public void initialize(){
		
		
	}
	
	public void run(){
		
		long currentStartTime = System.currentTimeMillis(); //the time of the newest starting of the new iteration
		long lastStartTime = currentStartTime; //represents the last time things were updated
		
		long TIME_GAP = (long)(((float)1)/UPDATE_FREQUENCY*1000);
		
		long timeStartedRunning = currentStartTime;
		
		running = true;
		while(running){
			currentStartTime = System.currentTimeMillis();//reset the start of the new iteration
			
			long timeDifference = currentStartTime - lastStartTime; //time between each iteration of the loop
			//System.out.println(timeDifference);
			
			GameTime time = new GameTime(currentStartTime - timeStartedRunning, timeDifference);//create GameTime
			
			//update once
			update(time);
			
			//TODO: implement draw skipping when needed
			//draw to the screen
			refreshScreen(time);
			
			System.out.println(Keyboard.getState().getPressedKeys());
			
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
			//reset the last tiem the loop restarted
			lastStartTime = currentStartTime;
		}
	}
	
	public void update(GameTime time){
		
	}
	
	//redraws the screen, with double buffering
	public void refreshScreen(GameTime gameTime){
		Image bufferImage = contentPane.createImage(contentPane.getWidth(), contentPane.getHeight());
		Graphics bufferGraphics = bufferImage.getGraphics();
		draw(gameTime);//calls to the draw method
		bufferGraphics.drawString("FPS: "+ frameRate, 50, 50);//draw framerate
		//draw the contents of the buffer to the contentPane
		contentPane.getGraphics().drawImage(bufferImage, 0, 0, contentPane);
		
		frameCount++;//increment frame count
		frameTime += gameTime.getElapsedTime().getTimeSeconds(); //increment the amount of time between fps updates
		
		//refresh the fps counter
		if (frameTime > 1){
			frameTime = 0;
			frameRate = frameCount;
			frameCount = 0;
		}
		
	}
	
	public void draw(GameTime time){
		
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
	
}
