package framework.input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

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

