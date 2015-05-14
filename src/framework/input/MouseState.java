package framework.input;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * By Waffalz
 * My way of converting Java's crappy event-driven programming to a much more manageable state-based one
 */
public class MouseState {
	
	private static MouseState currentState; 
	
	private boolean mouse1Pressed;
	private boolean mouse2Pressed;
	private boolean mouse3Pressed;
	
	Point pos;
	
	static private JFrame windowHandle;
	static private MouseInputListener windowHandleListener;
	
	public MouseState(){
		mouse1Pressed = false;
		mouse2Pressed = false;
		mouse3Pressed = false;
		
		pos = new Point(0,0);
	}
	
	/**
	 * Called once when a new Game 
	 */
	public static void initialize(){
		currentState = new MouseState();
		
	}
	
	public static JFrame getWindowHandle(){
		return windowHandle;
	}
	
	public static MouseInputListener getGetWindowHandleListener(){
		return windowHandleListener;
	}
	
	/**
	 * Sets the window for which MouseState's static mouse control data refers to
	 */
	public static void setWindowHandle(JFrame nextWindow){
		if (nextWindow != windowHandle){
			MouseInputListener listener = new MouseInputListener(){
	
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
				}
	
				@Override
				public void mouseEntered(MouseEvent arg0) {
					
				}
	
				@Override
				public void mouseExited(MouseEvent arg0) {
					
				}
	
				@Override
				public void mousePressed(MouseEvent arg0) {
					switch (arg0.getButton()){
					case MouseEvent.BUTTON1:
						currentState.mouse1Pressed = true;
					break;
					case MouseEvent.BUTTON2:
						currentState.mouse2Pressed = true;
						break;
					case MouseEvent.BUTTON3:
						currentState.mouse3Pressed = true;
						break;
					default:
						
					}
				}
	
				@Override
				public void mouseReleased(MouseEvent arg0) {
					switch (arg0.getButton()){
					case MouseEvent.BUTTON1:
						currentState.mouse1Pressed = false;
					break;
					case MouseEvent.BUTTON2:
						currentState.mouse2Pressed = false;
						break;
					case MouseEvent.BUTTON3:
						currentState.mouse3Pressed = false;
						break;
					default:
						
					}
				}
	
				@Override
				public void mouseDragged(MouseEvent arg0) {
					
				}
	
				@Override
				public void mouseMoved(MouseEvent arg0) {
					currentState.pos = arg0.getPoint();
				}
			
			};
			
			//remove listeners and event stuff from the old value of adaptee
			if (windowHandle != null){
				windowHandle.removeMouseListener(windowHandleListener);
				windowHandle.removeMouseMotionListener(windowHandleListener);
			}
			
			//set listeners to the new window
			nextWindow.getContentPane().addMouseListener(listener);
			nextWindow.addMouseMotionListener(listener);
			
			//set the new values
			windowHandle = nextWindow;
			windowHandleListener = listener;
		}
	}
	
	public static MouseState getState(){
		return (MouseState)currentState.clone();
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
