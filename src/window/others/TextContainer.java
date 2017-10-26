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

public class TextContainer extends ScreenComponent{

	private String text;
	private Color color;
	private int fontSize;

	public TextContainer(int x, int y, BufferedImage sprite, String text, Color color, int fontSize) {
		super(x, y, sprite);
		this.setText(text);
		this.color = color;
		this.fontSize = fontSize;
	}

	public void render(Graphics g) {
		g.setFont(new Font("Trebuchet MS", Font.PLAIN, fontSize));
		g.setColor(color);
		
		int fontWidth = g.getFontMetrics().stringWidth(text);
		int fontHeight = g.getFontMetrics().getAscent()-4;
		
		/*OPTIMIZING TEXT RENDERING*/
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
			    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		/**************************/
		g.drawString(text, x-(fontWidth/2), y-(fontHeight/2));
	}

	public Rectangle getClickBox() {
		return new Rectangle();
	}

	public void onClick(Game game) {
		//informative text shouldnt do anything
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

}
