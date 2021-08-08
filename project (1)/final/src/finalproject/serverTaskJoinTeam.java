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

public class serverTaskJoinTeam implements Runnable {
	private Socket connection;
	private String input;
	player playerarr[];
	
	

	public serverTaskJoinTeam(Socket s, player player[]) {
		this.connection = s;
		this.playerarr = player;
		
	}

	@Override
	public void run() {
		player temp = new player();	
				for(int out = 0; out< playerarr.length; out++) {
					for(int in = out; in < playerarr.length; in++) {
						if (playerarr[in].getlevel()<playerarr[out].getlevel()) {
						temp = playerarr[in];
						playerarr[in]= playerarr[out];
						playerarr[out]=playerarr[in];
					}
				}
				int group = 0;
				int start = 0;
				
				while (start<playerarr.length) {
				for(int i = start; i<playerarr.length;i++) {
						if (playerarr[i+2].getlevel()-playerarr[i].getlevel()<=2) {
							playerarr[i].setgroup("group"+group);
							playerarr[i+1].setgroup("group"+group);
							playerarr[i+2].setgroup("group"+group);
							group++;
							start = start + 3;
							break;
						}
					}
				}
				}		
			
	}
}
	

