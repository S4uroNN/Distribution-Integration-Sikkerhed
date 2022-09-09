package sock;
import java.io.*;
import java.net.*;
public class TCPServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(8888);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();

			if(clientSentence.equals("Goddag")){
				String greeting = clientSentence + " " + connectionSocket;
				outToClient.writeBytes(greeting);
				System.out.println(greeting);
			}
		//	capitalizedSentence = clientSentence.toUpperCase() + '\n';
		//	outToClient.writeBytes(capitalizedSentence);
	
		}
	}

}
