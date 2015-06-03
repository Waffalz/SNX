package snx.framework.addon;

import java.awt.Rectangle;

import snx.framework.Vector2;

/**
 * By Waffalz
 * A convenience class for keeping track of positioning
 */
public class Entity {
	private Vector2 position;
	private Vector2 size;
	private Vector2 velocity;
	
	public Entity(){
		position = new Vector2();
		setSize(new Vector2());
		setVelocity(new Vector2());
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void setPosition(Vector2 newPos){
		position = newPos;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)position.x, (int)position.y, (int)size.x, (int)size.y);
	}
}
