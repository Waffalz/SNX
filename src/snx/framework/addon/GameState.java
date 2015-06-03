package snx.framework.addon;

import snx.framework.Game;
import snx.framework.GameTime;

/**
 * By Waffalz
 * Represents different contexts of the game. Subclass this to make your own states
 */
public abstract class GameState {
	private Game parent;
	
	public GameState(Game parent){
		this.parent = parent;
	}
	
	public void initialize(){
		
	}
	
	public void update(GameTime gameTime){
			
	}
	
	public void draw(GameTime gameTime){
		
	}
	
	public void unload(){
		
	}
	
	public Game getParent(){
		return parent;
	}
	
}
