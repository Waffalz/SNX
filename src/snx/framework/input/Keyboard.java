package snx.framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Handles converting keyboard input into KeybordState form
 */
public class Keyboard {
	
	private static KeyboardState currentState = new KeyboardState();
	
	private static JFrame windowHandle;
	private static KeyListener windowHandleListener;
	
	/**
	 * Calls to initialize for Keys
	 */
	public static void initialize(){
		Keys.initialize();
	}
	
	/**
	 * Gets the current state of the keyboard
	 * @return The current KeyboardState
	 */
	public static KeyboardState getState(){
		return (KeyboardState)currentState.clone();
	}
	
	/**
	 * Returns the KeyListener that responds to the windowHandle's KeyEvents
	 * @return The window handle's KeyListener
	 */
	public static KeyListener getWindowHandleListener(){
		return windowHandleListener;
	}
	
	/**
	 * Returns the JFrame that the Keyboard is recording input for
	 * @return The current windowHandle
	 */
	public static JFrame getWindowHandle(){
		return windowHandle;
	}
	
	/**
	 * Sets sets the JFrame for which the Keyboard listens to input for 
	 * @param newHandle The new handle
	 */
	public static void setWindowHandle(JFrame newHandle){
		//TODO: add listeners, implement key listeners, set currentState's values
		
		KeyListener nListener = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				//add the keyevent's Keys equivalent to the list of pressed keys when the key is pressed
				//System.out.println("\n"+ KeyEvent.getKeyText(e.getKeyCode()));
				//System.out.println(e.getKeyCode() + " " + e.getKeyLocation());
				Keys key = Keys.getKeyFromKeyEvent(e);
				if (!currentState.pressedKeys.contains(key) && key!= Keys.None){
					currentState.pressedKeys.add(key);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//remove the keyevent's Keys equivalent from the list of pressed keys when the key is released
				Keys key = Keys.getKeyFromKeyEvent(e);
				if (currentState.pressedKeys.contains(key) && key!= Keys.None){
					currentState.pressedKeys.remove(key);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		};
		
		//disconnecting old listeners
		if (windowHandle != null && windowHandleListener != null){
			windowHandle.removeKeyListener(windowHandleListener);
		}
		
		//add the listener
		newHandle.addKeyListener(nListener);
		
		//set new values
		windowHandle = newHandle;
		windowHandleListener = nListener;
		
		windowHandle.setFocusTraversalKeysEnabled(false);
		
	}
	
	
}
