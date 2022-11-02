package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class GameLogic {
public static List<Player> players = new ArrayList<Player>();	

	public static Kiste kiste;
	
	
	public static Player makePlayers(String name) {
		pair p=getRandomFreePosition();
		Player me = new Player(name,p,"up");
		players.add(me);
		p=getRandomFreePosition();
		return me;
	}

	public static Kiste makeKiste() {
		pair p=getRandomFreePosition();
		kiste = new Kiste("Kiste",50, p);
		return kiste;
	}
	
	public static pair getRandomFreePosition()
	// finds a random new position which is not wall 
	// and not occupied by other players 
	{
		int x = 1;
		int y = 1;
		boolean foundfreepos = false;
		while  (!foundfreepos) {
			Random r = new Random();
			x = Math.abs(r.nextInt()%18) +1;
			y = Math.abs(r.nextInt()%18) +1;
			if (Generel.board[y].charAt(x)==' ') // er det gulv ?
			{
				foundfreepos = true;
				for (Player p: players) {
					if (p.getXpos()==x && p.getYpos()==y) //pladsen optaget af en anden 
						foundfreepos = false;
				}
				
			}
		}
		pair p = new pair(x,y);
		return p;
	}
	
	public synchronized static void updatePlayer(int delta_x, int delta_y, String direction, Player me)
	{
		me.direction = direction;
		int x = me.getXpos(),y = me.getYpos();

		if (Generel.board[y+delta_y].charAt(x+delta_x)=='w') {
			me.addPoints(-1);
		} 
		else {
			// collision detection
			if (kiste.getPosition().getX() == x+delta_x && kiste.getPosition().getY() == y+delta_y){
				me.addPoints(kiste.getPoint());
				pair pair = getRandomFreePosition();
				kiste.setPosition(pair);
			}
			Player p = getPlayerAt(x+delta_x,y+delta_y);
			if (p!=null) {
              me.addPoints(10);
              //update the other player
              p.addPoints(-10);
              pair pa = getRandomFreePosition();
              p.setLocation(pa);
              pair oldpos = new pair(x+delta_x,y+delta_y);
              //Gui.movePlayerOnScreen(oldpos,pa,p.direction);
			} else 
				me.addPoints(1);
			pair oldpos = me.getLocation();
			pair newpos = new pair(x+delta_x,y+delta_y); 
			//Gui.movePlayerOnScreen(oldpos,newpos,direction);
			me.setLocation(newpos);
		}
		
		
	}
	
	public static Player getPlayerAt(int x, int y) {
		for (Player p : players) {
			if (p.getXpos()==x && p.getYpos()==y) {
				return p;
			}
		}
		return null;
	}

	public static Kiste getKiste() {
		return kiste;
	}

	public static List<Player> getPlayers() {
		return players;
	}

	public static void clearList(){
		players.clear();
	}

	public synchronized static void NySpiller(StringBuilder sb) throws IOException {
		for (Player p : GameLogic.getPlayers()){
			p.sendData(sb.toString());
		}
	}

}
