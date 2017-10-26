package window.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import packets.Packet02Verify;
import window.Game;
import window.screens.RegisterScreen;
import window.screens.ScreenComponent;
import window.screens.VerifyScreen;

public class VerifyButton extends ScreenComponent {
	
	private String name;

	public VerifyButton(int x, int y, BufferedImage sprite, String name) {
		super(x, y, sprite);
		this.name = name;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, 24)); 
		g.setColor(Color.WHITE);
		int middleX = sprite.getWidth()/2;
		int middleY = sprite.getHeight()/2;
		int fontWidth = g.getFontMetrics().stringWidth("VERIFY");
		int fontHeight = g.getFontMetrics().getAscent()-4;
		
		/*OPTIMIZING TEXT RENDERING*/
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
			    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		/**************************/
		g2d.drawString("VERIFY", x+middleX-(fontWidth/2), y+middleY+(fontHeight/2));
		
	}

	public Rectangle getClickBox() {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void onClick(Game game) {
		if (game.getCurrentScreen() instanceof VerifyScreen) { //making sure we are on the verification screen
			VerifyScreen ver = (VerifyScreen) game.getCurrentScreen();
			String code = ver.verifyBox.getText();
			Packet02Verify packet = new Packet02Verify(name, code);
			packet.writeData(game.getConnection());
		}
	}

}
