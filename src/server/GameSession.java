package server;

import main.Main;

public class GameSession {

	private int id;
	private Main game;
	
	public GameSession(int id) {
		this.id = id;
		this.game = new Main();
	}
}
