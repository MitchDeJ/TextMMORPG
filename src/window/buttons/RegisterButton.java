package window.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import packets.Packet00Login;
import packets.Packet01Register;
import window.Game;
import window.screens.LoginScreen;
import window.screens.RegisterScreen;
import window.screens.ScreenComponent;

public class RegisterButton extends ScreenComponent {

	public RegisterButton(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, 24)); 
		g.setColor(Color.WHITE);
		int middleX = sprite.getWidth()/2;
		int middleY = sprite.getHeight()/2;
		int fontWidth = g.getFontMetrics().stringWidth("REGISTER");
		int fontHeight = g.getFontMetrics().getAscent()-4;
		
		/*OPTIMIZING TEXT RENDERING*/
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
			    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		/**************************/
		g2d.drawString("REGISTER", x+middleX-(fontWidth/2), y+middleY+(fontHeight/2));
		
	}

	public Rectangle getClickBox() {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void onClick(Game game) {
		if (game.getCurrentScreen() instanceof RegisterScreen) { //making sure we are on the login screen
			RegisterScreen reg = (RegisterScreen) game.getCurrentScreen();
			String username = reg.userBox.getText();
			String password = reg.passBox.getText();
			String email = reg.mailBox.getText();
			String cpassword = reg.cPassBox.getText();
			Packet01Register packet = new Packet01Register(username, password, email, cpassword);
			packet.writeData(game.getConnection());
		}
	}

}
