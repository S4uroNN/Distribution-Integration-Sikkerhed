package src;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ServerThread extends Thread{
	Socket connSocket;
	common c;
	Player me;
	Kiste kiste;
	
	public ServerThread(Socket connSocket,common c) {
		this.connSocket = connSocket;
		this.c=c;
	}
	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());
			String clientSentence = inFromClient.readLine();
			if (GameLogic.getPlayers().size() == 0){
				kiste = GameLogic.makeKiste();
			}
			else {
				kiste = GameLogic.getKiste();
			}
			me = GameLogic.makePlayers(clientSentence);
			me.setDataOutputStream(outToClient);
			StringBuilder sb = new StringBuilder();
			sb.append(kiste.getName()).append(" ").append(kiste.getPoint()).append(" ").append(kiste.getPosition()).append(" ");
			for (Player p : GameLogic.getPlayers()){
				sb.append(p.getName()).append(" ").append(p.getLocation()).append(" ").append(p.getDirection()).append(" ").append(p.getPoint()).append(" ");
			}
			GameLogic.NySpiller(sb);

			while (true){
				String movement = inFromClient.readLine();
				String[] split = movement.split(" ");
				GameLogic.updatePlayer(Integer.parseInt(split[0]), Integer.parseInt(split[1]) ,split[2] , me);
				StringBuilder sb2 = new StringBuilder();
				sb2.append(kiste.getName()).append(" ").append(kiste.getPoint()).append(" ").append(kiste.getPosition()).append(" ");
				for (Player p : GameLogic.getPlayers()){
					sb2.append(p.getName()).append(" ").append(p.getLocation()).append(" ").append(p.getDirection()).append(" ").append(p.getPoint()).append(" ");
				}
				System.out.println(sb2.toString());
				GameLogic.NySpiller(sb2);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		// do the work here

	}



}
