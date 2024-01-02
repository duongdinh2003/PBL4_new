package GUI_Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class PlayerSession {

	private static PlayerSession instance;
	public String idPlayer;
	public Socket socket;
	private String ipServerAdd;
	private int port;
	
	public PlayerSession(String idPlayer, String ipServer, int portServer) {
		this.idPlayer = idPlayer;
		this.ipServerAdd = ipServer;
		this.port = portServer;
		try {
			this.socket = new Socket(ipServer, portServer);
			
			startBackgroundThread();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tSession Failed");
		}
	}
	
	public static PlayerSession getInstance(String idPlayer, String ipServer, int portServer) {
		if (instance == null) {
			instance = new PlayerSession(idPlayer, ipServer, portServer);
		}
		return instance;
	}
	
	public static PlayerSession getExistedInstance() {
		if (instance == null) {
			Preferences prefer = Preferences.userNodeForPackage(PlayerSession.class);
			String idPlayer = prefer.get("idPlayer", "nonePlayer");
			String ipAddress = prefer.get("ipServerAdd", "noneIPAddress");
			int portServer = prefer.getInt("port", 9000);
			
			instance = new PlayerSession(idPlayer, ipAddress, portServer);
		}
		return instance;
	}
	
	public boolean checkExistedPlayer(String username, String password) {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Login");
			dos.writeUTF(username);
			dos.writeUTF(password);
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String kq = dis.readUTF();
			
			if (kq.equals("OK")) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi 5678");
		}
		return false;
	}
	
	private void startBackgroundThread() {
		Thread thread = new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(200);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\tLoi chay ngam");
			}
		});
		
		thread.start();
	}
	
	public List<String> requestGetRoomList() {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Get List Room");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			int numRoom = Integer.parseInt(dis.readUTF());
			List<String> listRoom = new ArrayList<String>();
			for (int i=0; i<numRoom; i++) {
				String room = dis.readUTF();
				listRoom.add(room);
				System.out.println(room);
			}
			return listRoom;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\tLoi request list room");
		}
		return null;
	}
	
	public void enterRoom(int idRoom) {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Enter Room");
			dos.writeUTF(idRoom+"");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String kq = dis.readUTF();
			
			if (kq.equals("Entered")) {
				System.out.println("Da vao phong "+idRoom);
			}
			else {
				System.out.println("Chua vao duoc phong "+idRoom);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi 5678 vao phong");
		}
	}
	
	public void exitRoom(int idRoom) {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Exit Room");
			dos.writeUTF(idRoom+"");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String kq = dis.readUTF();
			
			if (kq.equals("Exited")) {
				System.out.println("Da ra khoi phong "+idRoom);
			}
			else {
				System.out.println("Chua ra khoi phong "+idRoom);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi 5678 ra khoi phong");
		}
	}
	
	public void emptyRoom(int idRoom) {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Empty Room");
			dos.writeUTF(idRoom+"");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String kq = dis.readUTF();
			
			if (kq.equals("Emptied")) {
				System.out.println("Da ra khoi phong "+idRoom);
			}
			else {
				System.out.println("Chua ra khoi phong "+idRoom);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi 5678 lam trong phong");
		}
	}
	
	public void kickOffMatch(int idRoom) {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Set Status Room");
			dos.writeUTF(idRoom+"");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String kq = dis.readUTF();
			
			if (kq.equals("Kick-Off")) {
				System.out.println("Da bat dau tran dau "+idRoom);
			}
			else {
				System.out.println("Chua bat dau tran dau "+idRoom);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi 5678 bat dau tran dau");
		}
	}
	
	public void closeSession() {
		try {
			socket.close();
			System.out.println("Da dong ket noi");
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\tLoi dong phien");
		} finally {
			instance = null;
		}
	}
}
