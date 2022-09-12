package sock;

import java.io.*;
import java.net.*;

public class TCPServer {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String clientSentence;
        String sentence;
        Boolean farvelbesked = false;
        boolean firstMessage = true;
        ServerSocket welcomeSocket = new ServerSocket(8888);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedReader inFromServerUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();

            if (!firstMessage || clientSentence.matches("goddag")) {
                firstMessage = false;
                System.out.println("Svar: goddag eller nej");
                sentence = inFromServerUser.readLine();
                outToClient.writeBytes(sentence + '\n');
                while (farvelbesked == false) {
                    String beskedenFraKlient = inFromClient.readLine();
                    if (!beskedenFraKlient.matches("farvel")) {
                        System.out.println("Fra klienten: " + beskedenFraKlient);
                        sentence = inFromServerUser.readLine();
                        outToClient.writeBytes(sentence + '\n');
                    } else {
                        farvelbesked = true;
                        System.out.println("Klienten har afbrudt forbindelsen.");
                        connectionSocket.close();
                    }

                }
            } else {
                outToClient.writeBytes("Serveren har afvist dig." + '\n');
            }


        }
    }

}



