package ogpave12;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

// Denne er kun medtaget til Test-formål, skal IKKE anvendes.
public class TCPClient {

	public static void main(String argv[]) throws Exception{
		ArrayList<Person> personer = new ArrayList<Person>();
		personer.add(new Person(1,"Emil","Risskov"));
		personer.add(new Person(2,"Jesper","Viby"));
		personer.add(new Person(3,"Oliver","Aarhus"));

		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("localhost",6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes("Here are people \n");
		sendPersoner(personer,outToServer);
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}

	public static void sendPersoner(ArrayList<Person> list, DataOutputStream outputStream) throws IOException {
		for(Person p : list){
			outputStream.writeBytes(p.toString() + "\n");
		}
		outputStream.writeBytes("Personer sendt");
	}
}


