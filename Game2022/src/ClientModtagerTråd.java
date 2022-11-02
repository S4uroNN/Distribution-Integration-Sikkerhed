import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ClientModtagerTråd extends Thread{

    Socket clientSocket;
    common c;

    public ClientModtagerTråd(Socket connSocket) {
        this.clientSocket = connSocket;
        this.c=c; // Til Web-server opgaven skal denne ikke anvendes
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String answer = inFromServer.readLine();
            System.out.println(answer);
            String[] split = answer.split(" ");
            int counter = 0;
            for (int i = 0; i < split.length; i+=4) {
                Player p = new Player(split[i], new pair(Integer.parseInt(split[i+1]), Integer.parseInt(split[i+2])), split[i+3]);
                GameLogic.makePlayers(p.name);
                GameLogic.getPlayers().get(counter).setLocation(p.location);
                GameLogic.getPlayers().get(counter).setDirection(p.direction);
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
