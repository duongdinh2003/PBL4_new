package ChessServer;

public class ServerManager {

	public static void main(String[] args) throws InterruptedException {
		ChessServer2 chessServer2 = new ChessServer2();
		Thread thread = new Thread(() -> {
            chessServer2.startServer();
        });
        thread.start();
        
        ChessServer chessServer = new ChessServer();
		Thread thread2 = new Thread(() -> {
            chessServer.startServer();
        });
        thread2.start();
        
        ChessServer3 chessServer3 = new ChessServer3();
        Thread thread3 = new Thread(() -> {
        	chessServer3.startServer();
        });
        thread3.start();
        
        ConnectDBServer serverDB = new ConnectDBServer();
        Thread thread4 = new Thread(() -> {
        	serverDB.startServer();
        });
        thread4.start();

	}
}

class ServerThread2 extends Thread {
	
	
	ChessServer2 server2;
	
	public ServerThread2(ChessServer2 server2) {
		this.server2 = server2;
	}
	public void run() {
		
	}
}