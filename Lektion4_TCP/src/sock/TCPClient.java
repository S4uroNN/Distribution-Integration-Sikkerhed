package sock;
import java.io.*;
import java.net.*;

public class TCPClient {

	public static void main(String argv[]) throws Exception{
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("localhost",8888);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println("Welcome to TalkyWalky - Version 1");
		System.out.println("Please enter shite: ");
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		while(true){


			modifiedSentence = inFromServer.readLine();
			System.out.println("From server: " + modifiedSentence);

			if(modifiedSentence.equals("Nej")){
				clientSocket.close();
			}
		}
	}
}


