package window;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7492659545089075909L;
	
	private boolean running = false;
	private Thread thread;
	
	public static void main(String args[]) {
		new Window(1280, 720, "Text MMORPG", new Game());
	}
	
	private void init() {	//this will be executed before we enter the game loop

	}
	
	public void run() {
		
		init();
		this.requestFocus();
		
		/*Game loop*/
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		/*end of game loop*/
	}
	
	private void render() {
				
	}

	private void tick() {
				
	}

	public synchronized void start() {
		
		if (running)
			return; // cancel if already started.
		
		running = true;
		thread = new Thread(this);//make a new thread to run the game on.
		thread.start();
		
	}

}
