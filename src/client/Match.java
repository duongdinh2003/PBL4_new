package client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import server.GameSession;

public class Match {

	private Map<Integer, GameSession> games = new ConcurrentHashMap<>();
	private int gameID = 1;
	
	public synchronized int createGame() {
		int id = gameID++;
		GameSession game = new GameSession(id);
		games.put(id, game);
		return id;
	}
	
	public GameSession getGame(int id) {
		return games.get(id);
	}
	
	public void endGame(int id) {
		games.remove(id);
	}
}
