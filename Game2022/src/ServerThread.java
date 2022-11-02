import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ServerThread extends Thread{
	Socket connSocket;
	common c;
	
	public ServerThread(Socket connSocket,common c) {
		this.connSocket = connSocket;
		this.c=c; // Til Web-server opgaven skal denne ikke anvendes
	}
	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());
			
			// Do the work and the communication with the client here	
			// The following two lines are only an example
			
			String clientSentence = inFromClient.readLine();

			GameLogic.makePlayers(clientSentence);
			ArrayList<Player> list = new ArrayList<>(GameLogic.getPlayers());
			StringBuilder sb = new StringBuilder();
			for (Player p : list){
				sb.append(p.getName()).append(" ").append(p.getLocation()).append(" ").append(p.getDirection()).append(" ");
			}
			System.out.println(sb.toString());
			outToClient.writeBytes(sb.toString() + '\n');

		
		} catch (IOException e) {
			e.printStackTrace();
		}		
		// do the work here
	}
}
