package framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Keyboard {
private static KeyboardState currentState;
	
	private static JFrame windowHandle;
	private static KeyListener windowHandleListener;
	
	public static void initialize(){
		currentState = new KeyboardState();
		Keys.initialize();
	}
	
	public static KeyboardState getState(){
		return (KeyboardState)currentState.clone();
	}
	
	public static KeyListener getWindowHandleListener(){
		return windowHandleListener;
	}
	
	public static JFrame getWindowHandle(){
		return windowHandle;
	}
	
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
