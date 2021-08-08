package finalproject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class serverTaskLogin implements Runnable{
	private Socket connection;
	player player = new player();
	team Team = new team();
	
	
	public serverTaskLogin(Socket s,player player, team Team) {
		this.connection = s;
		this.player = player;
		this.Team = Team;
	}
		@Override
	public void run() {
		try {		
			
			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			DataOutputStream outputToClient = new DataOutputStream(connection.getOutputStream());
			
			String serverReply = ""; 
			String clientReply = inputFromClient.readLine(); 
			
			System.out.println(clientReply);
			
			
			try {
                Thread.sleep(1000); // delay the thread. Time delay = size of request string in seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
			if (clientReply.contains("login") || clientReply.contains("register")) {
				serverReply = "Please insert UserName and PassWord!";
				outputToClient.writeBytes(serverReply);
				outputToClient.flush();
				outputToClient.close();
				inputFromClient.close();
			}
			
			if (!clientReply.contains("login") && !clientReply.contains("register")) {
			int i = clientReply.indexOf(" ");
			String username = clientReply.substring(0, i);
			String password = clientReply.substring(i+1);
			
			System.out.println(username);
			System.out.println(password);
			
			java.io.File fIn = new java.io.File("user.txt");
			Scanner inputFromFile = new Scanner(fIn);
			String data = "";
			String datatest = "";
			
			while (inputFromFile.hasNext()){
				data = data + inputFromFile.next();
			}
			
			inputFromFile.close();
			FileWriter fOut = new FileWriter("user.txt",true);
			java.io.PrintWriter outputToFile = new java.io.PrintWriter(fOut);
			
			if (data.contains(username) && data.contains(password)) {
				 serverReply = "Welcome!";
				 player.setusername(username);
				 player.setpass(password);
				 Team.join(player);
				 
			} else {
					serverReply = "Registered!";
					
					outputToFile.print(username);
					outputToFile.print(password);
					outputToFile.print(" ");
					outputToFile.println("lvl 0");
					outputToFile.close();
					
					}
			
			outputToClient.writeBytes(serverReply);
			outputToClient.flush();
			outputToClient.close();
			inputFromClient.close();
			
		
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
}

/*public class serverTaskLogin implements Runnable{
	private Socket connection;
	private static player player = new player();

	public serverTaskLogin(Socket s) {
		this.connection = s;
	}
	@Override
	public void run() {
		//boolean temp1 = false;
		
		try {
			
		String input;
		String output = "tien loi";
		BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		input = inputFromClient.readLine();
		
		if (input.equalsIgnoreCase("login")){
			
			output= "Insert name/pass";
			} else {
				//if (check(input)) {output= "Welcome";} else {output= "Wrong";}#
				output="tienloi";
			}
		
		
	
		
		 try {
             Thread.sleep(input.length()*1000); // delay the thread. Time delay = size of request string in seconds
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
		 
		System.out.println(output);
		
		DataOutputStream outputToClient = new DataOutputStream(connection.getOutputStream());
		outputToClient.writeBytes(output);
		outputToClient.flush();
		
		inputFromClient.close();
		outputToClient.close();
	
}catch (IOException e) {
    e.printStackTrace();
}
	}
	
	public boolean check(String input) {
		
		int i = input.indexOf("/");
		boolean temp1 = false;
		
		String username = input.substring(0, i);
		String password = input.substring(i + 1);

		File f = new File("usersData.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document;
			try {
				document = builder.parse(f);
				document.getDocumentElement().normalize();
				NodeList nlist = document.getElementsByTagName("user");
				for (int temp = 0; temp < nlist.getLength(); temp++) {
					Node nNode = nlist.item(temp);
					if (nNode.getChildNodes().item(1).getTextContent().equalsIgnoreCase(username)
							&& nNode.getChildNodes().item(3).getTextContent().equalsIgnoreCase(password)) {
						temp1 = true;
						break;
					}
				}
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp1;
}
	
}*/
