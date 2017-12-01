package snx.framework.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.util.ArrayList;

import snx.framework.GameTime;
import snx.framework.Vector2;
import snx.framework.graphics.SpriteBatch;
import snx.framework.input.Mouse;
import snx.framework.input.MouseState;

public class UIButton extends UIComponent {
	
	public static final int HOVER_DISPLACEMENT = 4; //the number of pixels the button will distort when hovered over by the mouse

	private Font font;
	private String text;
	private Color textColor;
	
	private HorizontalTextAlignment hAlign;
	private VerticalTextAlignment vAlign;
	
	private ArrayList<Runnable> listeners;
	
	private boolean mouseOver;
	private boolean pressed;
	
	private MouseState oMouse;
	
	private ArrayList<Runnable> addListenerList;//a queue for listener Runnables to add when not iterating through listeners
	private ArrayList<Runnable> removeListenerList;//a queue for listener Runnables to remove when not iterating through listeners
	
	public UIButton() {
		super();
		font = new Font("Lucida Sans Regular", Font.PLAIN, 20);
		text = "";
		textColor = Color.white;
		
		hAlign = HorizontalTextAlignment.Center;
		vAlign = VerticalTextAlignment.Center;
		
		listeners = new ArrayList<Runnable>();
		addListenerList = new ArrayList<Runnable>();
		removeListenerList = new ArrayList<Runnable>();
		
		mouseOver = false;
		pressed = false;
		
		oMouse = Mouse.getState();
	}
	
	/**
	 * Updates the button and checks for mouse interaction
	 * @param gameTime The time of the game at which update is called
	 */
	public void update(GameTime gameTime){
		super.update(gameTime);
		MouseState mouse = Mouse.getState();
		
		//mouseOver controls when the button sees that the mouse is hovering over the button when it is enabled
		if (isEnabled() &&  isVisible()){
			mouseOver = (getDefiniteBounds().contains(mouse.getPosition()));//if mouse is within the bounds of the button
			pressed = (mouseOver && mouse.isMouse1Pressed());
			
			//activate listeners when mouse is released over the button 
			if (mouseOver && !mouse.isMouse1Pressed() && oMouse.isMouse1Pressed()){
				for (Runnable ru : listeners){
					ru.run();
				}
			}
		}
		
		oMouse = mouse;
		addListenersInQueue();
		removeListenersInQueue();
	}
	
	/**
	 * Draws the button and its children
	 * @param gameTime The time of the game at which draw is called
	 * @param spriteBatch The SpriteBatch to draw the component
	 */
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		if (isVisible()){
			Rectangle defBounds = getDefiniteBounds();
			if (isEnabled()){
				if (mouseOver){
					Color col = getColor();
					if (pressed){//darken the displayed color if mouse is pressed over the button
						col = col.darker();
					}
					//decrease the size of the button if mouse is hovering over
					Rectangle bBounds = new Rectangle(defBounds.x + HOVER_DISPLACEMENT, defBounds.y + HOVER_DISPLACEMENT,
							defBounds.width - HOVER_DISPLACEMENT * 2, defBounds.height - HOVER_DISPLACEMENT * 2);
					drawBackground(spriteBatch, bBounds, col, getBorderColor());
					drawText(spriteBatch, textColor);
				} else {
					//draw default state of the button
					drawBackground(spriteBatch, defBounds, getColor(), getBorderColor());
					drawText(spriteBatch, textColor);
				}
			} else {
				//gray out the button if not enabled
				drawBackground(spriteBatch, defBounds, getColor().brighter(), getBorderColor().brighter());
				drawText(spriteBatch, textColor.brighter());
			}
			
			drawChildren(gameTime, spriteBatch);
		}
		
	}
	
	public void addListener(Runnable listener){
		if (!addListenerList.contains(listener) && !listeners.contains(listener)){
			addListenerList.add(listener);
		}
	}
	
	public void removeListener(Runnable listener){
		if (!removeListenerList.contains(listener) && listeners.contains(listener)){
			removeListenerList.add(listener);
		}
	}
	
	private void addListenersInQueue(){
		for (Runnable toAdd : addListenerList){
			listeners.add(toAdd);
		}
		addListenerList.clear();
	}
	
	private void removeListenersInQueue(){
		for (Runnable toRemove : removeListenerList){
			listeners.remove(toRemove);
		}
		removeListenerList.clear();
	}
	
	//Stuff copied from UILabel. UIButton and UILabel are technically different so I'm gonna keep them of separate inheritance from each other
	
	private void drawText(SpriteBatch spriteBatch, Color col){
		FontMetrics metrics = spriteBatch.getGraphicsDevice().getFontMetrics(font);
		String[] lines = text.split("\n");
		
		Rectangle defBounds = getDefiniteBounds();
		
		int maxHeight = metrics.getHeight() * lines.length;
		for (int i = 0; i < lines.length; i++){
			Vector2 drawPos = new Vector2();
			
			//adjust x position of text according to the width and hAlignment of the text
			//not explaining the math behind it because it will be pain
			switch (hAlign){
			case Left:
				drawPos.x = defBounds.x;
				break;
			case Center:
				drawPos.x = defBounds.x + defBounds.width / 2 - metrics.stringWidth(lines[i])/2;
				break;
			case Right:
				drawPos.x = defBounds.x + defBounds.width - metrics.stringWidth(lines[i]);
				break;
			default:
				drawPos.x = defBounds.x + defBounds.width / 2 - metrics.stringWidth(lines[i])/2;
			}
			
			//adjust y position of text according to the width and vAlignment of the text
			//not explaining the math behind it because it will be pain
			switch (vAlign){
			case Top:
				drawPos.y = defBounds.y;
				break;
			case Center:
				drawPos.y = defBounds.y + defBounds.height / 2 + ((i) * metrics.getHeight()) - maxHeight / 2;
				break;
			case Bottom:
				drawPos.y = defBounds.y + defBounds.height + ((i) * metrics.getHeight()) - maxHeight;
				break;
			default:
				drawPos.y = defBounds.y + defBounds.height / 2 + ((i) * metrics.getHeight()) - maxHeight / 2;
			}
			spriteBatch.drawString(font, lines[i], drawPos, col);
		}
		
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the textColor
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the hAlign
	 */
	public HorizontalTextAlignment gethAlign() {
		return hAlign;
	}

	/**
	 * @param hAlign the hAlign to set
	 */
	public void sethAlign(HorizontalTextAlignment hAlign) {
		this.hAlign = hAlign;
	}

	/**
	 * @return the vAlign
	 */
	public VerticalTextAlignment getvAlign() {
		return vAlign;
	}

	/**
	 * @param vAlign the vAlign to set
	 */
	public void setvAlign(VerticalTextAlignment vAlign) {
		this.vAlign = vAlign;
	}
}
