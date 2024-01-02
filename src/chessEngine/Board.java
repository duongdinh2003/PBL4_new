package chessEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel {

	public int tileSize = 85;
	int cols = 8;
	int rows = 8;
//	int off = 150;
	
	ArrayList<Piece> pieceList = new ArrayList<>();
	public Piece selectedPiece; // the piece that you're moving
	Input input = new Input(this);
	public CheckScanner checkScanner = new CheckScanner(this); // #7
	public int enPassantTile = -1;
	//
	BufferedImage image;
	Graphics g;
	//
	//
	String listMoves = "";
	//
	
	public Board() {
		image = new BufferedImage(cols * tileSize, rows * tileSize, 3);
		g = image.getGraphics();
		
		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
		addPieces();
	}
	
	
	
	public Piece getPiece(int col, int row) {
		for (Piece piece: pieceList) {
			if(piece.col == col && piece.row == row) {
				return piece;
			}
		}
		return null;
	}
	
	public void makeMove(Move move) {
		
		if (move.piece.name.equals("Pawn")) { // En Passant
			movePawn(move);
		} else {
			move.piece.col = move.newCol;
			move.piece.row = move.newRow;
			move.piece.xPos = move.newCol*tileSize;
			move.piece.yPos = move.newRow*tileSize;
		
			move.piece.isFirstMove = false;
			
			capture(move.capture);
		}
		
		listMoves += " " + convertStandardMove(move.oldCol, move.oldRow, move.newCol, move.newRow);
//		this.selectedPiece = null;
//		this.repaint();
	}
	
	private void movePawn(Move move) {
		// en passant
		
		int colorIndex = move.piece.isWhite ? 1 : -1;
		
		if (getTileNum(move.newCol, move.newRow) == enPassantTile) {
			move.capture = getPiece(move.newCol, move.newRow + colorIndex);
		}
		
		if(Math.abs(move.piece.row - move.newRow) == 2) {
			enPassantTile = getTileNum(move.newCol, move.newRow + colorIndex);
		} else {
			enPassantTile = -1;
		}
		
		// promotions
		colorIndex = move.piece.isWhite ? 0 : 7;
		if (move.newRow == colorIndex) {
			promotionPawn(move);
		}
		
		move.piece.col = move.newCol;
		move.piece.row = move.newRow;
		move.piece.xPos = move.newCol*tileSize;
		move.piece.yPos = move.newRow*tileSize;
	
		move.piece.isFirstMove = false;
		
		capture(move.capture);
	}
	
	public void promotionPawn(Move move) {
		pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
		capture(move.piece);
	}
	
//	public void capture(Move move) {
//		pieceList.remove(move.capture);
//	}
	public void capture(Piece piece) {
		pieceList.remove(piece);
	}
	
	
	public boolean isValidMove(Move move) {
		if (sameTeam(move.piece, move.capture)) {
			return false;
		}
		
		if(!move.piece.isValidMovement(move.newCol, move.newRow)) {
			return false;
		}
		if(move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
			return false;
		}
		// #7 king check and pinned pieces
		if (checkScanner.isKingChecked(move)) {
			return false;
		}
		
		//
		
		return true;
	}
	
	public boolean sameTeam(Piece p1, Piece p2) {
		if (p1 == null || p2 == null) {
			return false;
		}
		return p1.isWhite == p2.isWhite;
	}
	

	public int getTileNum(int col, int row) {
		return row*rows+col;
	}
	
	Piece findKing(boolean isWhite) {
		for(Piece piece:pieceList) {
			if (isWhite == piece.isWhite && piece.name.equals("King")) {
				return piece;
			}
		}
		return null;
	}
	
	public void addPieces() {
//		Black
		pieceList.add(new Rook(this, 0, 0, false));
		pieceList.add(new Knight(this, 1, 0, false));
		pieceList.add(new Bishop(this, 2, 0, false));
		pieceList.add(new Queen(this, 3, 0, false));
		pieceList.add(new King(this, 4, 0, false));
		pieceList.add(new Bishop(this, 5, 0, false));
		pieceList.add(new Knight(this, 6, 0, false));
		pieceList.add(new Rook(this, 7, 0, false));
		
		pieceList.add(new Pawn(this, 0, 1, false));
		pieceList.add(new Pawn(this, 1, 1, false));
		pieceList.add(new Pawn(this, 2, 1, false));
		pieceList.add(new Pawn(this, 3, 1, false));
		pieceList.add(new Pawn(this, 4, 1, false));
		pieceList.add(new Pawn(this, 5, 1, false));
		pieceList.add(new Pawn(this, 6, 1, false));
		pieceList.add(new Pawn(this, 7, 1, false));
		
//		White
		pieceList.add(new Rook(this, 0, 7, true));
		pieceList.add(new Knight(this, 1, 7, true));
		pieceList.add(new Bishop(this, 2, 7, true));
		pieceList.add(new Queen(this, 3, 7, true));
		pieceList.add(new King(this, 4, 7, true));
		pieceList.add(new Bishop(this, 5, 7, true));
		pieceList.add(new Knight(this, 6, 7, true));
		pieceList.add(new Rook(this, 7, 7, true));
		
		pieceList.add(new Pawn(this, 0, 6, true));
		pieceList.add(new Pawn(this, 1, 6, true));
		pieceList.add(new Pawn(this, 2, 6, true));
		pieceList.add(new Pawn(this, 3, 6, true));
		pieceList.add(new Pawn(this, 4, 6, true));
		pieceList.add(new Pawn(this, 5, 6, true));
		pieceList.add(new Pawn(this, 6, 6, true));
		pieceList.add(new Pawn(this, 7, 6, true));

	}
	
//	public void paintComponent(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
//		// paint board
//		for (int r = 0; r < rows; r++) {
//			for (int c = 0; c < cols; c++) {
//				g2d.setColor((c + r)%2 == 0 ? new Color(227, 198, 181):new Color(157, 105, 53));
//				g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
//			}
//		}
//		// paint highlight available moves of selectedPiece
//		if(selectedPiece != null) {
//			for (int r = 0; r < rows; r++) {
//				for (int c = 0; c < cols; c++) {
//					if (isValidMove(new Move(this, selectedPiece, c, r))) {
//						g2d.setColor(new Color(68, 180, 57, 190));
//						g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
//					}
//				}
//			}
//		}
//		// paint pieces
//		for (Piece piece:pieceList) {
//			piece.paint(g2d);
//		}
//	}
	
	public void paint(Graphics g1) {
		System.out.println("Paint begin");
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);		// paint board
		g2d.fillRect(0, 0, this.WIDTH, this.HEIGHT);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				g2d.setColor((c + r)%2 == 0 ? new Color(227, 198, 181):new Color(157, 105, 53));
				g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
			}
		}
		
		// paint highlight available moves of selectedPiece
		if(selectedPiece != null) {
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					if (isValidMove(new Move(this, selectedPiece, c, r))) {
						g2d.setColor(new Color(68, 180, 57, 190));
						g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
					}
				}
			}
		}
		// paint pieces
		for (Piece piece:pieceList) {
			piece.paint(g2d);
		}
		
		g1.drawImage(image, 0, 0, null);
		//
//		AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
//		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//		tx.translate(-image.getWidth(null), -image.getHeight(null));
//		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//		image = op.filter(image, null);
//		g1.drawImage(image, 0, 0, null);
	}
	
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
	
	public void chessEngine(int oldCol, int oldRow, int newCol, int newRow) {
		// AI
		String stockfishPath = "D:/JDBC/chessEngine/stockfish-windows-x86-64-modern/stockfish/stockfish-windows-x86-64-modern.exe"; // Path to the Stockfish executable
//		String sendMove = convertStandardMove(oldCol, oldRow, newCol, newRow);
		try {
			Process stockfishProcess = Runtime.getRuntime().exec(stockfishPath);
			OutputStream stockfishInput = stockfishProcess.getOutputStream();
			InputStream stockfishOutput = stockfishProcess.getInputStream();
			// Send the UCI initialization command
			String uciCommand = "uci\n";
			stockfishInput.write(uciCommand.getBytes());
			stockfishInput.flush();
			
			// Send the position command
//			listMoves += " " + sendMove;
//			String positionCommand = "position startpos moves " + sendMove + "\n";
			String positionCommand = "position startpos moves" + listMoves + "\n";
			stockfishInput.write(positionCommand.getBytes());
			stockfishInput.flush();

			// Send the "go" command to ask Stockfish to analyze the position
			String goCommand = "go movetime 1000\n";
			stockfishInput.write(goCommand.getBytes());
			stockfishInput.flush();

			// Read Stockfish's response
			InputStreamReader reader = new InputStreamReader(stockfishOutput);
			BufferedReader bufferedReader = new BufferedReader(reader);;
//			String line = "";
//			int i = 1;
////			while (bufferedReader.readLine() != null && bufferedReader.readLine() != "") {
//			while (bufferedReader.readLine() != null) {
//				line = bufferedReader.readLine();
//				System.out.println(i++ + line);
//				if(bufferedReader.readLine() == null)
//					break;
//			}
			
			String lastLine = null;
			String line;

			// Read Stockfish's response
			while ((line = bufferedReader.readLine()) != null) {
			    lastLine = line;
			    if (lastLine.contains("bestmove"))
			    	break;
			}
		
			System.out.println("last:" + lastLine);
			String[] arrOfStr = lastLine.split(" ", 0);
			String getMove = arrOfStr[1];
			
			
			
			int oldCol2 = Integer.parseInt(revertColumn(getMove.charAt(0)+""));
			int oldRow2 = Integer.parseInt(convertRow(Character.getNumericValue(getMove.charAt(1))));
			int newCol2 = Integer.parseInt(revertColumn(getMove.charAt(2)+""));
			int newRow2 = Integer.parseInt(convertRow(Character.getNumericValue(getMove.charAt(3))));
//			String moveToMake = 
//					revertColumn(getMove.charAt(0)+"") 
//					+ convertRow(Character.getNumericValue(getMove.charAt(1)))
//					+ revertColumn(getMove.charAt(2)+"")
//					+ convertRow(Character.getNumericValue(getMove.charAt(3)));
			
			Move move = new Move(this, this.getPiece(oldCol2, oldRow2), newCol2, newRow2);
			this.makeMove(move);
			this.repaint();
			
			// Process Stockfish's response, which typically includes the best move

			// Send the "quit" command to Stockfish
			String quitCommand = "quit\n";
			stockfishInput.write(quitCommand.getBytes());
			stockfishInput.flush();
			System.out.println("End");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("Loi");
		}
		//
	}
}
