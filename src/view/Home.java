package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setPreferredSize(new Dimension(700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(10, 10));
		contentPane.setMaximumSize(new Dimension(32767, 32767));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon originalIcon = new ImageIcon(Home.class.getResource("/img/brg.png"));
		Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(650, 450, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
		
		JButton btnPlayWithFriend = new JButton("Play with Friend");
		btnPlayWithFriend.setForeground(Color.WHITE);
		btnPlayWithFriend.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		btnPlayWithFriend.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnPlayWithFriend.setBackground(new Color(51, 102, 0));
		btnPlayWithFriend.setAlignmentY(1.0f);
		btnPlayWithFriend.setAlignmentX(1.0f);
		btnPlayWithFriend.setBounds(142, 81, 156, 40);
		contentPane.add(btnPlayWithFriend);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setForeground(Color.WHITE);
		btnPlay.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		btnPlay.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnPlay.setBackground(new Color(51, 102, 0));
		btnPlay.setAlignmentY(1.0f);
		btnPlay.setAlignmentX(1.0f);
		btnPlay.setBounds(142, 21, 156, 40);
		contentPane.add(btnPlay);
		
		JButton btnPlayWithComputer = new JButton("Play with Computer");
		btnPlayWithComputer.setForeground(Color.WHITE);
		btnPlayWithComputer.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		btnPlayWithComputer.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnPlayWithComputer.setBackground(new Color(51, 102, 0));
		btnPlayWithComputer.setAlignmentY(1.0f);
		btnPlayWithComputer.setAlignmentX(1.0f);
		btnPlayWithComputer.setBounds(142, 144, 156, 40);
		contentPane.add(btnPlayWithComputer);
		
		JButton btnMatchHistory = new JButton("Match History");
		btnMatchHistory.setForeground(Color.WHITE);
		btnMatchHistory.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		btnMatchHistory.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnMatchHistory.setBackground(new Color(255, 51, 51));
		btnMatchHistory.setAlignmentY(1.0f);
		btnMatchHistory.setAlignmentX(1.0f);
		btnMatchHistory.setBounds(32, 212, 140, 40);
		contentPane.add(btnMatchHistory);
		
		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.setForeground(Color.WHITE);
		btnLeaderboard.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		btnLeaderboard.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnLeaderboard.setBackground(new Color(255, 51, 51));
		btnLeaderboard.setAlignmentY(1.0f);
		btnLeaderboard.setAlignmentX(1.0f);
		btnLeaderboard.setBounds(246, 212, 140, 40);
		contentPane.add(btnLeaderboard);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(resizedIcon);
		lblNewLabel_1.setBounds(0, 0, 436, 263);
		contentPane.add(lblNewLabel_1);
				
	}
}