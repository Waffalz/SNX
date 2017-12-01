package snx.framework.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Stroke;
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
		
		
		String[] lines = text.split("\n"); //splitting up text by lines
		FontMetrics metrics = graphics.getFontMetrics(font); //metrics for measuring text
		for (int i = 0; i < lines.length; i++){
			graphics.drawString(lines[i], position.x, position.y + (i + 1) * metrics.getHeight() - metrics.getMaxDescent());
		}
		
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
		
		String[] lines = text.split("\n"); //splitting up text by lines
		FontMetrics metrics = graphics.getFontMetrics(font); //metrics for measuring text
		for (int i = 0; i < lines.length; i++){
			graphics.drawString(lines[i], 0, (i + 1) * metrics.getHeight() - metrics.getMaxDescent());
		}
		
		graphics.setTransform(oTrans);//returns the graphics to its original transformation state
		graphics.setColor(oCol);
		graphics.setFont(oFont);
	}
	
	/**
	 * Draws a Rectangle to the screen
	 * @param dest The bounds of the SpriteBatch on which the rectangle will appear
	 * @param thickness The line thickness of the rectangle
	 * @param color The line color of the rectangle
	 */
	public void drawRectangle(Rectangle dest, float thickness, Color color){
		Color oCol = graphics.getColor();
		Stroke oStroke = graphics.getStroke();
		graphics.setColor(color);
		graphics.setStroke(new BasicStroke(thickness));
		graphics.drawRect(dest.x, dest.y, dest.width, dest.height);
		graphics.setColor(oCol);
		graphics.setStroke(oStroke);
	}
	
	/**
	 * Draws a rectangle of specified line color
	 * @param dest The bounds of the rectangle to be drawn to the SpriteBatch
	 * @param color The fill color of the rectangle
	 */
	public void drawFillRectangle(Rectangle dest, Color color){
		Color oCol = graphics.getColor();
		graphics.setColor(color);
		graphics.fillRect(dest.x, dest.y, dest.width, dest.height);
		graphics.setColor(oCol);
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
	
	/**
	 * Returns the graphics device that the SpriteBatch is drawing on
	 * @return The SpriteBatch's graphics device
	 */
	public Graphics2D getGraphicsDevice(){
		return graphics;
	}
	
	/**
	 * Prints the list of all fonts available. Mainly used for the developer to pick out fonts
	 */
	public static void printAllFonts(){
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (Font fo : fonts){
			System.out.println(fo.getFontName());
		}
	}
}
