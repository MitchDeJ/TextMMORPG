package window.screens;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import window.Game;

public abstract class ScreenComponent {
	
	protected int x;
	protected int y;
	
	protected BufferedImage sprite;
	
	public ScreenComponent(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public abstract void render(Graphics g);
	
	public abstract Rectangle getClickBox();

	public abstract void onClick(Game game);

}
