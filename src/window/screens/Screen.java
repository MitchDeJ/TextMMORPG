package window.screens;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import window.ImageLoader;

public abstract class Screen {
	
	protected final int WIDTH = 1024;
	protected final int HEIGHT = 768;
	
	public List<ScreenComponent> components = new ArrayList<ScreenComponent>();
	
	protected ImageLoader imageLoader = new ImageLoader();
	
	public void addComponent(ScreenComponent c) {
		components.add(c);
	}
	
	public void removeComponent(ScreenComponent c) {
		components.remove(c);
	}
	
	public List<ScreenComponent> getComponents() {
		return components;
	}
	
	public abstract void render(Graphics g);

}
