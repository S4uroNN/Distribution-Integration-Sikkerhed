//import ServerThread;

import javafx.application.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
public class Server {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		common c = new common();
		ServerSocket welcomeSocket = new ServerSocket(9999);
		while (true) {
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			Socket connectionSocket = welcomeSocket.accept();
			(new ServerThread(connectionSocket,c)).start();

		}
	}

}
