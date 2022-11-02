package src;

import java.util.ArrayList;

public class common {
	int pointP1;
	int pointP2;

	public common() {
		super();
		pointP1 = 0;
		pointP2 = 0;
	}

	public int getPointP1() {
		return pointP1;
	}

	public int getPointP2() {
		return pointP2;
	}

	public void setPointP1(int pointP1) {
		this.pointP1 = pointP1;
	}

	public void setPointP2(int pointP2) {
		this.pointP2 = pointP2;
	}

	public ArrayList<Player> getPlayers(){
		return new ArrayList<>(GameLogic.getPlayers());
	}
}
