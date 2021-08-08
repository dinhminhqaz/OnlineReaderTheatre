package finalproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class clienttask implements Runnable {
	private Socket connection;
	private int port = 1234;
	private String ip = "localhost";

	public String clientRequest = "";

	public clienttask(String _clientRequest) {
		clientRequest = _clientRequest;
	}

	@Override
	public void run() {

		try {
			connection = new Socket(ip, port);
			System.out.println("Connected! sending: " + clientRequest + "to Server...\nINFO:" + connection);

			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			wr.write(clientRequest);
			wr.write("\n");
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String serverRequest = rd.readLine();
			System.out.println("Server replied: " + serverRequest);

			wr.close();
			rd.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
