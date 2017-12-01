package snx.framework.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

import snx.framework.GameTime;
import snx.framework.Vector2;
import snx.framework.graphics.SpriteBatch;

public class UILabel extends UIComponent {
	
	private Font font;
	private String text;
	private Color textColor;
	
	private HorizontalTextAlignment hAlign;
	private VerticalTextAlignment vAlign;
	
	public UILabel() {
		super();
		font = new Font("Lucida Sans Regular", Font.PLAIN, 20);
		text = "";
		textColor = Color.white;
		
		hAlign = HorizontalTextAlignment.Center;
		vAlign = VerticalTextAlignment.Center;
	}
	
	/**
	 * Draws the component, its text, and its children
	 * @param gameTime The time of the game at which draw is called
	 * @param spriteBatch The SpriteBatch to draw the component
	 */
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		if (isVisible()){
			Rectangle defBounds = getDefiniteBounds();
			drawBackground(spriteBatch, defBounds, getColor(), getBorderColor());
			drawText(spriteBatch, textColor);
			drawChildren(gameTime, spriteBatch);
		}
		
	}
	
	/**
	 * Draws the label's text
	 * @param spriteBatch The SpriteBatch to draw to
	 * @param col the color of the font
	 */
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
	 * Returns the font of the text
	 * @return The font of the text
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Sets the font of the text
	 * @param font The new font of the text
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * Gets the text of the label
	 * @return The label's text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the label
	 * @param text The new text of the label
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the color of the text
	 * @return The text color
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * Sets the color of the text
	 * @param textColor The new color of the text
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * Gets the horizontal alignment of the text
	 * @return The horizontal alignment of the text
	 */
	public HorizontalTextAlignment gethAlign() {
		return hAlign;
	}

	/**
	 * Sets the horizontal alignment of the text
	 * @param hAlign The new horizontal alignment
	 */
	public void sethAlign(HorizontalTextAlignment hAlign) {
		this.hAlign = hAlign;
	}

	/**
	 * Gets the vertical alignment of the text
	 * @return The vertical alignment of the text
	 */
	public VerticalTextAlignment getvAlign() {
		return vAlign;
	}

	/**
	 * Sets the vertical alignment of the text
	 * @param vAlign The new vertical alignment of the text
	 */
	public void setvAlign(VerticalTextAlignment vAlign) {
		this.vAlign = vAlign;
	}
	
}
