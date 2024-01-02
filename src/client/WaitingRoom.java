package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Main;

public class WaitingRoom extends JFrame implements ActionListener {
	
	JButton btnStart, btnExit;

	public static void main(String[] args) {
		new WaitingRoom();
	}

	public WaitingRoom() {
		this.setSize(500, 500);
		this.setTitle("Select Team");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		btnStart = new JButton("START");
		btnStart.setBackground(Color.GREEN);
		btnStart.addActionListener(this);
		
		btnExit = new JButton("EXIT");
		btnExit.setBackground(Color.RED);
		btnExit.addActionListener(this);
		
		pnButton.add(btnStart);
		pnButton.add(btnExit);
		
		this.add(BorderLayout.SOUTH, pnButton);
		this.setVisible(true);
	}
	
	private void btnStartClicked() {
		Main main = new Main();
		main.setVisible(true);
	}
	
	private void btnExitClicked() {
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")) {
			btnStartClicked();
		}
		if (e.getActionCommand().equals("EXIT")) {
			btnExitClicked();
		}
	}
}
