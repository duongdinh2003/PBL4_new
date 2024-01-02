package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import server.ServerChess;

public class ClientProcessing extends Thread {
	
	public Socket socket;
	public ServerChess serverChess;
	
	public ClientProcessing(Socket socket, ServerChess server) {
		this.socket = socket;
		this.serverChess = server;
	}
	
	public void run() {
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				String rec = dis.readUTF();
				String[] position = rec.split(",");
				int oldCol = Integer.parseInt(position[0]);
				int oldRow = Integer.parseInt(position[1]);
				int newCol = Integer.parseInt(position[2]);
				int newRow = Integer.parseInt(position[3]);
//				System.out.println(oldCol+","+oldRow+" --> "+newCol+","+newRow);
//				for (int i=0; i<serverChess.listPlayer.size(); i++) {
//					try {
//						DataOutputStream dos = new DataOutputStream(serverChess.listPlayer.get(i).socket.getOutputStream());
//						
//						dos.writeUTF(oldCol+"");
//						dos.writeUTF(oldRow+"");
//						dos.writeUTF(newCol+"");
//						dos.writeUTF(newRow+"");
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//				}
				
				if (serverChess.listPlayer.size() < 2) {
					try {
						DataOutputStream dos1 = new DataOutputStream(serverChess.listPlayer.get(0).socket.getOutputStream());
						dos1.writeUTF(oldCol+"");
						dos1.writeUTF(oldRow+"");
						dos1.writeUTF(oldCol+"");
						dos1.writeUTF(oldRow+"");
					} catch (Exception e) {
						System.out.println(e.getMessage() + "\t Hihi 0");
					}
				}
				
				if (serverChess.listPlayer.size() == 2) {
					if (this == serverChess.listPlayer.get(0)) {
						try {
							DataOutputStream dos1 = new DataOutputStream(serverChess.listPlayer.get(0).socket.getOutputStream());
							dos1.writeUTF(oldCol+"");
							dos1.writeUTF(oldRow+"");
							dos1.writeUTF(newCol+"");
							dos1.writeUTF(newRow+"");
							
							DataOutputStream dos2 = new DataOutputStream(serverChess.listPlayer.get(1).socket.getOutputStream());
							dos2.writeUTF((7-oldCol)+"");
							dos2.writeUTF((7-oldRow)+"");
							dos2.writeUTF((7-newCol)+"");
							dos2.writeUTF((7-newRow)+"");
						} catch (Exception e) {
							System.out.println(e.getMessage() + "\t Hihi 1");
						}
					}
					
					if (this == serverChess.listPlayer.get(1)) {
						try {
							DataOutputStream dos1 = new DataOutputStream(serverChess.listPlayer.get(1).socket.getOutputStream());
							dos1.writeUTF(oldCol+"");
							dos1.writeUTF(oldRow+"");
							dos1.writeUTF(newCol+"");
							dos1.writeUTF(newRow+"");
							
							DataOutputStream dos2 = new DataOutputStream(serverChess.listPlayer.get(0).socket.getOutputStream());
							dos2.writeUTF((7-oldCol)+"");
							dos2.writeUTF((7-oldRow)+"");
							dos2.writeUTF((7-newCol)+"");
							dos2.writeUTF((7-newRow)+"");
						} catch (Exception e) {
							System.out.println(e.getMessage() + "\t Hihi 2");
						}
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
