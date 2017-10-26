package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import window.Game;

public class PacketReceiver extends Thread {
	
	private DatagramSocket socket;
	private Game game;

	public PacketReceiver(Game game) {
		this.game = game;
		this.socket = game.getConnection().getSocket();
	}
	
	public void receive() {
       	byte[] data = new byte[1024];
    	DatagramPacket packet = new DatagramPacket(data, data.length);
    	try {
    		socket.receive(packet);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	 game.getPacketParser().parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
	}

	public void run() {
		while (true) {
			receive();
		}
	}

}
