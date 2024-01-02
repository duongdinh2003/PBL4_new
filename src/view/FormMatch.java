package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMatch extends JFrame {

	private JPanel contentPane;
	private final JLabel lblBackground = new JLabel("");

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormMatch frame = new FormMatch();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FormMatch(JPanel panelmatch) {
		setTitle("Match");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1262, 882);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(558, 69, 680, 680);
		panel.add(panelmatch);
		contentPane.add(panel);
		
		JTextArea textAreaHistoryMoves = new JTextArea();
		textAreaHistoryMoves.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaHistoryMoves.setBounds(25, 88, 486, 235);
		contentPane.add(textAreaHistoryMoves);
		
		JTextArea textAreaMessage = new JTextArea();
		textAreaMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaMessage.setBounds(25, 367, 486, 320);
		contentPane.add(textAreaMessage);
		
		JTextArea textAreaInputMessage = new JTextArea();
		textAreaInputMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAreaInputMessage.setBounds(25, 719, 394, 49);
		contentPane.add(textAreaInputMessage);
		
		JButton btnSendMessage = new JButton("Send");
		btnSendMessage.setBackground(Color.GREEN);
		btnSendMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSendMessage.setBounds(429, 721, 82, 47);
		contentPane.add(btnSendMessage);
		lblBackground.setIcon(new ImageIcon(FormMatch.class.getResource("/res/background.png")));
		lblBackground.setBounds(10, 10, 920, 600);
		contentPane.add(lblBackground);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuit.setBackground(new Color(255, 69, 0));
		btnQuit.setBounds(25, 31, 82, 47);
		contentPane.add(btnQuit);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDraw.setBackground(Color.GREEN);
		btnDraw.setBounds(219, 31, 82, 47);
		contentPane.add(btnDraw);
	}
}
