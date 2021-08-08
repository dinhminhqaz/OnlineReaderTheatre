package finalproject;

import java.net.Socket;

public class serverTaskRegister implements Runnable {
	private Socket connection;
	private static String input;
	private static player player = new player();

	public serverTaskRegister(Socket s, String in) {
		this.connection = s;
		this.input = in;
	}

	@Override
	public void run() {
		player.register();
	}
}
