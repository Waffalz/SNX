package ombr.game;

import java.awt.Color;

import snx.framework.*;
import snx.framework.graphics.*;
import ombr.flow.*;


public class Game1 extends Game {
	
	SpriteBatch spriteBatch;
	
	Color backColor;
	
	public Game1(){
		super();
		getWindow().setTitle("Operation Make BattleField: Redux");
		numbersVisible = true;
	}
	
	/**
	 * Called to once per runtime to initialize values
	 */
	public void initialize(){
		super.initialize();
		//TODO: put initialization code here
		setGameState(new MenuState());
		
		backColor = new Color(101, 156, 239);
	}
	
	/**
	 * Called to once per runtime after initialize to load content necessary to the game
	 */
	public void loadContent(){
		spriteBatch = new SpriteBatch(getGraphicsDevice());
		//TODO: put content loading code here
		
		
	}
	/**
	 * Called to perform logic such as updating the world
	 */
	public void update(GameTime gameTime){
		super.update(gameTime);
		//TODO: put drawing code here
		getGameState().update(gameTime);
		
	} 
	
	/**
	 * Called when the game should draw itself, for graphics and the sort
	 */
	public void draw(GameTime gameTime){
		super.draw(gameTime);
		spriteBatch.clear(backColor);
		//TODO: put drawing code here
		getGameState().draw(gameTime, spriteBatch);
		
	}
	
	
}