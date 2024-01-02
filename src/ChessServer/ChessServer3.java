package ChessServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class MatchMessage {
	ClientProcessingMsg player1, player2;
	List<ClientProcessingMsg> playerList = new ArrayList<ClientProcessingMsg>();
	
	public MatchMessage(ClientProcessingMsg p1, ClientProcessingMsg p2) {
		this.player1 = p1;
		this.player2 = p2;
	}
}

public class ChessServer3 {

	Vector<ClientProcessingMsg> waiterList = new Vector<ClientProcessingMsg>();
//	Vector<ClientProcessingMsg> playerList = new Vector<ClientProcessingMsg>();
	List<MatchMessage> matchList = new ArrayList<MatchMessage>();
	ServerSocket server;
	public ChessServer3() {
		
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(8888);
			System.out.println("Server 3 is runnning on port 8888");
			while (true) {
				try {
					Socket socket = server.accept();
					DataInputStream dis = new DataInputStream(socket.getInputStream());	
					String request = dis.readUTF();
					System.out.println("Request: "+request);
					if (request.equals("Quick play")) {
						ClientProcessingMsg client = new ClientProcessingMsg(socket, this);
						waiterList.add(client);
						client.start();
						if (waiterList.size() == 2) {
							for (int i=0; i<waiterList.size(); i++) {
								DataOutputStream dos = new DataOutputStream(waiterList.get(i).soc.getOutputStream());
								dos.writeUTF("Found!");
							}
							waiterList.clear();
						}
						GUI_Server.serverManagerForm.updateServer3(getClientsAddress());		
					}
					
//					switch (request) {
//						case "Quick play":
//							waiterList.add(client);
//							if (waiterList.size() == 2) {
//								for (int i=0; i<waiterList.size(); i++) {
//									DataOutputStream dos = new DataOutputStream(waiterList.get(i).soc.getOutputStream());
//									dos.writeUTF("Found!");
//								}
//								waiterList.clear();
//							}
//							break;
//							
//						default:
//							playerList.add(client);
//							if (playerList.size() % 2 == 0) {
//								MatchMessage match = new MatchMessage(playerList.get(playerList.size()-2), playerList.get(playerList.size()-1));
//								match.playerList.add(playerList.get(playerList.size()-2));
//								match.playerList.add(playerList.get(playerList.size()-1));
//								matchList.add(match);
//							}
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	public void stopServer() { // stop accept connections
		try {
			server.close();
			System.out.println("Server 3 stopped accepting connections");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<String> getClientsAddress() {
		ArrayList<String> addressesContainer = new ArrayList<String>();
		for (ClientProcessingMsg a: waiterList) {
			addressesContainer.add(a.soc.getInetAddress().getHostAddress());
		}
		return addressesContainer;
	}
	
	public Boolean removeClient(String ipToRemove) {
		for (ClientProcessingMsg client : waiterList) {
			System.out.println("InetAddress: " + client.soc.getInetAddress().getHostAddress());
			if(client.soc.getInetAddress().getHostAddress().equals(ipToRemove)) {
				System.out.println("line 105");
				try {
					client.soc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				waiterList.remove(client);
				GUI_Server.serverManagerForm.updateServer3(getClientsAddress());
				return true;
			}
		}
		return false;
	}
	
	public void closeAllConnections() { // close all connections
		try {
			for (int i = 0; i < waiterList.size(); i++) {
				waiterList.get(i).soc.close();
				waiterList.get(i).interrupt();
			}
			System.out.println("Server 3 closed all connections");
			GUI_Server.serverManagerForm.updateServer3(getClientsAddress());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//
}

class ClientProcessingMsg extends Thread {
	Socket soc;
	ChessServer3 server;
	
	public ClientProcessingMsg(Socket socket, ChessServer3 server) {
		this.soc = socket;
		this.server = server;
	}
	
	public boolean isContainedInMatchMsg(Socket socket) {
		for (int i=0; i<server.matchList.size(); i++) {
			for (int j=0; j<server.matchList.get(i).playerList.size(); j++) {
				if (server.matchList.get(i).playerList.get(j).soc == socket) {
					return true;
				}
			}
		}
		return false;
	}
	
	public MatchMessage getMatchMsg(Socket socket) {
		for (int i=0; i<server.matchList.size(); i++) {
			for (int j=0; j<server.matchList.get(i).playerList.size(); j++) {
				if (server.matchList.get(i).playerList.get(j).soc == socket) {
					return server.matchList.get(i);
				}
			}
		}
		return null;
	}
	
	public void run() {
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(soc.getInputStream());
				String msg = dis.readUTF();
				System.out.println("Message from client: "+msg);
				
				if (!isContainedInMatchMsg(soc)) {
					continue;
				} else {
					
					MatchMessage match = getMatchMsg(soc);
					// Kiem tra da du 2 nguoi hay chua
					if (match.playerList.size() < 2) {
						continue;
					}
					
					for (int i=0; i<match.playerList.size(); i++) {
						try {
							DataOutputStream dos = new DataOutputStream(match.playerList.get(i).soc.getOutputStream());
							dos.writeUTF(msg);
						} catch (Exception e) {
							System.out.println("Loi khong gui duoc tin nhan");
							break;
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("1 Client da ngat ket noi server 8888");
				break;
			}
		}
	}
}
