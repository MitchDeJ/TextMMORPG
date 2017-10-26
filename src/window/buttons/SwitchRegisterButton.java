package window.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import packets.Packet00Login;
import window.Game;
import window.screens.LoginScreen;
import window.screens.RegisterScreen;
import window.screens.Screen;
import window.screens.ScreenComponent;

public class SwitchRegisterButton extends ScreenComponent {
	
	public SwitchRegisterButton(int x, int y, BufferedImage sprite, BufferedImage selectedSprite, Screen screen) {
		super(x, y, sprite);
		if (screen instanceof RegisterScreen)
			this.sprite = selectedSprite;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, 24)); 
		g.setColor(Color.WHITE);
		int middleX = sprite.getWidth()/2;
		int middleY = sprite.getHeight()/2;
		int fontWidth = g.getFontMetrics().stringWidth("register");
		int fontHeight = g.getFontMetrics().getAscent()-4;
		
		/*OPTIMIZING TEXT RENDERING*/
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
			    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		/**************************/
		g2d.drawString("register", x+middleX-(fontWidth/2), y+middleY+(fontHeight/2));
		
	}

	public Rectangle getClickBox() {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void onClick(Game game) {
		if (game.getCurrentScreen() instanceof LoginScreen) {
			game.setCurrentScreen(new RegisterScreen());
		}
	}

}
