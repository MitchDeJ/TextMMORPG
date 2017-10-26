package packets;

import net.ServerConnection;

public class Packet03Quit extends Packet {

	public Packet03Quit() {
		super(03);
	}

	public void writeData(ServerConnection c) {
		c.sendData(getData());
	}

	public byte[] getData() {
		return ("03;").getBytes();
	}

}
