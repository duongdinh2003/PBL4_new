package chessEngine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingWorker;

public class Input extends MouseAdapter {

	Board board;
	
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
		
		int oldCol = board.selectedPiece.col;
		int oldRow = board.selectedPiece.row;
		
		int newCol = e.getX()/board.tileSize;
		int newRow = e.getY()/board.tileSize;
		
		if(board.selectedPiece != null) {
			Move move = new Move(board, board.selectedPiece, newCol, newRow);
			
			if(board.isValidMove(move)) {
				
//				System.out.println(board.selectedPiece.col + board.selectedPiece.row + col + row + "");
				System.out.println("Da gui: " + oldCol + "," + oldRow + "," + newCol + "," + newRow);
				board.makeMove(move);
				
//				board.selectedPiece = null;
				
				System.out.println("before repaint");
//				board.selectedPiece = null;
				board.repaint();
				System.out.println("after repaint");
//				System.out.println("before repaint");
//				board.repaint();
//				System.out.println("after repaint");
				
				
				
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						board.chessEngine(oldCol, oldRow, newCol, newRow);
						return null;
					}
				};
				worker.execute();
				
				System.out.println("after chessengine");
			} else {
				// nếu nước đi không hợp lệ, reset xPos, yPos lại như ban đầu
				board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
				board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
			}
		}
		
		board.selectedPiece = null;
		board.repaint();
		
		
	}
}
