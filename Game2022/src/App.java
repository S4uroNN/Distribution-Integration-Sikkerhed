import java.net.*;
import java.io.*;
import javafx.application.Application;

public class App {
	public static void main(String[] args) throws Exception{
		String sentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket= new Socket("localhost",9999);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());


		ClientModtagerTråd clientModtagerTråd = new ClientModtagerTråd(clientSocket);
		clientModtagerTråd.start();

		System.out.println("Indtast Spiller navn: ");
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');





		Application.launch(Gui.class);
	}
}
;