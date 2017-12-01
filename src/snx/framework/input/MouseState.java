package snx.framework.input;

import java.awt.Point;

/**
 * My way of converting Java's programming to a state-based one
 */
public class MouseState {
	
	protected boolean mouse1Pressed;
	protected boolean mouse2Pressed;
	protected boolean mouse3Pressed;
	
	protected Point pos;
	
	public MouseState(){
		mouse1Pressed = false;
		mouse2Pressed = false;
		mouse3Pressed = false;
		
		pos = new Point(0,0);
	}
	
	/**
	 * Whether the left button is pressed down
	 * @return Whether the left button is pressed down
	 */
	public boolean isMouse1Pressed(){
		return mouse1Pressed;
	}
	
	/**
	 * Whether the right button is pressed down
	 * @return Whether the right button is pressed down
	 */
	public boolean isMouse2Pressed(){
		return mouse2Pressed;
	}
	
	/**
	 * Whether the middle button is pressed down
	 * @return Whether the middle button is pressed down
	 */
	public boolean isMouse3Pressed(){
		return mouse3Pressed;
	}
	
	/**
	 * Returns the position of the mouse
	 * @return The position of the mouse
	 */
	public Point getPosition(){
		return pos;
	}
	
	/**
	 * Creates a copy of the MouseState
	 * @return A copy of the MouseState
	 */
	public Object clone(){
		MouseState toReturn = new MouseState();
		toReturn.mouse1Pressed = mouse1Pressed;
		toReturn.mouse2Pressed = mouse2Pressed;
		toReturn.mouse3Pressed = mouse3Pressed;
		
		toReturn.pos = new Point(pos.x, pos.y);
		
		return toReturn;
	}
	
}
