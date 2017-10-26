package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerConnection {
	
	private InetAddress host;
	private String hostName;
	private int port;
    private DatagramSocket socket;
    
    public ServerConnection(String hostname, int port) {
    	this.hostName = hostname;
    	this.port = port;
    	init();
    }
    
    public void init() {
		try {
			host = InetAddress.getByName(hostName);
			socket = new DatagramSocket();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, host, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DatagramSocket getSocket() {
		return socket;
	}

}
