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
import window.screens.ScreenComponent;

public class LoginButton extends ScreenComponent {

	public LoginButton(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, 24)); 
		g.setColor(Color.WHITE);
		int middleX = sprite.getWidth()/2;
		int middleY = sprite.getHeight()/2;
		int fontWidth = g.getFontMetrics().stringWidth("LOGIN");
		int fontHeight = g.getFontMetrics().getAscent()-4;
		
		/*OPTIMIZING TEXT RENDERING*/
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
			    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		/**************************/
		g2d.drawString("LOGIN", x+middleX-(fontWidth/2), y+middleY+(fontHeight/2));
		
	}

	public Rectangle getClickBox() {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void onClick(Game game) {
		if (game.getCurrentScreen() instanceof LoginScreen) { //making sure we are on the login screen
			LoginScreen login = (LoginScreen) game.getCurrentScreen();
			String username = login.userBox.getText();
			String password = login.passBox.getText();
			Packet00Login packet = new Packet00Login(username, password);
			packet.writeData(game.getConnection());
		}
	}

}
