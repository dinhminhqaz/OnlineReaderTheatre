package finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
	private static Socket connection;
	private static ServerSocket serversocket;
	
	public static ExecutorService executor = Executors.newFixedThreadPool(15);
	
	public static void main(String[] args) throws IOException{
	
	int port = 1234;
	
	serversocket = new ServerSocket(port);
	
	player player = new player("","",0);
	
	team Team = new team();
	
	while (true) {
		
		
		System.out.println("Wait for connections");
		
		connection = serversocket.accept();
		
		
		System.out.println("New client connected "+ connection.getInetAddress().getHostAddress());
		
		if (player.getusername()=="") {
		executor.execute(new serverTaskLogin(connection,player,Team));}

		System.out.println(Team.getlength());
		
		if (Team.getlength() == 15)
			{
			executor.execute(new serverTaskJoinTeam(connection,Team.playerarr));
			for (int i=0; i<15;i++)
			{System.out.println(Team.playerarr[i].getusername());}
			}	
		
		
		executor.shutdown();
	    
		//while (!executor.isTerminated()) {}
		
		//connection.close();

		}
	
		
	}
	

}