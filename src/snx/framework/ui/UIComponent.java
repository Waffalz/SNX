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
	
	public void update(GameTime gameTime){
		processChildren(gameTime);
	}
	
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		
		if (visible){
			Rectangle defBounds = getDefiniteBounds();
			drawBackground(spriteBatch, defBounds, color, borderColor);
			drawChildren(gameTime, spriteBatch);
		}
		
	}

	/**
	 * @param spriteBatch
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
	 * @param toRemove
	 */
	public void remove(UIComponent toRemove){
		if (!removeList.contains(toRemove) && children.contains(toRemove)){
			removeList.add(toRemove);
		}
	}

	/**
	 * updates children and handles add/removal queues
	 * @param gameTime
	 */
	protected void processChildren(GameTime gameTime) {
		updateChildren(gameTime);
		
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
	}
	
	/**
	 * @param gameTime 
	 * @param spriteBatch
	 */
	protected void drawChildren(GameTime gameTime, SpriteBatch spriteBatch) {
		for (UIComponent child : children){
			child.draw(gameTime, spriteBatch);
		}
	}
	
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
	
	//Because your code isn't professional until you have a buttload of nearly excessive encapsulation

	public Point getPosition() {
		return bounds.getLocation();
	}

	public void setPosition(Point position) {
		this.bounds.setLocation(position);;
	}
	
	/**
	 * returns the position of the component relative to 0,0 of the game's window's contentPane
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

	public Dimension getSize() {
		return bounds.getSize();
	}

	public void setSize(Dimension size) {
		this.bounds.setSize(size);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void setBounds(Rectangle bounds){
		this.bounds = bounds;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public UIComponent getParent() {
		return parent;
	}
	
	private void setParent(UIComponent parent){
		this.parent = parent;
	}

	public ArrayList<UIComponent> getChildren() {
		return new ArrayList<UIComponent>(children);
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}


	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the borderColor
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor the borderColor to set
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @return the borderSize
	 */
	public float getBorderSize() {
		return borderSize;
	}

	/**
	 * @param borderSize the borderSize to set
	 */
	public void setBorderSize(float borderSize) {
		this.borderSize = borderSize;
	}
	
}
