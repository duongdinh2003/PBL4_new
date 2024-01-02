package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Client.inMatchWithPlayer;
import chessEngine.Board;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 661);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSlideBar = new JPanel();
		panelSlideBar.setBackground(new Color(240, 248, 255));
		panelSlideBar.setBounds(10, 10, 1, 604);
		contentPane.add(panelSlideBar);
		panelSlideBar.setLayout(null);
		
		JButton btnPlayWithFriend = new JButton("Play With Friend");
		btnPlayWithFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				setVisible(false);
//				FormListRoom f = new FormListRoom();
//				f.setVisible(true);
//				f.show();
			}
		});
		btnPlayWithFriend.setBackground(new Color(0, 255, 127));
		btnPlayWithFriend.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPlayWithFriend.setBounds(66, 111, 205, 43);
		panelSlideBar.add(btnPlayWithFriend);
		
		JButton btnPlayWithComputer = new JButton("Play With Computer");
		btnPlayWithComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Board board = new Board();
				FormMatch match = new FormMatch(board);
				match.show();
			}
		});
		btnPlayWithComputer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPlayWithComputer.setBackground(new Color(0, 255, 127));
		btnPlayWithComputer.setBounds(66, 206, 205, 43);
		panelSlideBar.add(btnPlayWithComputer);
		
		JButton btnQuickPlay = new JButton("Quick Play");
		btnQuickPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				main.Board board = new main.Board();
//				FormMatch match = new FormMatch(board);
//				match.show();
				
				inMatchWithPlayer match = new inMatchWithPlayer("localhost", 4444);
				match.show();
			}
		});
		btnQuickPlay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuickPlay.setBackground(new Color(0, 255, 127));
		btnQuickPlay.setBounds(66, 302, 205, 43);
		panelSlideBar.add(btnQuickPlay);
		
		JLabel lblCloseSlideBar = new JLabel("");
		lblCloseSlideBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeMenuBar(panelSlideBar);
			}
		});
		lblCloseSlideBar.setIcon(new ImageIcon(HomePage.class.getResource("/res/icons8-previous-50.png")));
		lblCloseSlideBar.setBounds(260, 26, 50, 50);
		panelSlideBar.add(lblCloseSlideBar);
		
		JPanel panelInfor = new JPanel();
		panelInfor.setBackground(new Color(222, 184, 135));
		panelInfor.setBounds(10, 10, 951, 604);
		contentPane.add(panelInfor);
		panelInfor.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Let's Play Chess");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(304, 23, 343, 42);
		panelInfor.add(lblNewLabel);
		
		JLabel lblOpenSlideBar = new JLabel("");
		lblOpenSlideBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openMenuBar(panelSlideBar);
			}
		});
		lblOpenSlideBar.setIcon(new ImageIcon(HomePage.class.getResource("/res/icons8-next-50.png")));
		lblOpenSlideBar.setBounds(25, 74, 50, 50);
		panelInfor.add(lblOpenSlideBar);
	}
	
	private void openMenuBar(JPanel panelSlideBar) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<340; i++) {
					panelSlideBar.setSize(i, 604);
					try {
						Thread.sleep(2);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void closeMenuBar(JPanel panelSlideBar) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=340; i>=0; i--) {
					panelSlideBar.setSize(i, 604);
					try {
						Thread.sleep(2);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}).start();
	}
}
