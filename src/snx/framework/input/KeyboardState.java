package snx.framework.input;

import java.util.ArrayList;

/**
 * A state-based representation of the state of the keyboard keys. Because I don't like Java's input system
 */
public class KeyboardState {
	
	//a list of all the keys pressed in the state. If a key is pressed its respective enum will be contained in the key pressed
	protected ArrayList<Keys> pressedKeys;
	
	public KeyboardState(){
		pressedKeys = new ArrayList<Keys>();
	}
	
	public boolean isKeyPressed(Keys i){
		return pressedKeys.contains(i);
	}
	
	public ArrayList<Keys> getPressedKeys(){
		return new ArrayList<Keys>(this.pressedKeys);
	}
	
	public Object clone(){
		KeyboardState toReturn = new KeyboardState();
		toReturn.pressedKeys = new ArrayList<Keys>(this.pressedKeys);
		return toReturn;
	}
}

