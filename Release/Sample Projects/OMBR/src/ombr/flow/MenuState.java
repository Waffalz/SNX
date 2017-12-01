package ombr.flow;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import snx.framework.GameState;
import snx.framework.GameTime;
import snx.framework.graphics.SpriteBatch;
import snx.framework.ui.UIButton;
import snx.framework.ui.UIComponent;
import snx.framework.ui.UILabel;

public class MenuState extends GameState {
	
	UIComponent menu;
	
	public MenuState(){
		super();
	}
	
	public void initialize(){
		Dimension screenSize = getParent().getContentPane().getSize();
		
		menu = new UIComponent();
		menu.setSize(screenSize);
		menu.setBorderColor(menu.getColor());
		
		UILabel title = new UILabel();
		title.setSize(new Dimension(500, 200));
		title.setPosition(new Point(screenSize.width/2 - title.getSize().width/2, 100));
		title.setText("OPERATION MAKE BATTLEFIELD\n--Redux--");
		title.setFont(new Font("Lucida Sans Regular", Font.PLAIN, 30));
		menu.add(title);
		
		UIButton exitButton = new UIButton();
		exitButton.setSize(new Dimension(300, 50));
		exitButton.setPosition(new Point(screenSize.width/2 - exitButton.getSize().width/2, 500));
		exitButton.setText("Exit");
		exitButton.addListener(new Runnable(){
			public void run(){
				getParent().exit();
			}
		});
		menu.add(exitButton);
		
		UIButton playButton = new UIButton();
		playButton.setSize(new Dimension(300, 50));
		playButton.setPosition(new Point(screenSize.width/2 - playButton.getSize().width/2, 350));
		playButton.setText("Play");
		playButton.addListener(new Runnable(){
			public void run(){
				//TODO: make playing stuff happen
				getParent().setGameState(new CombatState());
			}
		});
		menu.add(playButton);
	}
	
	public void update(GameTime gameTime){
		menu.update(gameTime);
	}
	
	public void draw(GameTime gameTime, SpriteBatch spriteBatch){
		menu.draw(gameTime, spriteBatch);

	}
	
	public void unload(){
		
	}
	
}
