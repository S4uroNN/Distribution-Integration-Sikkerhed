import javafx.application.Application;

import java.io.*;
import java.net.*;
// Denne er kun medtaget til Test-formål, skal IKKE anvendes.
public class TCPClient {

	public static void main(String argv[]) throws Exception{
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("10.10.131.231",6789);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());



		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');

		GameLogic.makePlayers(sentence);
		Application.launch(Gui.class);

		//modifiedSentence = inFromServer.readLine();
		//System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}


