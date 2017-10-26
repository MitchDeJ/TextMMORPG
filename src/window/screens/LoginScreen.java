package window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import window.buttons.LoginButton;
import window.buttons.SwitchLoginButton;
import window.buttons.SwitchRegisterButton;
import window.others.TextBox;
import window.others.TextContainer;

public class LoginScreen extends Screen {
	
	private BufferedImage logo = imageLoader.loadImage("/sprites/GUI/logo_small.png");
	private BufferedImage textBoxSprite = imageLoader.loadImage("/sprites/GUI/components/text_box.png");
	private BufferedImage loginButtonSprite = imageLoader.loadImage("/sprites/GUI/components/login_button.png");
	private BufferedImage switchOn = imageLoader.loadImage("/sprites/GUI/components/dark_button.png");
	private BufferedImage switchOff = imageLoader.loadImage("/sprites/GUI/components/light_button.png");
	
	public SwitchLoginButton sLoginBut = new SwitchLoginButton((WIDTH/2)-((switchOn.getWidth()/2)*2), 225, switchOff, switchOn, this);
	public SwitchRegisterButton sRegBut = new SwitchRegisterButton((WIDTH/2), 225, switchOff, switchOn, this);
	public TextBox userBox = new TextBox((WIDTH/2)-(textBoxSprite.getWidth()/2), 300, textBoxSprite, "Username", 24, 15);
	public TextBox passBox = new TextBox((WIDTH/2)-(textBoxSprite.getWidth()/2), 375, textBoxSprite, "Password", 24, 30);
	public LoginButton loginButton = new LoginButton((WIDTH/2)-(loginButtonSprite.getWidth()/2), 450, loginButtonSprite);
	public TextContainer info = new TextContainer((WIDTH/2), 535, null, "", Color.RED, 15);
	
	public LoginScreen() {	
		//setting up the textboxes
		userBox.setSelected(true);
		passBox.setSecret(true);
		passBox.setSubmitter(loginButton);
		
		//adding all components to the screen
		addComponent(sLoginBut);
		addComponent(sRegBut);
		addComponent(userBox);
		addComponent(passBox);
		addComponent(loginButton);
		addComponent(info);
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
		g.drawImage(logo, (WIDTH/2)-(logo.getWidth()/2), 50, null);
		
	}
}
