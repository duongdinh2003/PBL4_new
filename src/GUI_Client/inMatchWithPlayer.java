package GUI_Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Board;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class inMatchWithPlayer extends JFrame {
	String ipServer;
	int portServer;
	public int idRoom;
	
	public matchFeaturesPanel matchFeaturesPanel;
	Board board;
	
	
	private JPanel contentPane;
	private JPanel panelDraw;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					inMatchWithPlayer frame = new inMatchWithPlayer();
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
	public inMatchWithPlayer(int idRoom, String ipServer, int portServer) {
		this.ipServer = ipServer;
		this.portServer = portServer;
		this.idRoom = idRoom;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chess game");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(718, 10, 279, 70);
		contentPane.add(lblNewLabel);
		// Board
		board = new Board(ipServer, portServer, this);
		board.setBounds(165, 90, board.tileSize*board.cols, board.tileSize*board.rows);
		contentPane.add(board);
		// History
//		moveHistoryPanel moveHistoryPanel = new moveHistoryPanel();
//		moveHistoryPanel.setBounds(750, 100, 600, 315);
//		contentPane.add(moveHistoryPanel);
		// Chat
		matchFeaturesPanel = new matchFeaturesPanel();
		matchFeaturesPanel.setBounds(906, 92, 600, 700);
		contentPane.add(matchFeaturesPanel);
		
		panelDraw = new JPanel();
		panelDraw.setBackground(new Color(211, 211, 211));
		panelDraw.setVisible(false);
		panelDraw.setBounds(125, 261, 356, 139);
		matchFeaturesPanel.add(panelDraw);
		panelDraw.setLayout(null);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.requestDraw();
				panelDraw.setVisible(false);
			}
		});
		btnYes.setBackground(new Color(0, 255, 0));
		btnYes.setFont(new Font("Arial", Font.PLAIN, 14));
		btnYes.setBounds(50, 97, 108, 32);
		panelDraw.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDraw.setVisible(false);
			}
		});
		btnNo.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNo.setBackground(Color.GREEN);
		btnNo.setBounds(204, 97, 108, 32);
		panelDraw.add(btnNo);
		
		JLabel lblNewLabel_1 = new JLabel("Are you sure to draw?");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(81, 33, 201, 32);
		panelDraw.add(lblNewLabel_1);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setBackground(new Color(0, 191, 255));
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDraw.setVisible(true);
			}
		});
		btnDraw.setBounds(1074, 22, 123, 40);
		contentPane.add(btnDraw);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitMatch(idRoom);
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuit.setBackground(new Color(250, 128, 114));
		btnQuit.setBounds(1258, 22, 123, 40);
		contentPane.add(btnQuit);
		
		
		setLocationRelativeTo(null);
		//
		this.setVisible(true);
	}
	
	public inMatchWithPlayer(String ipServer, int portServer) {
		this.ipServer = ipServer;
		this.portServer = portServer;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chess game");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(718, 10, 279, 70);
		contentPane.add(lblNewLabel);
		// Board
		board = new Board(ipServer, portServer, this);
		board.setBounds(165, 90, board.tileSize*board.cols, board.tileSize*board.rows);
		contentPane.add(board);
		// History
//		moveHistoryPanel moveHistoryPanel = new moveHistoryPanel();
//		moveHistoryPanel.setBounds(750, 100, 600, 315);
//		contentPane.add(moveHistoryPanel);
		// Chat
		matchFeaturesPanel = new matchFeaturesPanel();
		matchFeaturesPanel.setBounds(906, 92, 600, 700);
		contentPane.add(matchFeaturesPanel);
		
//		panelDraw = new JPanel();
//		panelDraw.setBackground(new Color(211, 211, 211));
//		panelDraw.setVisible(false);
//		panelDraw.setBounds(125, 261, 356, 139);
//		matchFeaturesPanel.add(panelDraw);
//		panelDraw.setLayout(null);
//		
//		JButton btnYes = new JButton("Yes");
//		btnYes.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				board.requestDraw();
//				panelDraw.setVisible(false);
//			}
//		});
//		btnYes.setBackground(new Color(0, 255, 0));
//		btnYes.setFont(new Font("Arial", Font.PLAIN, 14));
//		btnYes.setBounds(50, 97, 108, 32);
//		panelDraw.add(btnYes);
//		
//		JButton btnNo = new JButton("No");
//		btnNo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				panelDraw.setVisible(false);
//			}
//		});
//		btnNo.setFont(new Font("Arial", Font.PLAIN, 14));
//		btnNo.setBackground(Color.GREEN);
//		btnNo.setBounds(204, 97, 108, 32);
//		panelDraw.add(btnNo);
		
//		JLabel lblNewLabel_1 = new JLabel("Are you sure to draw?");
//		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setBounds(81, 33, 201, 32);
//		panelDraw.add(lblNewLabel_1);
		
//		JButton btnDraw = new JButton("Draw");
//		btnDraw.setBackground(new Color(0, 191, 255));
//		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		btnDraw.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				panelDraw.setVisible(true);
//			}
//		});
//		btnDraw.setBounds(1074, 22, 123, 40);
//		contentPane.add(btnDraw);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitMatchAI();
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuit.setBackground(new Color(250, 128, 114));
		btnQuit.setBounds(1258, 22, 123, 40);
		contentPane.add(btnQuit);
		
		
		setLocationRelativeTo(null);
		//
		this.setVisible(true);
	}
	
	public void updateHistory(String move) {
		matchFeaturesPanel.updateHistory(move);
	}
	
	public void updateMessage(String msg) {
		matchFeaturesPanel.updateMessage(msg);
	}
	
	public void quitMatch(int idRoom) {
		dispose();
		try {
			board.socket.close();
		} catch (Exception e2) {
			System.out.println(e2.getMessage()+"\tThoat");
		}
		PlayerSession playerSession = PlayerSession.getExistedInstance();
		playerSession.emptyRoom(idRoom);
		new selectMode();
	}
	
	public void quitMatchAI() {
		dispose();
		try {
			board.socket.close();
		} catch (Exception e2) {
			System.out.println(e2.getMessage()+"\tThoat");
		}
		new selectMode();
	}
}