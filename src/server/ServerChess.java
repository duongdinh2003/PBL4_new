package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.ClientProcessing;

public class ServerChess {
	
	public ServerSocket serverSocket;
	public Socket socket;
	public Vector<ClientProcessing> listPlayer = new Vector<ClientProcessing>();
	private static final int PORT = 8888;
	private static final int MAX_THREADS = 10;
	private ExecutorService pool;
	
//	public ServerChess(int port) {
//		try {
//			serverSocket = new ServerSocket(port);
//			
//			while (true) {
//				socket = serverSocket.accept();
//				ClientProcessing client = new ClientProcessing(socket, this);
//				listPlayer.add(client);
//				client.start();
//				System.out.println(listPlayer.size());
//				
//				if (listPlayer.size() == 1) {
//					DataOutputStream dos = new DataOutputStream(client.socket.getOutputStream());
//					dos.writeUTF("WHITE");
//				}
//				
//				if (listPlayer.size() == 2) {
//					DataOutputStream dos = new DataOutputStream(listPlayer.get(1).socket.getOutputStream());
//					dos.writeUTF("BLACK");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	public ServerChess() throws IOException {
		serverSocket = new ServerSocket(PORT);
		pool = Executors.newFixedThreadPool(MAX_THREADS);
	}
	
	public void startServer() {
		System.out.println("Server Chess is running...");
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				ClientProcessing client = new ClientProcessing(clientSocket, this);
				pool.execute(client);
				listPlayer.add(client);
				
				System.out.println(listPlayer.size());
				
				if (listPlayer.size() % 2 == 1) {
					DataOutputStream dos = new DataOutputStream(listPlayer.get(listPlayer.size()-1).socket.getOutputStream());
					dos.writeUTF("WHITE");
				}

				if (listPlayer.size() % 2 == 0) {
					DataOutputStream dos = new DataOutputStream(listPlayer.get(listPlayer.size()-1).socket.getOutputStream());
					dos.writeUTF("BLACK");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			stopServer();
		}
	}
	
	public void stopServer() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
			pool.shutdown();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			new ServerChess().startServer();
		} catch (Exception e) {
			System.out.println("Loi tao server");
		}
	}
}
