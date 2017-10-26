package window.others;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import window.Game;
import window.screens.ScreenComponent;

public class TextBox extends ScreenComponent {

	private boolean selected = false;
	public boolean secret = false;
	public boolean allCaps = false;
	private String text = "";
	private String placeholder;
	private int fontSize;
	private int maxChars;
	
	/*This component's onClick() method will be run if enter is pressed whilst selected*/
	private ScreenComponent submitter = null; 

	public TextBox(int x, int y, BufferedImage sprite, String placeholder, int fontSize, int maxChars) {
		super(x, y, sprite);
		this.placeholder = placeholder;
		this.fontSize = fontSize;
		this.maxChars = maxChars;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, fontSize));

		if (!isSelected())
			g.setColor(new Color(87, 87, 110)); // text color when not selected
		else
			g.setColor(new Color(204, 122, 0)); // text color when selected

		String textToRender;
		String secretText = "";

		if (isSecret()) {
			for (int i = 0; i < text.length(); i++) {
				secretText += "*";
			}
			textToRender = secretText;
		} else {
			textToRender = text;
		}
		
		if (allCaps)
			textToRender = textToRender.toUpperCase();

		if (textToRender.length() <= 0) {
			g.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
			textToRender = placeholder;
		}

		int middleX = sprite.getWidth() / 2;
		int middleY = sprite.getHeight() / 2;
		int fontWidth = g.getFontMetrics().stringWidth(textToRender);
		int fontHeight = g.getFontMetrics().getAscent() - 4;

		/* OPTIMIZING TEXT RENDERING */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		/**************************/
		g2d.drawString(textToRender, x + middleX - (fontWidth / 2), y + middleY + (fontHeight / 2));
	}

	public Rectangle getClickBox() {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	public void onClick(Game game) {
		setSelected(true);
		for (int i = 0; i < game.getCurrentScreen().getComponents().size(); i++) {
			ScreenComponent s = game.getCurrentScreen().getComponents().get(i);
			if (s instanceof TextBox && s != this) {
				TextBox t = (TextBox) s;
				t.setSelected(false);
			}
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void addText(String text) {
		if (this.text.length() == maxChars)
			return;
		this.text += text;
	}

	public void backspace() {
		if (text.length() <= 0)
			return;
		this.text = text.substring(0, text.length() - 1);
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	public ScreenComponent getSubmitter() {
		return submitter;
	}

	public void setSubmitter(ScreenComponent submitter) {
		this.submitter = submitter;
	}
	
	public boolean isAllCaps() {
		return allCaps;
	}

	public void setAllCaps(boolean allCaps) {
		this.allCaps = allCaps;
	}

}