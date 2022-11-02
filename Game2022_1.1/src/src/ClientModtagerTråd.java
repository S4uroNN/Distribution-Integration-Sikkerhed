package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientModtagerTråd extends Thread{

    Socket clientSocket;
    common c;
    pair kisteOldpos;

    public ClientModtagerTråd(Socket connSocket) {
        this.clientSocket = connSocket;
        this.c=c; // Til Web-server opgaven skal denne ikke anvendes
    }

    @Override
    public void run() {
        try {

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {

                String answer = inFromServer.readLine();
                System.out.println(answer);

                String[] split = answer.split(" ");
                HashMap<String, pair> oldPos = new HashMap<String, pair>();
                for (Player p : GameLogic.getPlayers()){
                    oldPos.put(p.name, p.getLocation());
                }

                if (GameLogic.getKiste() != null) {
                    kisteOldpos = GameLogic.getKiste().getPosition();
                }

                GameLogic.clearList();
                Kiste kiste = GameLogic.makeKiste();
                kiste.setName(split[0]);
                kiste.setPoint(Integer.parseInt(split[1]));

                pair newPos = new pair(Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                kiste.setPosition(newPos);
                if (kisteOldpos != null) {
                    if (kisteOldpos.getX() != newPos.getX() || kisteOldpos.getY() != newPos.getY()) {
                        Gui.moveChestOnScreen(kisteOldpos, newPos);
                    }
                }
                for (int i = 4; i < split.length; i += 5) {
                    Player p = new Player(split[i], new pair(Integer.parseInt(split[i + 1]), Integer.parseInt(split[i + 2])), split[i + 3]);
                            Player player = GameLogic.makePlayers(p.name);
                            if (!oldPos.containsKey(p.name)){
                                player.setLocation(p.location);
                                player.setDirection(p.direction);
                                Gui.placePlayerOnScreen(p.getLocation(),p.getDirection());
                            }
                            else if (oldPos.get(p.name) != player.getLocation()){
                                player.setLocation(new pair(Integer.parseInt(split[i + 1]), Integer.parseInt(split[i + 2])));
                                player.setDirection(split[i+3]);
                                player.addPoints(Integer.parseInt(split[i+4]));
                                Gui.movePlayerOnScreen(oldPos.get(p.name), player.getLocation(), player.getDirection());
                                Gui.updateScoreTable();
                            }
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
