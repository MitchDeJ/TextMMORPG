package window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import window.buttons.LoginButton;
import window.buttons.SwitchLoginButton;
import window.buttons.SwitchRegisterButton;
import window.buttons.VerifyButton;
import window.others.TextBox;
import window.others.TextContainer;

public class VerifyScreen extends Screen {
	
	private String name;
	
	private BufferedImage textBoxSprite = imageLoader.loadImage("/sprites/GUI/components/text_box.png");
	private BufferedImage loginButtonSprite = imageLoader.loadImage("/sprites/GUI/components/login_button.png");
	
	public TextContainer info = new TextContainer((WIDTH/2), 280, null, "Check your e-mail for your verification code!", new Color(87, 87, 110), 15);
	public TextBox verifyBox = new TextBox((WIDTH/2)-(textBoxSprite.getWidth()/2), 300, textBoxSprite, "Verification code", 24, 5);
	public VerifyButton verifyButton = new VerifyButton((WIDTH/2)-(loginButtonSprite.getWidth()/2), 375, loginButtonSprite, name);
	public TextContainer errorinfo = new TextContainer((WIDTH/2), 450, null, "", new Color(87, 87, 110), 15);
	
	public VerifyScreen(String name) {	
		//saving name so we can tell the server who we're verifiying.
		this.name = name;
		verifyButton = new VerifyButton((WIDTH/2)-(loginButtonSprite.getWidth()/2), 375, loginButtonSprite, name);
		//setting up the textboxes
		verifyBox.setSelected(true);
		verifyBox.setAllCaps(true);
		verifyBox.setSubmitter(verifyButton);
		
		//adding all components to the screen
		addComponent(info);
		addComponent(verifyBox);
		addComponent(verifyButton);
		addComponent(errorinfo);
	}

	@Override
	public void render(Graphics g) {
		//drawing background
		Graphics2D g2d = (Graphics2D) g;
		Color backgroundColor = new Color(39, 39, 49);
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, 1200, 800);
		for (int i = 0; i < components.size(); i++) {
			ScreenComponent s = components.get(i);
			s.render(g);
		}
		
	}
}
