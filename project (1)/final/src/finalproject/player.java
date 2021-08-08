package finalproject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.event.DocumentEvent.ElementChange;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
//import com.sun.org.apache.xml.internal.serializer.ElemDesc;

public class player {
	private static Lock lock = new ReentrantLock();
	private String username;
	private String password;
	private int level;
	private String group;
	private String character;

	public player() {
	}

	public player(String s1, String s2, int i) {
		this.username = s1;
		this.password = s2;
		this.level = i;
	}

	public void login(Socket connection) throws IOException {
		boolean temp1 = false;
		BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		DataOutputStream outputToClient = new DataOutputStream(connection.getOutputStream());
		
		String input = inputFromClient.readLine();

		if (input.equalsIgnoreCase("login")){
			outputToClient.writeBytes("Input your user/pass:");
			outputToClient.flush();} else {
		int i = input.indexOf("/");
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
		if (temp1) {
		outputToClient.writeBytes("Welcome");
		outputToClient.flush();} else {
			outputToClient.writeBytes("username or pass is wrong!");
			outputToClient.flush();
		}
	}
}

	public boolean register() {
		System.out.print("tienloi Input your information following construction username/pass :   \n");
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		int i = s.indexOf("/");
		String username = s.substring(0, i);
		String password = s.substring(i + 1);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			Element root = doc.createElementNS("xmls", "users");
			doc.appendChild(root);

			root.appendChild(createUser(doc, "1", username, password, "level", "script", "character"));
//			    root.appendChild(createUser(doc, "2", "userName2", "passWord", "level", "script", "character"));
//			    root.appendChild(createUser(doc, "3", "userName3", "passWord", "level", "script", "character"));

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transf = transformerFactory.newTransformer();

			transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transf.setOutputProperty(OutputKeys.INDENT, "yes");
			transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(doc);

			File myFile = new File("usersData.xml");

			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(myFile);

			try {
				transf.transform(source, console);
				transf.transform(source, file);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException | TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private static Node createUser(Document doc, String id, String userName, String passWord, String level,
			String script, String character) {

		Element user = doc.createElement("user");

		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "userName", userName));
		user.appendChild(createUserElement(doc, "passWord", passWord));
		user.appendChild(createUserElement(doc, "level", level));
		user.appendChild(createUserElement(doc, "script", script));
		user.appendChild(createUserElement(doc, "character", character));

		return user;
	}

	private static Node createUserElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String s) {
		this.username = s;
	}

	public String getpass() {
		return password;
	}

	public void setpass(String s) {
		this.password = s;
	}

	public int getlevel() {
		return level;
	}

	public void setlevel(int i) {
		this.level = i;
	}

	public String getgroup() {
		return group;
	}

	public void setgroup(String s) {
		this.group = s;
	}

	public String getcharacter() {
		return character;
	}

	public void setcharacter(String s) {
		this.character = s;
	}

	public static void jointeam(Socket connection, String input) {
		// TODO Auto-generated method stub

		try {

			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			DataOutputStream outputToClient = new DataOutputStream(connection.getOutputStream());

			String serverReply = "";
			String clientReply = inputFromClient.readLine();

			int i = clientReply.indexOf(" ");
			String username = clientReply.substring(0, i);
			String password = clientReply.substring(i + 1);

			try {
				Thread.sleep(1000); // delay the thread. Time delay = size of request string in seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				File f = new File("userdata.xml");
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				try {
					builder = factory.newDocumentBuilder();
					Document document;
					try {
						document = builder.parse(f);
						document.getDocumentElement().normalize();
						NodeList nlist = document.getElementsByTagName("object");

						for (int temp = 0; temp < nlist.getLength(); temp++) {
							Node nNode = nlist.item(temp);
							if (nNode.getTextContent().contains(username)) {
								System.out.println("Correct");
							}
							;
						}
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
