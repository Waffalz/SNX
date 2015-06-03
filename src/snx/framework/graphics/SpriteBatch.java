package snx.framework.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import snx.framework.Vector2;

/**
 * By Waffalz
 * Handles drawing to graphics, for convenience
 */
public class SpriteBatch {
	Graphics2D graphics;
	
	public SpriteBatch(Graphics2D g){
		graphics = g;
	}
	
	//TODO: implement more methods for drawing
	
	/**
	 * Draws an image
	 * @param img The Image to be drawn
	 * @param destinationRectangle The location on the graphicsDevice to be drawn to
	 * @param color The color. Duh. Currently this parameter doesn't do anything
	 */
	public void draw(BufferedImage img, Rectangle destinationRectangle, Color color){
		Color oCol = graphics.getColor();
		graphics.setColor(color);
		graphics.drawImage(img, destinationRectangle.x, destinationRectangle.y, destinationRectangle.width, destinationRectangle.height, null);
		graphics.setColor(oCol);
	}
	
	/**
	 * Draws a specified part of an image to the graphicsDevice
	 * @param img The Image to be drawn
	 * @param destinationRectangle The location on the graphicsDevice to be drawn to
	 * @param sourceRectangle The part of the image to be drawn
	 * @param color The color. Duh. Currently this parameter doesn't do anything
	 */
	public void draw(BufferedImage img, Rectangle destinationRectangle, Rectangle sourceRectangle, Color color){
		Color oCol = graphics.getColor();
		graphics.setColor(color);
		graphics.drawImage(img.getSubimage(sourceRectangle.x, sourceRectangle.y, sourceRectangle.width, sourceRectangle.height),
				destinationRectangle.x, destinationRectangle.y, destinationRectangle.width, destinationRectangle.height, null);
		graphics.setColor(oCol);
	}
	
	/**
	 * Draws a specified part of an image to the graphicsDevice, with rotations
	 * @param img The image to be drawn
	 * @param destinationRectangle The location on the graphicsDevice to be drawn to
	 * @param sourceRectangle The part of the image to be drawn
	 * @param color The color. Duh. Currently this parameter doesn't do anything
	 * @param rotation The angle of the transformed drawing
	 * @param origin The point about which the drawing will be rotated, relative to the position of the source rectangle
	 */
	public void draw(BufferedImage img, Rectangle destinationRectangle, Rectangle sourceRectangle, Color color, float rotation, Vector2 origin){
		Color oCol = graphics.getColor();
		AffineTransform oTrans = graphics.getTransform();
		graphics.setColor(color);
		BufferedImage toDraw = img.getSubimage(sourceRectangle.x, sourceRectangle.y, sourceRectangle.width, sourceRectangle.height);
		
		graphics.translate(destinationRectangle.x + origin.x, destinationRectangle.y + origin.y);
		graphics.rotate(rotation);
		
		graphics.translate(-origin.x, -origin.y);
		
		graphics.drawImage(toDraw, 0, 0, destinationRectangle.width, destinationRectangle.height, null);
		
		graphics.setTransform(oTrans);//returns the graphics to its original transformation state
		graphics.setColor(oCol);
	}
	
	//TODO: add drawString method(s)
	
	/**
	 * Draws text
	 * @param font The font of the text to be drawn
	 * @param text The text to be drawn
	 * @param position The position at which the text will be drawn
	 * @param color The color of which the text will be drawn
	 */
	public void drawString(Font font, String text, Vector2 position, Color color){
		Font oFont = graphics.getFont();
		Color oColor = graphics.getColor();
		graphics.setFont(font);
		graphics.setColor(color);
		graphics.drawString(text, position.x, position.y);
		graphics.setColor(oColor);
		graphics.setFont(oFont);
	}
	
	/**
	 * Draws text
	 * @param font The font of the text to be drawn
	 * @param text The text to be drawn
	 * @param position The position at which the text will be drawn
	 * @param color The color of which the text will be drawn
	 * @param rotation The angle of rotation at which the text will be drawn
	 * @param origin The point about which the text will be drawn
	 */
	public void drawString(Font font, String text, Vector2 position, Color color, float rotation, Vector2 origin){
		Color oCol = graphics.getColor();
		Font oFont = graphics.getFont();
		AffineTransform oTrans = graphics.getTransform();
		graphics.setColor(color);
		graphics.setFont(font);
		
		graphics.translate(position.x + origin.x, position.y + origin.y);
		graphics.rotate(rotation);
		
		graphics.translate(-origin.x, -origin.y);
		
		graphics.drawString(text, 0, 0);
		
		graphics.setTransform(oTrans);//returns the graphics to its original transformation state
		graphics.setColor(oCol);
		graphics.setFont(oFont);
	}
	
	/**
	 * Clears the entire graphics object by filling it with one color
	 * @param c The color to fill the graphics with
	 */
	public void clear(Color c){
		Color oCol = graphics.getColor();
		graphics.setColor(c);
		Rectangle clip = graphics.getClipBounds();
		graphics.fillRect(0, 0,  clip.width, clip.height);
		graphics.setColor(oCol);
		
	}
	
	public Graphics2D getGraphicsDevice(){
		return graphics;
	}
}