package packets;

import net.ServerConnection;

public class Packet01Register extends Packet {
	
	private String username;
	private String password;
	private String email;
	private String cpassword;

	public Packet01Register(String username, String password, String email, String cpassword) {
		super(01);
		this.username = username;
		this.password = password;
		this.email = email;
		this.cpassword = cpassword;
	}

	@Override
	public void writeData(ServerConnection c) {
		c.sendData(getData());
	}

	@Override
	public byte[] getData() {
		return ("01;"+username+";"+password+";"+email+";"+cpassword+";").getBytes();
	}

}
