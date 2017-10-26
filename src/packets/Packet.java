package packets;

import net.ServerConnection;

public abstract class Packet {

	public static enum PacketTypes {
		INVALID(-1), LOGINSTATUS(00), REGISTERSTATUS(01), VERIFYSTATUS(02);
		
		private int packetId;
		private PacketTypes(int packetId) {
			this.packetId = packetId;
		}
		
		public int getId() {
			return packetId;
		}
	}
	
	public byte packetId;
	
	public Packet(int packetId) {
		this.packetId = (byte) packetId;
	}
	
	public abstract void writeData(ServerConnection c);
	
	public String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract byte[] getData();
	
	public static PacketTypes lookupPacket(String packetId) {
		try {
		return lookupPacket(Integer.parseInt(packetId));
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return PacketTypes.INVALID;
	}
	
	public static PacketTypes lookupPacket(int id) {
		for (PacketTypes p: PacketTypes.values()) {
			if (p.getId() == id) {
				return p;
			}
		}
			return PacketTypes.INVALID;
	}
	
}
