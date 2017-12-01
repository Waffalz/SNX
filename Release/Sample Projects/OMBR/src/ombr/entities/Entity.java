package ombr.entities;

import snx.framework.GameTime;
import snx.framework.graphics.SpriteBatch;

/*
 * Class of objects in the game world to be updated and drawn
 * Not to be confused with the Entity class in snx.framework.addon, because these entities don't need too much stuff
 */
public abstract class Entity {
	
	public abstract void update(GameTime gameTime);
	
	public abstract void draw(GameTime gameTime, SpriteBatch spriteBatch);
	
}
