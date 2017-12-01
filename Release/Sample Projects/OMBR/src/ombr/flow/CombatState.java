package ombr.flow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import snx.framework.GameState;
import snx.framework.GameTime;
import snx.framework.graphics.SpriteBatch;
import snx.framework.input.Keyboard;
import snx.framework.input.KeyboardState;
import snx.framework.input.Keys;
import snx.framework.ui.UIComponent;

public class CombatState extends GameState {
	
	private boolean playing;//whether the main game part should be looping
	private boolean paused;//whether the pause menu is up
	private boolean shopping;//whther the shop is up
	
	private UIComponent shopFrame; //the window for the shop
	private UIComponent pauseMenu; //the window for pausing the game
	
	private KeyboardState oKipz; //the state of the keyboard during the most recent call to update
	
	
	
	public CombatState() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		Dimension screenSize = getParent().getContentPane().getSize();
		
		shopFrame = new UIComponent();
		shopFrame.setSize(new Dimension(500, 500));
		shopFrame.setPosition(new Point(screenSize.width/2 - shopFrame.getSize().width/2, 100));
		shopFrame.setDepth(1);
		shopFrame.setVisible(false);
		
		//TODO: implement shop buttons
		
		pauseMenu = new UIComponent();
		pauseMenu.setSize(screenSize);
		pauseMenu.setColor(new Color(0, 0, 0, 100));
		pauseMenu.setBorderSize(0);
		pauseMenu.setDepth(0);
		pauseMenu.setVisible(false);
		
		paused = false;
		shopping = false;
		
		oKipz = Keyboard.getState();
		
		playing = true;
	}
	
	public void update(GameTime gameTime){
		KeyboardState kipz = Keyboard.getState();
		
		if (playing){
			
			updateWorld(gameTime, kipz);
			
			if (kipz.isKeyPressed(Keys.Escape) && !oKipz.isKeyPressed(Keys.Escape)){
				paused = true;
				playing = false;
				pauseMenu.setVisible(true);
				
			} else {
				if (kipz.isKeyPressed(Keys.Tab) && !oKipz.isKeyPressed(Keys.Tab)){
					shopping = true;
					playing = false;
					shopFrame.setVisible(true);
					
				}
				
			}
			
		} else {
		
			if (paused){
				updatePauseMenu(gameTime, kipz);
				
			}
			
			if (shopping){
				updateShopMenu(gameTime, kipz);
				
			}
		}
		
		oKipz = kipz;
		
	}
	
	public void updateWorld(GameTime gameTime, KeyboardState kipz){
		
	}
	
	//updating the shop menu
	public void updateShopMenu(GameTime gameTime, KeyboardState kipz){
		if ((kipz.isKeyPressed(Keys.Escape) && !oKipz.isKeyPressed(Keys.Escape))
				|| (kipz.isKeyPressed(Keys.Tab) && !oKipz.isKeyPressed(Keys.Tab))){
			shopping = false;
			playing = true;
			shopFrame.setVisible(false);
		}
		
	}
	
	//updating the pause menu
	public void updatePauseMenu(GameTime gameTime, KeyboardState kipz){
		if (kipz.isKeyPressed(Keys.Escape) && !oKipz.isKeyPressed(Keys.Escape)){
			paused = false;
			playing = true;
			pauseMenu.setVisible(false);
		}
		
	}
	
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		spriteBatch.clear(new Color(255, 20, 20));
		
		shopFrame.draw(gameTime, spriteBatch);
		pauseMenu.draw(gameTime, spriteBatch);
	}
	
	public void unload(){
		
	}
}
