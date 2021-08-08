package finalproject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	public static void main(String args[]) throws UnknownHostException, IOException {
		Scanner scanner = new Scanner(System.in); // Create scanner to allow keyboard input
		while (true) {
			System.out.print("INFO: Enter q to stop session\n");
			System.out.print("Enter message request to the server :   \n");
			String input = scanner.nextLine(); // Waiting for keyboard input
			if ("q".equals(input)) {
				System.out.println("Exit!"); //
				break;
			}
			clienttask clientThread = new clienttask(input); // create a new socket task
			clientThread.run(); // Run Task
		}
	}
}
