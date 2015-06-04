package snx.framework.template;

import java.awt.Color;

import snx.framework.*;
import snx.framework.graphics.*;

public class Game1 extends Game {
	
	SpriteBatch spriteBatch;
	
	public Game1(){
		super();
	}
	
	/**
	 * Called to once per runtime to initialize values
	 */
	public void initialize(){
		super.initialize();
		//TODO: put initialization code here
		
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
		
	}
	
	/**
	 * Called when the game should draw itself, for graphics and the sort
	 */
	public void draw(GameTime gameTime){
		super.draw(gameTime);
		spriteBatch.clear(Color.white);
		//TODO: put drawing code here
		
	}
}