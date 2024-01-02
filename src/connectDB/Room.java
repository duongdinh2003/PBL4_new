package connectDB;

public class Room {

	private int idRoom;
	private String nameRoom;
	private int playerNumber;
	private boolean statusRoom;
	
	public Room(int id, String name, int playerNum, boolean status) {
		this.idRoom = id;
		this.nameRoom = name;
		this.playerNumber = playerNum;
		this.statusRoom = status;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public boolean isStatusRoom() {
		return statusRoom;
	}

	public void setStatusRoom(boolean statusRoom) {
		this.statusRoom = statusRoom;
	}
	
	public String getNameRoom() {
		return nameRoom;
	}
	
}
