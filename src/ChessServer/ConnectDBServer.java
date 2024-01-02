package ChessServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import connectDB.DataBaseManager;

public class ConnectDBServer {
	
	private ServerSocket server;
	private Socket socket;
	Vector<ClientRequestDB> playerList = new Vector<ClientRequestDB>();
	public ConnectDBServer() {
		
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(5678);
			System.out.println("Server DB is runnning on port 5678");
			while (true) {
				try {
					socket = server.accept();				
					ClientRequestDB client = new ClientRequestDB(socket, this);
					playerList.add(client);
					client.start();
					GUI_Server.serverManagerForm.updateServerDB(getClientsAddress());
				} catch (Exception e1) {
					System.out.println(e1.getMessage()+"\tLoi ket noi server 5678");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi server 5678");
		}
	}
	//
	public void stopServer() { // stop accept connections
		try {
			server.close();
			System.out.println("Server DB stopped accepting connections");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<String> getClientsAddress() {
		ArrayList<String> addressesContainer = new ArrayList<String>();
		for (ClientRequestDB a: playerList) {
			addressesContainer.add(a.soc.getInetAddress().getHostAddress());
		}
		return addressesContainer;
	}
	
	public Boolean removeClient(String ipToRemove) {
		for (ClientRequestDB client : playerList) {
			System.out.println("InetAddress: " + client.soc.getInetAddress().getHostAddress());
			if(client.soc.getInetAddress().getHostAddress().equals(ipToRemove)) {
				System.out.println("line 105");
				try {
					client.soc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				playerList.remove(client);
				GUI_Server.serverManagerForm.updateServerDB(getClientsAddress());
				return true;
			}
		}
		return false;
	}
	
	public void closeAllConnections() { // close all connections
		try {
			for (int i = 0; i < playerList.size(); i++) {
				playerList.get(i).soc.close();
				playerList.get(i).interrupt();
			}
			System.out.println("Server DB closed all connections");
			GUI_Server.serverManagerForm.updateServerDB(getClientsAddress());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//
//	public static void main(String[] args) {
//		new ConnectDBServer();
//	}
	
//	private boolean checkExistUser(String username, String password) {
//		Connection connection = DataBaseManager.openConnection();
//		try {
//			Statement stm = connection.createStatement();
//			String query = "select * from Player where username = '" + username + "' and pass = '" + password + "'";
//			
//			ResultSet rs = stm.executeQuery(query);
//			
//			if (rs.next()) {
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//		DataBaseManager.closeConnection();
//		return false;
//	}
	

}

class ClientRequestDB extends Thread {
	Socket soc;
	ConnectDBServer server;
	
	public ClientRequestDB(Socket socket, ConnectDBServer server) {
		this.soc = socket;
		this.server = server;
	}
	
	private boolean checkExistUser(String username, String password) {
		Connection connection = DataBaseManager.openConnection();
		try {
			Statement stm = connection.createStatement();
			String query = "select * from Player where username = '" + username + "' and pass = '" + password + "'";
			
			ResultSet rs = stm.executeQuery(query);
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		DataBaseManager.closeConnection();
		return false;
	}
	
	private ArrayList<String> getRoomList() {
		ArrayList<String> list = new ArrayList<String>();
		Connection connection = DataBaseManager.openConnection();
		try {
			Statement stm = connection.createStatement();
			String query = "select ID_Room, Name_Room, status_Room, current_player_count from Room";
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
				int id = rs.getInt("ID_Room");
				String name = rs.getString("Name_Room");
				int st = rs.getInt("status_Room");
//				boolean status = st == 1 ? true : false;
				int count = rs.getInt("current_player_count");
				String str = id + "," + name + "," + st + "," + count;
				list.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void enterRoomForPlayer(int idRoom) {
		Connection connection = DataBaseManager.openConnection();
		try {
			Statement stm = connection.createStatement();
			String query = "select * from Room where ID_Room = "+idRoom;
			ResultSet rs = stm.executeQuery(query);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("current_player_count");
			}
			count++;
			String query2 = "update Room set current_player_count = "+count+" where ID_Room = "+idRoom;
			stm.executeUpdate(query2);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi vao phong");
		}
	}
	
	private void exitRoomForPlayer(int idRoom) {
		Connection connection = DataBaseManager.openConnection();
		try {
			Statement stm = connection.createStatement();
			String query = "select * from Room where ID_Room = "+idRoom;
			ResultSet rs = stm.executeQuery(query);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("current_player_count");
			}
			count--;
			String query2 = "update Room set current_player_count = "+count+" where ID_Room = "+idRoom;
			stm.executeUpdate(query2);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi ra khoi phong");
		}
	}
	
	private void emptyRoom(int idRoom) {
		Connection connection = DataBaseManager.openConnection();
		try {
			Statement stm = connection.createStatement();
			String query = "select * from Room where ID_Room = "+idRoom;
			ResultSet rs = stm.executeQuery(query);
			int st = 0;
			int count = 0;
			if (rs.next()) {
				st = rs.getInt("status_Room");
				count = rs.getInt("current_player_count");
			}
			if (st == 0 && count == 0) {
				return;
			}
			count = 0;
			st = 0;
			String query2 = "update Room set current_player_count = "+count+", status_Room = "+st+" where ID_Room = "+idRoom;
			stm.executeUpdate(query2);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi lam trong phong");
		}
	}
	
	private void setStatusRoom(int idRoom) {
		Connection connection = DataBaseManager.openConnection();
		try {
			Statement stm = connection.createStatement();
			String query = "select * from Room where ID_Room = "+idRoom;
			ResultSet rs = stm.executeQuery(query);
			int st = 0;
			if (rs.next()) {
				st = rs.getInt("status_Room");
			}
			if (st == 1) {
				return;
			}
			st = 1;
			String query2 = "update Room set status_Room = "+st+" where ID_Room = "+idRoom;
			stm.executeUpdate(query2);
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi dat trang thai phong");
		}
	}
	
	private void sendMessageToPlayer() {
		
	}
	
	public void run() {
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(soc.getInputStream());
				String msg = dis.readUTF();
				
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
				if (msg.equals("Login")) {
					String username = dis.readUTF();
					String password = dis.readUTF();
					if (checkExistUser(username, password)) {
						dos.writeUTF("OK");
					} else {
						dos.writeUTF("Error");
					}
					continue;
				}
				if (msg.equals("Get List Room")) {
					int numRoom = getRoomList().size();
					dos.writeUTF(numRoom+"");
					for (int i=0; i<numRoom; i++) {
						dos.writeUTF(getRoomList().get(i));
					}
					continue;
				}
				if (msg.equals("Enter Room")) {
					int idRoom = Integer.parseInt(dis.readUTF());
					enterRoomForPlayer(idRoom);
					dos.writeUTF("Entered");
					continue;
				}
				if (msg.equals("Exit Room")) {
					int idRoom = Integer.parseInt(dis.readUTF());
					exitRoomForPlayer(idRoom);
					dos.writeUTF("Exited");
					
					int numRoom = getRoomList().size();
					dos.writeUTF(numRoom+"");
					for (int i=0; i<numRoom; i++) {
						dos.writeUTF(getRoomList().get(i));
					}
					continue;
				}
				if (msg.equals("Empty Room")) {
					int idRoom = Integer.parseInt(dis.readUTF());
					emptyRoom(idRoom);
					dos.writeUTF("Emptied");
					continue;
				}
				if (msg.equals("Set Status Room")) {
					int idRoom = Integer.parseInt(dis.readUTF());
					setStatusRoom(idRoom);
					dos.writeUTF("Kick-Off");
					continue;
				}
			} catch (Exception e) {
				System.out.println("Line 320" + e.getMessage());
				break;
			}
		}
	}
}
