package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ServerChess;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainServer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainServer frame = new MainServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PORT");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(86, 97, 59, 49);
		contentPane.add(lblNewLabel);
		
		JButton btnStart = new JButton("START");
		btnStart.setBackground(new Color(0, 255, 0));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int port = Integer.parseInt(textField.getText());
//				new ServerChess(port);
//				try {
//					new ServerChess();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
				
				
//				JOptionPane.showMessageDialog(contentPane, "Xin chao cac ban");
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStart.setBounds(265, 335, 126, 49);
		contentPane.add(btnStart);
		
		textField = new JTextField("8888");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(203, 101, 337, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
	}
}
