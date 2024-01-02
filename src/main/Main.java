package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main extends JFrame {

//	public static void main(String[] args) {
//		
//	}
	
	
	public Main() {
		this.getContentPane().setBackground(Color.black);
		this.setLayout(new GridBagLayout());
//		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1000, 1000));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Board board = new Board();
//		this.add(board);
		
//		CountDownTime countTime = new CountDownTime();
//		this.add(BorderLayout.SOUTH, countTime);
		
		this.setVisible(true);
	}
}
