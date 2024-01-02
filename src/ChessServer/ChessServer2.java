package ChessServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pieceServer.Bishop;
import pieceServer.King;
import pieceServer.Knight;
import pieceServer.Pawn;
import pieceServer.Piece;
import pieceServer.Queen;
import pieceServer.Rook;


public class ChessServer2 {
	List<MoveHistory> moveHistory = new ArrayList<MoveHistory>();
	Vector<ClientProcessing2> playerList = new Vector<ClientProcessing2>();
	ServerSocket server;
	


//	public static void main(String[] args) throws Exception{
//		new ChessServer2();
//	}
	public ChessServer2() {
		
	}
	//
	public void startServer() {
		try {
			server = new ServerSocket(6666);
			System.out.println("Server 2 is running on port 6666");
			while(true) {
				try {
					Socket soc = server.accept();
					ClientProcessing2 t = new ClientProcessing2(soc, this);
					playerList.add(t);
					t.start();
					setColorAIMode(soc);
					GUI_Server.serverManagerForm.updateServer2(getClientsAddress());
//					if (playerList.size() == 1) { 
//						setColorAIMode();
//						break;
//					} 
				} catch (Exception e1) {
					System.out.println("Line 56" + e1.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Line 60" + e.getMessage());
		}
	}

	public void stopServer() { // stop accept connections
		try {
			server.close();
			System.out.println("Server 2 stopped accepting connections");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<String> getClientsAddress() {
		ArrayList<String> addressesContainer = new ArrayList<String>();
		for (ClientProcessing2 a: playerList) {
			addressesContainer.add(a.soc.getInetAddress().getHostAddress());
		}
		return addressesContainer;
	}
	
	public Boolean removeClient(String ipToRemove) {
		for (ClientProcessing2 client : playerList) {
			System.out.println("InetAddress: " + client.soc.getInetAddress().getHostAddress());
			if(client.soc.getInetAddress().getHostAddress().equals(ipToRemove)) {
				System.out.println("line 105");
				try {
					client.soc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				playerList.remove(client);
				GUI_Server.serverManagerForm.updateServer2(getClientsAddress());
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
			System.out.println("Server 2 closed all connections");
			GUI_Server.serverManagerForm.updateServer2(getClientsAddress());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//
//	public void setColorPlayer() {
//		try {
//			DataOutputStream dos = new DataOutputStream(this.playerList.get(0).soc.getOutputStream());
//			dos.writeUTF("You are white player");
//			DataOutputStream dos2 = new DataOutputStream(this.playerList.get(1).soc.getOutputStream());
//			dos2.writeUTF("You are black player");
//		} catch (Exception e) {
//			
//		}
//	}
	public void setColorAIMode(Socket soc) {
		try {
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			dos.writeUTF("You are white player");
		} catch (Exception e) {
			
		}
	}
	
	private void removeAllClient() {
		for (int i = 0; i < playerList.size(); i++) {
			try {
				DataOutputStream dos2 = new DataOutputStream(
						playerList.get(i).soc.getOutputStream());
				dos2.writeUTF("-9999");
				dos2.writeUTF("-9999");
				System.out.println(i + ":" + playerList.get(i).soc.isClosed());
			} catch (Exception e2) {
				
			}
		}
	}
	
	public void SendError2All() {
		for (int j = 0; j < playerList.size(); j++) {
			try {
				DataOutputStream dos = new DataOutputStream(playerList.get(j).soc.getOutputStream());
				dos.writeUTF("-9999");
				dos.writeUTF("-9999");
				dos.writeUTF("-9999");
				dos.writeUTF("-9999");
//				System.out.println(j + ":" + server.playerList.get(j).soc.isClosed());
			} catch (Exception e2) {
				
			}
		}
	}
	// standardize functions	
	public String convertColumn(int col) {
		switch(col) {
			case 0:
				return "a";
			case 1:
				return "b";
			case 2:
				return "c";
			case 3:
				return "d";
			case 4:
				return "e";
			case 5:
				return "f";
			case 6:
				return "g";
			case 7:
				return "h";
			default:
				return "";
		}
	}
	
	public String revertColumn(String col) {
		switch(col) {
			case "a":
				return "0";
			case "b":
				return "1";
			case "c":
				return "2";
			case "d":
				return "3";
			case "e":
				return "4";
			case "f":
				return "5";
			case "g":
				return "6";
			case "h":
				return "7";
			default:
				return "";
		}
	}
	
	public String convertRow(int row) {
		return 8 - row + "";
	}
	
	public String convertStandardMove(int oldCol, int oldRow, int newCol, int newRow) {
		return convertColumn(oldCol) + convertRow(oldRow) + convertColumn(newCol) + convertRow(newRow);
	}
	// end standardize functions
	// chess engine
	public int[] chessEngine(String listMoves) {
		// AI
		String stockfishPath = "D:/JDBC/chessEngine/stockfish-windows-x86-64-modern/stockfish/stockfish-windows-x86-64-modern.exe";
		try {
			Process stockfishProcess = Runtime.getRuntime().exec(stockfishPath);
			OutputStream stockfishInput = stockfishProcess.getOutputStream();
			InputStream stockfishOutput = stockfishProcess.getInputStream();
			// Send the UCI initialization command
			String uciCommand = "uci\n";
			stockfishInput.write(uciCommand.getBytes());
			stockfishInput.flush();
			// Send the position command
			String positionCommand = "position startpos moves" + listMoves + "\n";
			stockfishInput.write(positionCommand.getBytes());
			stockfishInput.flush();
			// Send the "go" command to ask Stockfish to analyze the position
			String goCommand = "go movetime 1000\n";
			stockfishInput.write(goCommand.getBytes());
			stockfishInput.flush();
			// Read Stockfish's response
			InputStreamReader reader = new InputStreamReader(stockfishOutput);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String lastLine = null;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
			    lastLine = line;
			    if (lastLine.contains("bestmove"))
			    	break;
			}
			// Send the "quit" command to Stockfish
			String quitCommand = "quit\n";
			stockfishInput.write(quitCommand.getBytes());
			stockfishInput.flush();
			
			System.out.println("last:" + lastLine);
			String[] arrOfStr = lastLine.split(" ", 0);
			String getMove = arrOfStr[1];
						
			int oldCol2 = Integer.parseInt(revertColumn(getMove.charAt(0)+""));
			int oldRow2 = Integer.parseInt(convertRow(Character.getNumericValue(getMove.charAt(1))));
			int newCol2 = Integer.parseInt(revertColumn(getMove.charAt(2)+""));
			int newRow2 = Integer.parseInt(convertRow(Character.getNumericValue(getMove.charAt(3))));
			
			int[] moveToMake = new int[] {oldCol2, oldRow2, newCol2, newRow2};				
//			Move move = new Move(this, this.getPiece(oldCol2, oldRow2), newCol2, newRow2);
//			this.makeMove(move);
//			this.repaint();
			return moveToMake;
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("Loi");
		}
		return null;
	}
}

class ClientProcessing2 extends Thread {
	Socket soc;
	ChessServer2 server;
	
	ArrayList<Piece> pieceList = new ArrayList<>();
	public String listMoves = ""; // for chess engine
	
	public ClientProcessing2(Socket soc, ChessServer2 server) {
		this.soc = soc;
		this.server = server;
		addPieces();
	}
	public void run() {
		while(true) {
			try {
				DataInputStream dis = new DataInputStream(soc.getInputStream()); // loi khi ngat ket noi
				int col = Integer.parseInt(dis.readUTF());
				int row = Integer.parseInt(dis.readUTF());
				int newCol = Integer.parseInt(dis.readUTF());
				int newRow = Integer.parseInt(dis.readUTF());
				System.out.println(col + "," + row + "," + newCol + "," + newRow);
				// Kiểm tra đủ 2 người hay chưa
//				if (server.playerList.size() < 2)
//					continue;
				Piece tempPiece = this.getPiece(col, row);
				if (tempPiece == null) {
					System.out.println("tempPiece null");
					continue;
				} else {
					System.out.println("tempPiece(" + tempPiece.col + "," +tempPiece.row + ")");
				}

				// Kiểm tra lượt đánh của từng người có hợp lệ hay không
				boolean ok = true;
				if (ok) {
					this.listMoves += " " + server.convertStandardMove(col, row, newCol, newRow);
					if(this.getPiece(newCol, newRow) != null) {
						this.getPiece(newCol, newRow).isWhite = tempPiece.isWhite;
					}
					tempPiece.col = newCol;
					tempPiece.row = newRow;
					
					if (tempPiece.name.equals("King") && Math.abs(newCol - col) == 2) {
						Piece rook;

						if (col < newCol) {
							rook = this.getPiece(7, row);
							rook.col = 5;
						} else {
							rook = this.getPiece(0, row);
							rook.col = 3;
						}
					}

					try {
						DataOutputStream dos = new DataOutputStream(server.playerList.get(0).soc.getOutputStream());
						dos.writeUTF(col + "");
						dos.writeUTF(row + "");
						dos.writeUTF(newCol + "");
						dos.writeUTF(newRow + "");
						System.out.println("Da gui: " + col + "," + row + "," + newCol + "," + newRow);
					} catch (Exception e) {

					}
					int[] movesFromAI = server.chessEngine(listMoves);
					int col2 = movesFromAI[0];
					int row2 = movesFromAI[1];
					int newCol2 = movesFromAI[2];
					int newRow2 = movesFromAI[3];
					this.listMoves += " " + server.convertStandardMove(col2, row2, newCol2, newRow2);
					try {
						DataOutputStream dos = new DataOutputStream(server.playerList.get(0).soc.getOutputStream());
						dos.writeUTF(col2 + "");
						dos.writeUTF(row2 + "");
						dos.writeUTF(newCol2 + "");
						dos.writeUTF(newRow2 + "");
						System.out.println("Da gui: " + col + "," + row + "," + newCol + "," + newRow);
					} catch (Exception e) {

					}
					Piece tempPiece2 = this.getPiece(col2, row2);
					if(this.getPiece(newCol2, newRow2) != null) {
						this.getPiece(newCol2, newRow2).isWhite = tempPiece2.isWhite;
					}
					tempPiece2.col = newCol2;
					tempPiece2.row = newRow2;
					if (tempPiece2.name.equals("King") && Math.abs(newCol2 - col2) == 2) {
						Piece rook2;
						if (col2 < newCol2) {
							rook2 = this.getPiece(7, row2);
							rook2.col = 5;
						} else {
							rook2 = this.getPiece(0, row2);
							rook2.col = 3;
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("1 Client da ngat ket noi");
				if(this == server.playerList.get(0) || this == server.playerList.get(1)) {
					server.SendError2All();
				}
				break;
			}
		}
	}
	
	public void addPieces() {
//		Black
		pieceList.add(new Rook(0, 0, false));
		pieceList.add(new Knight(1, 0, false));
		pieceList.add(new Bishop(2, 0, false));
		pieceList.add(new Queen(3, 0, false));
		pieceList.add(new King(4, 0, false));
		pieceList.add(new Bishop(5, 0, false));
		pieceList.add(new Knight(6, 0, false));
		pieceList.add(new Rook(7, 0, false));
		
		pieceList.add(new Pawn(0, 1, false));
		pieceList.add(new Pawn(1, 1, false));
		pieceList.add(new Pawn(2, 1, false));
		pieceList.add(new Pawn(3, 1, false));
		pieceList.add(new Pawn(4, 1, false));
		pieceList.add(new Pawn(5, 1, false));
		pieceList.add(new Pawn(6, 1, false));
		pieceList.add(new Pawn(7, 1, false));
		
//		White
		pieceList.add(new Rook(0, 7, true));
		pieceList.add(new Knight(1, 7, true));
		pieceList.add(new Bishop(2, 7, true));
		pieceList.add(new Queen(3, 7, true));
		pieceList.add(new King(4, 7, true));
		pieceList.add(new Bishop(5, 7, true));
		pieceList.add(new Knight(6, 7, true));
		pieceList.add(new Rook(7, 7, true));
		
		pieceList.add(new Pawn(0, 6, true));
		pieceList.add(new Pawn(1, 6, true));
		pieceList.add(new Pawn(2, 6, true));
		pieceList.add(new Pawn(3, 6, true));
		pieceList.add(new Pawn(4, 6, true));
		pieceList.add(new Pawn(5, 6, true));
		pieceList.add(new Pawn(6, 6, true));
		pieceList.add(new Pawn(7, 6, true));

	}
	
	public Piece getPiece(int col, int row) {
		for (Piece piece: pieceList) {
			if(piece.col == col && piece.row == row) {
				return piece;
			}
		}
		return null;
	}
	
}