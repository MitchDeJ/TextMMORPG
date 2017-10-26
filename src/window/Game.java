package window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import net.PacketParser;
import net.PacketReceiver;
import net.ServerConnection;
import packets.Packet03Quit;
import window.screens.LoginScreen;
import window.screens.Screen;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7492659545089075909L;
	
	private boolean running = false;
	private Thread thread;
	
	//Current screen we want to render
	private Screen currentScreen;
	
	//Networking
	private ServerConnection connection;
	private PacketReceiver packetReceiver;
	private PacketParser packetParser;
	
	//graphics
	BufferStrategy bs = this.getBufferStrategy();
	Graphics g;
	
	
	public static void main(String args[]) {
		new Window(1024, 768, "Affliction", new Game());
	}
	
	private void init() {	//this will be executed before we enter the game loop
		addMouseListener(new MouseHandler(this));
		setFocusTraversalKeysEnabled(false);
		addKeyListener(new KeyHandler(this));
		
		connection = new ServerConnection("localhost", 1337);
		packetReceiver = new PacketReceiver(this);
		packetParser = new PacketParser(this);
		
		packetReceiver.start();
		
		LoginScreen logScreen = new LoginScreen();	
		currentScreen = logScreen;
		
		/*Making sure the server knows when we quit*/
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		        Packet03Quit quitPacket = new Packet03Quit();
		        quitPacket.writeData(getConnection());
		    }
		}));
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
			
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		/*RENDERING CODE*/
		currentScreen.render(g);
		/*END OF RENDERING CODE*/
		
		g.dispose();
		bs.show();
				
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
	
	public PacketParser getPacketParser() {
		return packetParser;
	}

	public ServerConnection getConnection() {
		return connection;
	}
	
	public Screen getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(Screen switchto) {
		this.currentScreen = switchto;
	}
	

}
