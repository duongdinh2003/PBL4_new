package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;

import pieces.Piece;

public class Input extends MouseAdapter {

	Board board;
	String receive = "";
	
	public Input(Board board) {
		this.board = board;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		int col = e.getX()/board.tileSize;
		int row = e.getY()/board.tileSize;
		
		Piece pieceXY = board.getPiece(col, row);
		if (pieceXY != null) {
			board.selectedPiece = pieceXY;
			receive += col+",";
			receive += row+",";
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(board.selectedPiece != null) {
			// cập nhật xPos, yPos của selectedPiece
			board.selectedPiece.xPos = e.getX() - board.tileSize/2; // the piece is centered on the mouse
			board.selectedPiece.yPos = e.getY() - board.tileSize/2;
			
			board.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int col = e.getX()/board.tileSize;
		int row = e.getY()/board.tileSize;
		
		if(board.selectedPiece != null) {
			Move move = new Move(board, board.selectedPiece, col, row);
			
			if(board.isValidMove(move)) {
//				board.makeMove(move);
				receive += col + ",";
				receive += row + ",";
				
				try {
					DataOutputStream dos = new DataOutputStream(board.socket.getOutputStream());
					dos.writeUTF(receive);
					receive = "";
					System.out.println(receive);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} else {
				// nếu nước đi không hợp lệ, reset xPos, yPos lại như ban đầu
				board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
				board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
				receive = "";
			}
		}
		
		board.selectedPiece = null;
		board.repaint();
	}
}
