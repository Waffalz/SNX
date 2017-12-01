package snx.framework;

import snx.framework.graphics.SpriteBatch;

/**
 * A custom class I made
 * Represents different contexts of the game. Extend this to make your own states.
 */
public abstract class GameState {
	private Game parent;
	
	public GameState(){
		parent = null;
	}
	
	public void initialize(){
		
	}
	
	public void update(GameTime gameTime){
			
	}
	
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		
	} 
	
	public void unload(){
		
	}
	
	public Game getParent(){
		return parent;
	}
	
	protected void setParent(Game parent){
		this.parent = parent;
	}
}
