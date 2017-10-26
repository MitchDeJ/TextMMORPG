package packets;

import net.ServerConnection;

public class Packet02Verify extends Packet {
	
	private String username;
	private String vcode;

	public Packet02Verify(String username, String vcode) {
		super(02);
		this.username = username;
		this.vcode = vcode;
	}

	public void writeData(ServerConnection c) {
		c.sendData(getData());
	}

	public byte[] getData() {
		return ("02;"+username+";"+vcode+";").getBytes();
	}
	
}
