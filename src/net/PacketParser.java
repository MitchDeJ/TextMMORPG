package net;

import java.awt.Color;
import java.net.InetAddress;
import java.util.ArrayList;

import packets.Packet;
import packets.Packet.PacketTypes;
import packets.Packet03Quit;
import window.Game;
import window.screens.LoginScreen;
import window.screens.RegisterScreen;
import window.screens.VerifyScreen;

public class PacketParser {

	private Game game;

	public PacketParser(Game game) {
		this.game = game;
	}

	public void parsePacket(byte[] data, InetAddress address, int port) {

		String message = new String(data).trim();
		System.out.println(message);
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		int status;

		switch (type) {
		case INVALID:
			break;
		case LOGINSTATUS:
			status = Integer.parseInt(parseMessageIndex(message, 0));
			String verified = parseMessageIndex(message, 0);

			if (game.getCurrentScreen() instanceof LoginScreen) {

				LoginScreen login = (LoginScreen) game.getCurrentScreen();

				if (status == 1) {
					login.info.setColor(Color.GREEN);
					login.info.setText("Logged in!");
					if (verified.equalsIgnoreCase("false")) {
						String name = login.userBox.getText();
						game.setCurrentScreen(new VerifyScreen(name));
					}
				} else if (status == 0) {
					login.info.setColor(Color.RED);
					login.info.setText("Incorrect password!");
				} else if (status == -1) {
					login.info.setColor(Color.RED);
					login.info.setText("User doesn't exist!");
				} else if (status == 2) {
					login.info.setColor(Color.RED);
					login.info.setText("Already logged in!");
				}
			}
			break;
		case REGISTERSTATUS:
			status = Integer.parseInt(parseMessageIndex(message, 0));

			if (game.getCurrentScreen() instanceof RegisterScreen) {
				RegisterScreen reg = (RegisterScreen) game.getCurrentScreen();

				if (status == 2 || status == 3) {
					reg.info.setColor(Color.RED);
					reg.info.setText("Username contains invalid characters.");
				} else if (status == 4) {
					reg.info.setColor(Color.RED);
					reg.info.setText("Invalid email address.");
				} else if (status == 5) {
					reg.info.setColor(Color.RED);
					reg.info.setText("Email address already in use.");
				} else if (status == 6) {
					reg.info.setColor(Color.RED);
					reg.info.setText("Password and Password confirmation do not match.");
				} else if (status == 0) {
					reg.info.setColor(Color.RED);
					reg.info.setText("Username is already in use.");
				} else if (status == 1) {
					reg.info.setColor(Color.GREEN);
					reg.info.setText("Successfully registered your account!");
					String name = reg.userBox.getText();
					game.setCurrentScreen(new VerifyScreen(name));
				}
			}
			break;
		case VERIFYSTATUS:
			status = Integer.parseInt(parseMessageIndex(message, 0));

			if (game.getCurrentScreen() instanceof VerifyScreen) {
				VerifyScreen ver = (VerifyScreen) game.getCurrentScreen();

				if (status == 1) {
					ver.errorinfo.setColor(Color.GREEN);
					ver.errorinfo.setText("Verified!");
				} else if (status == 0) {
					ver.errorinfo.setColor(Color.RED);
					ver.errorinfo.setText("Incorrect code.");
				} else if (status == -1) {
					ver.errorinfo.setColor(Color.RED);
					ver.errorinfo.setText("Unexpected error, try restarting the client.");
				}
			}
			break;
		default:
			break;
		}

	}

	private String parseMessageIndex(String message, int index) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		char divider = ';';
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == divider) {
				list.add(i);
			}
		}
		int[] div = new int[list.size()];

		for (int i = 0; i < div.length; i++) {
			div[i] = list.get(i).intValue();
		}

		return message.substring(div[index] + 1, div[index + 1]);
	}

}
