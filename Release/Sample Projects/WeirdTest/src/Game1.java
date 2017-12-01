

//-------------TEST CASE-------------------

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import snx.framework.Game;
import snx.framework.GameTime;
import snx.framework.Vector2;
import snx.framework.addon.Entity;
import snx.framework.graphics.SpriteBatch;
import snx.framework.input.*;

public class Game1 extends Game {
	
	SpriteBatch spriteBatch;
	
	Rectangle kipRect;
	
	Entity musRect;
	Rectangle musSource;
	
	Vector2 velocity;
	
	BufferedImage logo;
	
	Font tFont;
	
	ArrayList<String> readText;
	
	float rot = 0;
	float rotVel = (float)Math.PI;
	
	public Game1(){
		super();
		numbersVisible = true;
	}
	
	/**
	 * 
	 */
	public void initialize(){
		//TODO: put initialization code here
		kipRect = new Rectangle(10, 10, 200, 200);
		musRect = new Entity();
		musRect.setPosition(new Vector2(500, 500));
		musRect.setSize(new Vector2(50,50));
		musSource = new Rectangle(128,128, 128, 128);
		velocity = new Vector2(100, 100);
		super.initialize();
		
		/*
		for (String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
			System.out.println(s);
		}
		*/
	}
	
	/**
	 * 
	 */
	public void loadContent(){
		spriteBatch = new SpriteBatch((Graphics2D)getGraphicsDevice());
		//TODO: put content loading code here
		logo = getContentManager().loadImage("Content/GuildLogo.png");
		tFont = new Font("OCR A Extended", Font.PLAIN, 50);
		
		readText = getContentManager().loadText("Content/TestText.txt");
	}
	
	/**
	 * 
	 */
	public void update(GameTime gameTime){
		super.update(gameTime);
		//TODO: put drawing code here
		
		MouseState mus = Mouse.getState();
		KeyboardState kipz = Keyboard.getState();
		
		if (kipz.isKeyPressed(Keys.A)){
			kipRect.x -= (int)(velocity.x * gameTime.getElapsedTime().getTimeSeconds());
		}
		if (kipz.isKeyPressed(Keys.D)){
			kipRect.x += (int)(velocity.x * gameTime.getElapsedTime().getTimeSeconds());
		}
		if (kipz.isKeyPressed(Keys.W)){
			kipRect.y -= (int)(velocity.x * gameTime.getElapsedTime().getTimeSeconds());
		}
		if (kipz.isKeyPressed(Keys.S)){
			kipRect.y += (int)(velocity.x * gameTime.getElapsedTime().getTimeSeconds());
		}
		
		Vector2 mDif = new Vector2(mus.getPosition().x, mus.getPosition().y).subtract(musRect.getPosition().x, musRect.getPosition().y);
		Vector2 mDir = mDif.unit();
		Vector2 mDisp = mDir.unit().multiply(velocity);
		musRect.getPosition().x += (mDisp.x * gameTime.getElapsedTime().getTimeSeconds());
		musRect.getPosition().y += (mDisp.y * gameTime.getElapsedTime().getTimeSeconds());
		
		rot += rotVel * gameTime.getElapsedTime().getTimeSeconds();
	}
	
	/**
	 * 
	 */
	public void draw(GameTime gameTime){
		super.draw(gameTime);
		spriteBatch.clear(Color.white);
		//TODO: put drawing code here
		
		Graphics g = getGraphicsDevice();
		g.setColor(Color.black);
		spriteBatch.draw(logo, kipRect, Color.red);
		
		spriteBatch.draw(logo, musRect.getBounds(), musSource, Color.RED);
		spriteBatch.draw(logo, musRect.getBounds(), musSource, Color.white, rot, musRect.getSize().divide(2));
		
		spriteBatch.drawString(tFont, "It's a trap!", musRect.getPosition(), Color.black);
		spriteBatch.drawString(tFont, "It's a trap!\nIt's a trap!", musRect.getPosition(), Color.black, rot, new Vector2());
		
		spriteBatch.drawString(tFont, readText.get(0), new Vector2(50, 50), Color.black);
	}
}
