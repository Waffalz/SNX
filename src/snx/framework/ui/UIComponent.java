package snx.framework.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import snx.framework.GameTime;
import snx.framework.graphics.SpriteBatch;

/**
 * By Waffalz
 * So using traditional Swing doesn't work with SNX, I'm gonna have to reinvent the wheel 
 * to suit SNX's needs. *sigh*
 * 
 */
public class UIComponent {
	
	private UIComponent parent;
	private ArrayList<UIComponent> children;
	
	private Rectangle bounds;
	
	private int depth;
	
	private boolean visible;
	private boolean enabled;
	
	private BufferedImage image;
	
	private Color color;
	private Color borderColor;
	
	private float borderSize;
	
	private ArrayList<UIComponent> addList;//a queue for UIComponents to add when not iterating through children
	private ArrayList<UIComponent> removeList;//a queue for UIComponents to remove when not iterating through children
	
	
	public UIComponent() {
		// TODO Auto-generated constructor stub
		parent = null;
		children = new ArrayList<UIComponent>();
		
		bounds = new Rectangle();
		
		visible = true;
		enabled = true;
		
		borderSize = 3;
		
		color = new Color(101, 156, 239);
		borderColor = Color.WHITE;
		
		addList = new ArrayList<UIComponent>();
		removeList = new ArrayList<UIComponent>();
	}
	
	/**
	 * Calls whenever the state of the component needs to be updated
	 * @param gameTime The time of the game at which update is called
	 */
	public void update(GameTime gameTime){
		processChildren(gameTime);
	}
	
	/**
	 * Draws the component and its children
	 * @param gameTime The time of the game at which draw is called
	 * @param spriteBatch The SpriteBatch to draw the component
	 */
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		
		if (visible){
			Rectangle defBounds = getDefiniteBounds();
			drawBackground(spriteBatch, defBounds, color, borderColor);
			drawChildren(gameTime, spriteBatch);
		}
		
	}

	/**
	 * Draws the background of the component
	 * @param spriteBatch The SpriteBatch to draw to
	 */
	protected void drawBackground(SpriteBatch spriteBatch, Rectangle dest, Color col, Color bCol) {
		
		if (image != null){
			spriteBatch.draw(image, dest, col);
		} else {
			spriteBatch.drawFillRectangle(dest, col);
			spriteBatch.drawRectangle(dest, borderSize, bCol);
		}
	}
	
	/**
	 * puts toAdd in queue to be added to this' children when not iterating through children
	 * @param toAdd The UIComponent to add
	 */
	public void add(UIComponent toAdd){
		//adding toAdd as a child while it is already a child to another uIComponent would generate issues. Like, a lot of them.
		if (toAdd.getParent() != null) throw new RuntimeException("Cannot add a UIComponent that is already a child of another.");
		
		//if you add UIComponent A to UIComponent B when B is already A's parent, you'd have infinite recursion through
		//the circular hierarchy. That's also incestuous, which is gross.
		if (parent == toAdd) throw new RuntimeException("Cannot add a UIComponent\'s parent as a child. That would be incest.");
		
		if(!addList.contains(toAdd) && !children.contains(toAdd)){
			addList.add(toAdd);
		}
		
	}
	
	/**
	 * puts toRemove in queue to be removed from this' children when not iterating through children
	 * @param toRemove The component to be removed
	 */
	public void remove(UIComponent toRemove){
		if (!removeList.contains(toRemove) && children.contains(toRemove)){
			removeList.add(toRemove);
		}
	}

	/**
	 * updates children and handles add/removal queues
	 * @param gameTime The GameTime for that update cycle
	 */
	protected void processChildren(GameTime gameTime) {

		boolean hasChanged = (!addList.isEmpty() || !removeList.isEmpty());
		//update queues
		addChildrenInQueue();
		removeChildrenInQueue();
		
		if (hasChanged){ //if the contents of children has changed
			Collections.sort(children, new Comparator<UIComponent>(){
				@Override
				public int compare(UIComponent sub, UIComponent comp) {
					return (new Integer(sub.depth).compareTo(new Integer(comp.depth)));
				}
			}); //sort children based on depth
		}
		
		updateChildren(gameTime);
	}
	
	/**
	 * Traverses through the component's children and draws the children
	 * @param gameTime The GameTime of the draw cycle
	 * @param spriteBatch The SpriteBatch to be drawn to
	 */
	protected void drawChildren(GameTime gameTime, SpriteBatch spriteBatch) {
		for (UIComponent child : children){
			child.draw(gameTime, spriteBatch);
		}
	}
	
	/**
	 * Traverses through the component's children and updates them
	 * @param gameTime The time of the game
	 */
	private void updateChildren(GameTime gameTime){
		for (UIComponent child : children){
			child.update(gameTime);
		}
	}
	
	/**
	 * Adds all UIComponents in the addList queue to children
	 */
	private void addChildrenInQueue(){
		for (UIComponent toAdd : addList){
			children.add(toAdd);
			toAdd.setParent(this);
		}
		addList.clear();
	}
	
	/**
	 * Removes all UIComponents in the removeList queue from children
	 */
	private void removeChildrenInQueue(){
		for (UIComponent toRemove : removeList){
			children.remove(toRemove);
			toRemove.setParent(null);
		}
		removeList.clear();
	}
	
	// Because your code isn't professional until you have a buttload of nearly excessive encapsulation
	
	/**
	 * Returns the position of the component
	 * @return The Position of the component relative to its parent
	 */
	public Point getPosition() {
		return bounds.getLocation();
	}
	
	/**
	 * Sets the position of the component
	 * @param position the new position of the component
	 */
	public void setPosition(Point position) {
		this.bounds.setLocation(position);;
	}
	
	/**
	 * returns the position of the component relative to 0,0 of the game's window's contentPane
	 * @return The bounds of the component relative to the game window as a whole
	 */
	public Rectangle getDefiniteBounds(){
		if (parent == null){
			return bounds;
		} else {
			Rectangle parentPos = parent.getDefiniteBounds();
			return new Rectangle(bounds.getLocation().x + parentPos.x, bounds.getLocation().y + parentPos.y, bounds.width, bounds.height);
			//yeah, it's kinda recursive
		}
	}
	
	/**
	 * Returns the size of the component
	 * @return The size of the component
	 */
	public Dimension getSize() {
		return bounds.getSize();
	}
	
	/**
	 * Sets the size of the component
	 * @param size The new size of the component
	 */
	public void setSize(Dimension size) {
		this.bounds.setSize(size);
	}
	
	/**
	 * Returns the bounds of the component relative to its parent
	 * @return The bonds of the component, relative to the position of its parent
	 */
	public Rectangle getBounds(){
		return bounds;
	}
	
	/**
	 * Sets the size and position of the component, relative to its parent
	 * @param bounds The new bounds of the component
	 */
	public void setBounds(Rectangle bounds){
		this.bounds = bounds;
	}
	
	/**
	 * Returns the background image
	 * @return The background image
	 */
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Returns the component's parent
	 * @return The component's parent
	 */
	public UIComponent getParent() {
		return parent;
	}
	
	/**
	 * Sets the value of the component's parent.
	 * @param The new parent
	 */
	private void setParent(UIComponent parent){
		this.parent = parent;
	}
	
	/**
	 * Returns a list of the component's children components
	 * @return A list of the component's children
	 */
	public ArrayList<UIComponent> getChildren() {
		return new ArrayList<UIComponent>(children); //copy the list of children
	}

	/**
	 * Returns the component's depth (z value, order, etc)
	 * @return The component's depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Sets the depth (zValue, order, etc) of the component
	 * @param depth The new depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	/**
	 * Returns whether the component is visible
	 * @return Whether the component is visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Set whether the component is visible
	 * @param visible The new state of being visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Returns whether the component is enabled
	 * @return The current state of being enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}


	/**
	 * Sets the status of being enabled
	 * @param enabled The new state of being enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns the background color
	 * @return The color of the background
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the border
	 * @param color The color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Returns the color of the border
	 * @return the borderColor
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Sets the color of the border
	 * @param borderColor The borderColor to set
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Returns the size of the component's border
	 * @return the borderSize
	 */
	public float getBorderSize() {
		return borderSize;
	}

	/**
	 * Sets the size of the component's border
	 * @param borderSize the borderSize to set
	 */
	public void setBorderSize(float borderSize) {
		this.borderSize = borderSize;
	}
	
}
