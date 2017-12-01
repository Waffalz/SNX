package snx.framework.input;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

/**
 * Manages the game's mouse input
 */
public class Mouse {
	private static MouseState currentState = new MouseState(); 
	
	static private JFrame windowHandle;
	static private MouseInputListener windowHandleListener;
	
	/**
	 * Called once when a new Game is created
	 */
	public static void initialize(){
		
	}
	
	/**
	 * Returns the JFrame for which Mouse tracks input
	 * @return The window handle
	 */
	public static JFrame getWindowHandle(){
		return windowHandle;
	}
	
	/**
	 * Returns the MouseListener that responds to the window handle's mouse input
	 * @return The window handle's MouseListener
	 */
	public static MouseInputListener getGetWindowHandleListener(){
		return windowHandleListener;
	}
	
	/**
	 * Sets the window for which MouseState's static mouse control data refers to
	 * @param nextWindow The window handle to set set to
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
					currentState.pos = arg0.getPoint();
				}
	
				@Override
				public void mouseMoved(MouseEvent arg0) {
					currentState.pos = arg0.getPoint();
				}
			
			};
			
			//remove listeners and event stuff from the old value of adaptee
			if (windowHandle != null && windowHandleListener != null){
				windowHandle.getContentPane().removeMouseListener(windowHandleListener);
				windowHandle.getContentPane().removeMouseMotionListener(windowHandleListener);
			}
			
			//set listeners to the new window
			nextWindow.getContentPane().addMouseListener(listener);
			nextWindow.getContentPane().addMouseMotionListener(listener);
			
			//set the new values
			windowHandle = nextWindow;
			windowHandleListener = listener;
		}
	}
	
	/**
	 * Returns the state of the Mouse at the time the method was called
	 * @return The current state of the Mouse
	 */
	public static MouseState getState(){
		return (MouseState)currentState.clone();
	}
	
}
