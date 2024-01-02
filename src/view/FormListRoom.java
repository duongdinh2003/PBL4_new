package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Client.PlayerSession;
import GUI_Client.selectMode;
import connectDB.Room;

public class FormListRoom extends JFrame {

	private JPanel contentPane;
	public List<Room> listRoom;
	public int id_room;
//	public Lobby lobby;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormListRoom frame = new FormListRoom();
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
	public FormListRoom(List<String> listRoomStr) {
		setTitle("List Room");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 608);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 855, 551);
		contentPane.add(panel);
		
		createListRoom(listRoomStr);
		addListRoom(panel);
	}
	
	public void createListRoom(List<String> listRoomStr) {
		listRoom = new ArrayList<Room>();
		for (int i=0; i<listRoomStr.size(); i++) {
			String roomStr[] = listRoomStr.get(i).split(",");
			int id = Integer.parseInt(roomStr[0]);
			String name = roomStr[1];
			int st = Integer.parseInt(roomStr[2]);
			boolean status = st == 1 ? true : false;
			int count = Integer.parseInt(roomStr[3]);
			listRoom.add(new Room(id, name, count, status));
		}
		
//		listRoom = new ArrayList<Room>();
//		
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//			Connection con = DriverManager.getConnection(url, "sa", "Binben2022@");
//			
//			String query = "select ID_Room, Name_Room, status_Room, current_player_count from Room";
//			PreparedStatement stm = con.prepareStatement(query);
//			
//			ResultSet rs = stm.executeQuery();
//			while (rs.next()) {
//				int id = rs.getInt("ID_Room");
//				String name = rs.getString("Name_Room");
//				int st = rs.getInt("status_Room");
//				boolean status = st == 1 ? true : false;
//				int count = rs.getInt("current_player_count");
//				listRoom.add(new Room(id, name, count, status));
//			}
//			
//			stm.close();
//			con.close();
			
//			Socket socket = new Socket("localhost", 5678);
//			DataInputStream dis = new DataInputStream(socket.getInputStream());
//			int sl = Integer.parseInt(dis.readUTF());
//			for (int i=0; i<sl; i++) {
//				String room = dis.readUTF();
//				String[] roomAttri = room.split(",");
//				int id = Integer.parseInt(roomAttri[0]);
//				int st = Integer.parseInt(roomAttri[1]);
//				boolean status = st == 1 ? true : false;
//				int count = Integer.parseInt(roomAttri[2]);
//				listRoom.add(new Room(id, status, count));
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	public void addListRoom(JPanel panel) {
		panel.setLayout(new GridLayout(3, 3));
		
		for (Room room : listRoom) {
			JButton btnRoom = new JButton("Room " + room.getIdRoom() + " Player: "+room.getPlayerNumber()+"/2");
			btnRoom.setFont(new Font("Tahoma", Font.PLAIN, 24));
			
			if (room.isStatusRoom()) {
				btnRoom.setBackground(Color.RED);
			} else {
				btnRoom.setBackground(Color.GREEN);
			}
			
			btnRoom.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (btnRoom.getBackground().equals(Color.RED)) {
						return;
					} else {
						try {
//							room.setPlayerNumber(room.getPlayerNumber()+1);
//							
//							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//							String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//							Connection conn = DriverManager.getConnection(url, "sa", "Binben2022@");
//							
//							String query = "update Room set current_player_count = "+room.getPlayerNumber()
//											+" where ID_Room = "+room.getIdRoom();
//							Statement stm = conn.createStatement();
//							stm.execute(query);
							
//							String query1 = "select current_player_count from Room where ID_Room ="+room.getIDRoom();
//							ResultSet rs = stm.executeQuery(query1);
//							
//							int count = 0;
//							while (rs.next()) {
//								count = rs.getInt("current_player_count");
//							}
//							btnRoom.setText("Room " + room.getIdRoom() + "\nPlayer: "+room.getPlayerNumber()+"/2");
							
//							rs.close();
//							stm.close();
//							conn.close();
							id_room = room.getIdRoom();
							
							dispose();
//							setVisible(false);
							
							PlayerSession playerSession = PlayerSession.getExistedInstance();
							playerSession.enterRoom(id_room);
							Lobby lobby = new Lobby(id_room);
//							if (room.getPlayerNumber() < 2) {
//								lobby.btnStart.setEnabled(false);
//							}
							lobby.show();
//							lobby.setVisible(true);
							
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}
					}
				}
			});
			
			panel.add(btnRoom);
			
			
		}
		
		JButton btnReturnHome = new JButton("Return Home");
		btnReturnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
//				HomePage home = new HomePage();
//				home.show();
				new selectMode();
			}
		});
		panel.add(btnReturnHome);
	}
	
//	private static int getCurrentNumberPlayer(int idRoom) {
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//			Connection con = DriverManager.getConnection(url, "sa", "Binben2022@");
//			
//			String query = "select ID_Room, status_Room, current_player_count from Room where ID_Room = "+idRoom;
//			Statement stm = con.createStatement();
//			ResultSet rs = stm.executeQuery(query);
//			if (rs.next()) {
//				int num = rs.getInt("current_player_count");
//				return num;
//			}
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return 0;
//	}
	
//	private static class MySwingWorker extends SwingWorker<Void, Integer> {
//
//		@Override
//		protected Void doInBackground() throws Exception {
//			int num = FormListRoom.getCurrentNumberPlayer(1);
//			publish(num);
//			return null;
//		}
//		
//	}
	
//	public void updateRoom(int id) {
//		int sl = 0;
//		for (Room room : listRoom) {
//			if (room.getIdRoom() == id) {
//				sl = room.getPlayerNumber();
//				break;
//			}
//		}
//		sl--;
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//			Connection conn = DriverManager.getConnection(url, "sa", "Binben2022@");
//			
//			String query = "update Room set current_player_count = "+sl
//							+" where ID_Room = "+id;
//			Statement stm = conn.createStatement();
//			stm.execute(query);
//			
//			stm.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}

//class Room {
//	int idRoom;
//	boolean statusRoom;
//	int playerCount = 0;
//	
//	public Room(int id, boolean status, int playerCount) {
//		this.idRoom = id;
//		this.statusRoom = status;
//		this.playerCount = playerCount;
//	}
//	
//	public int getIDRoom() {
//		return idRoom;
//	}
//	
//	public boolean getStatusRoom() {
//		return statusRoom;
//	}
//	
//	public int getPlayerCount() {
//		return playerCount;
//	}
//	
//	public void setPlayerCount(int count) {
//		this.playerCount = count;
//	}
//}
