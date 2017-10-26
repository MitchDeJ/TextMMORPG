package packets;

import net.ServerConnection;

public class Packet00Login extends Packet {
	
	private String username;
	private String password;

	public Packet00Login(String username, String password) {
		super(00);
		this.username = username;
		this.password = password;
	}

	@Override
	public void writeData(ServerConnection c) {
		c.sendData(getData());
	}

	@Override
	public byte[] getData() {
		return ("00;"+username+";"+password+";").getBytes();
	}

}