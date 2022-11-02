package src;//import ServerThread;

import javafx.application.Application;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {

		common c = new common();
		ServerSocket welcomeSocket = new ServerSocket(9999);
		while (true) {

			Socket connectionSocket = welcomeSocket.accept();
			ServerThread serverThread = new ServerThread(connectionSocket, c);

			serverThread.start();

		}
	}

}
