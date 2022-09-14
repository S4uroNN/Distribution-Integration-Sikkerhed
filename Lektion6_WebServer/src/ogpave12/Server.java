package ogpave12;

import serverskeleton.common;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
//		common c = new common("eksempel");

		ArrayList<Person> personer = new ArrayList<Person>();
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			(new ServerThread(connectionSocket)).start();
		}
	}
	public static void modtagPersoner(ArrayList<Person>personer, BufferedReader instream){

	}
}
