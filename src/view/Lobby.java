package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Client.PlayerSession;
import GUI_Client.inMatchWithPlayer;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Lobby extends JFrame {

	private JPanel contentPane;
	public JButton btnStart;
//	private FormListRoom formListRoom;
	int idRoom;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Lobby frame = new Lobby();
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
	public Lobby(int id) {
		idRoom = id;
		setTitle(id+"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 434);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 319, 532, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Board board = new Board();
//				FormMatch match = new FormMatch(board);
//				match.show();
				dispose();
				
				PlayerSession playerSession = PlayerSession.getExistedInstance();
				playerSession.kickOffMatch(id);
				
				new inMatchWithPlayer(id, "localhost", 4444);
			}
		});
		btnStart.setBackground(new Color(173, 255, 47));
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStart.setBounds(119, 10, 136, 35);
		panel.add(btnStart);
		
		JButton btnExit = new JButton("Exit");
//		formListRoom.createListRoom();
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					Room room = getRoom(id);
////					room.setPlayerCount(room.getPlayerCount()-1);
//					int sl = room.getPlayerCount()-1;
//					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//					String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//					Connection con = DriverManager.getConnection(url, "sa", "Binben2022@");
//					
//					String query = "update Room set current_player_count = "+sl  //room.getPlayerCount()
//									+" where ID_Room = "+id;
//					Statement stm = con.createStatement();
//					stm.execute(query);
//					
//					stm.close();
//					con.close();
					
//					formListRoom.updateRoom(id);
					dispose();
//					setVisible(false);
					
					
//					FormListRoom formListRoom = new FormListRoom();
//					formListRoom.updateRoom(id);
//					formListRoom.show();
					
					PlayerSession playerSession = PlayerSession.getExistedInstance();
					playerSession.exitRoom(id);
					List<String> listRoom = playerSession.requestGetRoomList();
					FormListRoom room = new FormListRoom(listRoom);
					room.show();
					
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBackground(new Color(240, 128, 128));
		btnExit.setBounds(281, 10, 136, 35);
		panel.add(btnExit);
	}
	
//	public Room getRoom(int id) {
//		for (Room room : formListRoom.listRoom) {
//			if (room.getIDRoom() == id) {
//				return room;
//			}
//		}
//		return null;
//	}

}
