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
	
	public void update(GameTime gameTime){
		super.update(gameTime);
	}
	
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		if (isVisible()){
			Rectangle defBounds = getDefiniteBounds();
			drawBackground(spriteBatch, defBounds, getColor(), getBorderColor());
			drawText(spriteBatch, textColor);
			drawChildren(gameTime, spriteBatch);
		}
		
	}
	
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
