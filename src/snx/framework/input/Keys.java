package snx.framework.input;

import static java.awt.event.KeyEvent.*;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Enum that represents keyboard keys.
 * I'm updating documentation for this two years after I made it and even new I remember how painful typing this was.
 */
public enum Keys{
	
	//letter keys
	A("A", VK_A), 
	B("B", VK_B), 
	C("C", VK_C), 
	D("D", VK_D), 
	E("E", VK_E), 
	F("F", VK_F), 
	G("G", VK_G), 
	H("H", VK_H), 
	I("I", VK_I), 
	J("J", VK_J), 
	K("K", VK_K), 
	L("L", VK_L), 
	M("M", VK_M), 
	N("N", VK_N), 
	O("O", VK_O), 
	P("P", VK_P), 
	Q("Q", VK_Q), 
	R("R", VK_R), 
	S("S", VK_S), 
	T("T", VK_T), 
	U("U", VK_U), 
	V("V", VK_V), 
	W("W", VK_W), 
	X("X", VK_X), 
	Y("Y", VK_Y),
	Z("Z", VK_Z),
	
	Semicolon("Semicolon", VK_SEMICOLON),
	Comma("Comma", VK_COMMA, KEY_LOCATION_STANDARD),
	Period("Period", VK_PERIOD, KEY_LOCATION_STANDARD),
	Underscore("Underscore", VK_UNDERSCORE),
	
	//Number keys at the top of the keyboard
	D1("D1", VK_1), 
	D2("D2", VK_2), 
	D3("D3", VK_3), 
	D4("D4", VK_4), 
	D5("D5", VK_5), 
	D6("D6", VK_6), 
	D7("D7", VK_7), 
	D8("D8", VK_8), 
	D9("D9", VK_9), 
	D0("D0", VK_0),
	
	//directional keys
	Left("Left", VK_LEFT), 
	Right("Right", VK_RIGHT), 
	Up("Up", VK_UP), 
	Down("Down", VK_DOWN),
	
	//you know, f keys
	F1("F1", VK_F1), 
	F2("F2", VK_F2), 
	F3("F3", VK_F3), 
	F4("F4", VK_F4), 
	F5("F5", VK_F5), 
	F6("F6", VK_F6), 
	F7("F7", VK_F7), 
	F8("F8", VK_F8), 
	F9("F9", VK_F9), 
	F10("F10", VK_F10), 
	F11("F11", VK_F11), 
	F12("F12", VK_F12), 
	F13("F13", VK_F13), 
	F14("F14", VK_F14), 
	F15("F15", VK_F15), 
	F16("F16", VK_F16), 
	F17("F17", VK_F17), 
	F18("F18", VK_F18), 
	F19("F19", VK_F19), 
	F20("F20", VK_F20), 
	F21("F21", VK_F21), 
	F22("F22", VK_F22), 
	F23("F23", VK_F23), 
	F24("F24", VK_F24), 
	
	//numpad keys
	NumPad0("NumPad 0", VK_NUMPAD0, KEY_LOCATION_NUMPAD), 
	NumPad1("NumPad 1", VK_NUMPAD1, KEY_LOCATION_NUMPAD), 
	NumPad2("NumPad 2", VK_NUMPAD2, KEY_LOCATION_NUMPAD), 
	NumPad3("NumPad 3", VK_NUMPAD3, KEY_LOCATION_NUMPAD), 
	NumPad4("NumPad 4", VK_NUMPAD4, KEY_LOCATION_NUMPAD), 
	NumPad5("NumPad 5", VK_NUMPAD5, KEY_LOCATION_NUMPAD), 
	NumPad6("NumPad 6", VK_NUMPAD6, KEY_LOCATION_NUMPAD), 
	NumPad7("NumPad 7", VK_NUMPAD7, KEY_LOCATION_NUMPAD), 
	NumPad8("NumPad 8", VK_NUMPAD8, KEY_LOCATION_NUMPAD), 
	NumPad9("NumPad 9", VK_NUMPAD9, KEY_LOCATION_NUMPAD),
	
	//numpad operators
	NumPadEnter("NumPad Enter", VK_ENTER, KEY_LOCATION_NUMPAD),
	
	//Math operations keys
	Add("Add", VK_ADD), 
	Subtract("Subtract", VK_SUBTRACT), 
	Multiply("Multiply", VK_MULTIPLY), 
	Divide("Divide", VK_DIVIDE), 
	
	Equals("=", VK_EQUALS), //the "=" key
	
	//Forward slash("/"+ and Back Slash("\")(I'M REALLY FEELING IT) keys, respectively.
	Slash("/", VK_SLASH),
	BackSlash("\"", VK_BACK_SLASH), 
	
	//"[" and "]"
	OpenBracket("[", VK_OPEN_BRACKET),
	CloseBracket("]", VK_CLOSE_BRACKET), 
	
	//Alt keys
	RightAlt("Right Alt", VK_ALT, KEY_LOCATION_RIGHT), 
	LeftAlt("Left Alt", VK_ALT, KEY_LOCATION_LEFT),
	
	//Shift buttons
	RightShift("Right Shift", VK_SHIFT, KEY_LOCATION_RIGHT),
	LeftShift("Left Shift", VK_SHIFT, KEY_LOCATION_LEFT), 
	
	//Ctrl keys
	RightControl("Right Control", VK_CONTROL, KEY_LOCATION_RIGHT),
	LeftControl("Left Control", VK_CONTROL, KEY_LOCATION_LEFT), 
	
	Context("Context Menu", VK_CONTEXT_MENU),
	
	//Windows keys
	RightWindows("Right Windows", VK_WINDOWS, KEY_LOCATION_RIGHT),
	LeftWindows("Left Windows", VK_WINDOWS, KEY_LOCATION_LEFT), 
	
	//PageUp and PageDown keys. Nuff said.
	PageUp("Page Up", VK_PAGE_UP),
	PageDown("Page Down", VK_PAGE_DOWN), 
	
	Tab("Tab", VK_TAB), //TAB key
	Space("Space Bar", VK_SPACE), //Spacebar
	Enter("Enter", VK_ENTER), //ENTER key
	Escape("Escape", VK_ESCAPE), //ESC key
	
	Select(), //SELECT key

	Back("Backspace", VK_BACK_SPACE), //BACKSPACE key
	Decimal("Decimal", VK_DECIMAL), //Decimal key
	End("End", VK_END), //END key
	Insert("Insert", VK_INSERT), //INS key
	Home("Home", VK_HOME), //Home key
	Delete("Delete", VK_DELETE), //DEL key
	Help("Help", VK_HELP), //HELP key
	PrintScreen("Print Screen", VK_PRINTSCREEN), //PRINT SCREEN key

	CapsLock("Caps Lock", VK_CAPS_LOCK), //CAPS LOCK key
	NumLock("Num Lock", VK_NUM_LOCK), //NUM LOCK key
	Scroll("Scroll Lock", VK_SCROLL_LOCK), //SCROLL LOCK key
	
	None("None", 0);  //Reserved
	
	private String description;
	
	private int keyCode;
	private int keyLocation;
	
	/**
	 * List of keys grouped by key location, then identified by key ID
	 */
	public static final HashMap<Integer, HashMap<Integer, Keys>> keyMap = new HashMap<Integer, HashMap<Integer, Keys>>();
	
	Keys(){
		description = "";
		keyCode = 0;
		keyLocation = KEY_LOCATION_STANDARD;
	}
	
	Keys(int code){
		description = "";
		keyCode = code;
		keyLocation = KEY_LOCATION_STANDARD;
	}
	
	Keys(int code, int loc){
		description = "";
		keyCode = code;
		keyLocation = loc;
	}
	
	Keys(String newName, int code){
		description = newName;
		keyCode = code;
		keyLocation = KEY_LOCATION_STANDARD;
	}
	
	Keys(String newName, int code, int loc){
		description = newName;
		keyCode = code;
		keyLocation = loc;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getKeyCode(){
		return keyCode;
	}
	
	public int getKeyLocation(){
		return keyLocation;
	}
	
	/**
	 * Initializes the keyLocations Hashmap and assigns key values to it
	 */
	public static void initialize(){
		Keys[] allKeys = Keys.values();
		
		for (Keys key : allKeys){
			//get keys' locations
			int lo = key.getKeyLocation();
			Integer loI = new Integer(lo);
			
			if (keyMap.containsKey(loI)){//if there is already a hashmap keyed to that key location
				keyMap.get(loI).put(new Integer(key.getKeyCode()), key);//add the key to the preexisting hashmap
			} else {//else if there is no hashmap yet made for that location
				HashMap<Integer, Keys> locationHash = new HashMap<Integer, Keys>();//create a new hashmap
				locationHash.put(new Integer(key.getKeyCode()), key);//add the key to the hashmap
				keyMap.put(loI, locationHash);//add the hashmap to keyLocations, keyed to the keyMap
			}
			
		}
		
	}
	
	/**
	 * Converts a java.awt KeyEvent into an SNX Key
	 * @param e The KeyEvent to convert
	 * @return The corresponding Key
	 */
	public static Keys getKeyFromKeyEvent(KeyEvent e){
		//return the key in the hashmap of keys, organized by locations
		return keyMap.get(new Integer(e.getKeyLocation())).get(new Integer(e.getKeyCode()));
		
	}
	
	/**
	 * Returns the String representation of a Key
	 * @return The description of the Key
	 */
	public String toString(){
		return description;
	}
}