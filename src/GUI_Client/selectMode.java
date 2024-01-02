package GUI_Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.FormListRoom;
import view.FormLogin;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class selectMode extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectMode frame = new selectMode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public selectMode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Play with friend");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				new inMatchWithPlayer("localhost", 4444);
//				FormListRoom room = new FormListRoom();
//				room.show();
				
				PlayerSession playerSession = PlayerSession.getExistedInstance();
				List<String> listRoom = playerSession.requestGetRoomList();
				FormListRoom room = new FormListRoom(listRoom);
				room.show();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(644, 191, 257, 63);
		contentPane.add(btnNewButton);
		
		JButton btnPlayWithAi = new JButton("Play with AI");
		btnPlayWithAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				new inMatchWithPlayer("localhost", 6666);
			}
		});
		btnPlayWithAi.setForeground(Color.BLACK);
		btnPlayWithAi.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPlayWithAi.setBackground(new Color(0, 128, 128));
		btnPlayWithAi.setBounds(644, 268, 257, 63);
		contentPane.add(btnPlayWithAi);
		
		JButton btnQuickPlay = new JButton("Quick Play");
		btnQuickPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LobbyQuickPlay("localhost", 8888);
			}
		});
		btnQuickPlay.setForeground(Color.BLACK);
		btnQuickPlay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnQuickPlay.setBackground(new Color(0, 128, 128));
		btnQuickPlay.setBounds(644, 345, 257, 63);
		contentPane.add(btnQuickPlay);
		
		JLabel lblLogOut = new JLabel("");
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PlayerSession playerSession = PlayerSession.getExistedInstance();
				playerSession.closeSession();
				
				dispose();
				FormLogin login = new FormLogin();
				login.show();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLogOut.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLogOut.setIcon(new ImageIcon(selectMode.class.getResource("/res/icons8-logout-50.png")));
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setBounds(1397, 24, 133, 50);
		contentPane.add(lblLogOut);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Truong Quang Loc\\OneDrive\\Hình ảnh\\Get the We Heart It app!.jpg"));
//		lblNewLabel.setBounds(55, 75, 820, 496);
//		contentPane.add(lblNewLabel);
		//bg
//		ImageIcon backgroundIcon = new ImageIcon("C:\\\\Users\\\\Truong Quang Loc\\\\OneDrive\\\\Hình ảnh\\\\Get the We Heart It app!.jpg"); // Replace with the actual path to your image

//        JLabel backgroundLabel = new JLabel();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(true); // Optional: setResizable to false if you don't want the user to resize the frame
        setLocationRelativeTo(null);
		setVisible(true);
	}
}