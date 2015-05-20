package framework.input;

import java.awt.Point;

/**
 * By Waffalz
 * My way of converting Java's crappy event-driven programming to a much more manageable state-based one
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
	
	public boolean isMouse1Pressed(){
		return mouse1Pressed;
	}
	public boolean isMouse2Pressed(){
		return mouse2Pressed;
	}
	public boolean isMouse3Pressed(){
		return mouse3Pressed;
	}
	
	public Point getPosition(){
		return pos;
	}
	
	/**
	 * Override Object clone
	 */
	public Object clone(){
		MouseState toReturn = new MouseState();
		toReturn.mouse1Pressed = mouse1Pressed;
		toReturn.mouse2Pressed = mouse2Pressed;
		toReturn.mouse3Pressed = mouse3Pressed;
		
		toReturn.pos = (Point)pos.clone();
		
		return toReturn;
	}
	
}
