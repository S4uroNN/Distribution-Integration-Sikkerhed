package src;

import java.net.*;
import java.io.*;
import javafx.application.Application;

public class App {
	static DataOutputStream outToServer;
	public static void modtagPosition(int delta_x, int delta_y, String direction) throws IOException {
		String movement = delta_x + " " + delta_y + " " + direction + " ";
		outToServer.writeBytes(movement + '\n');

	}


	public static void main(String[] args) throws Exception{
		String sentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("localhost",9999);

		outToServer = new DataOutputStream(clientSocket.getOutputStream());

		System.out.println("Indtast Spiller navn: ");
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');

		ClientModtagerTråd clientModtagerTråd = new ClientModtagerTråd(clientSocket);
		clientModtagerTråd.start();



		Application.launch(Gui.class);


	}
}
