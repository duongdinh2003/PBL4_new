package GUI_Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChessServer.ChessServer;
import ChessServer.ChessServer2;
import ChessServer.ChessServer3;
import ChessServer.ConnectDBServer;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class serverManagerForm extends JFrame {

	private JPanel contentPane;
	// Server
	ChessServer chessServer;
	ChessServer2 chessServer2;
	ChessServer3 chessServer3;
	ConnectDBServer chessServerDB;
	// Server1
	static JLabel lblServer1Con;
	static JLabel lblServer1Sta;
	static JComboBox<String> comboBoxServer1;
	static DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	static JLabel lblServer1Noti;
	// Server2
	static JLabel lblServer2Con;
	static JLabel lblServer2Sta;
	static JComboBox<String> comboBoxServer2;
	static DefaultComboBoxModel<String> comboBoxModel2 = new DefaultComboBoxModel<>();
	static JLabel lblServer2Noti;
	// Server3
	static JLabel lblServer3Con;
	static JLabel lblServer3Sta;
	static JComboBox<String> comboBoxServer3;
	static DefaultComboBoxModel<String> comboBoxModel3 = new DefaultComboBoxModel<>();
	static JLabel lblServer3Noti;
	// ServerDB
	static JLabel lblServerDBCon;
	static JLabel lblServerDBSta;
	static JComboBox<String> comboBoxServerDB;
	static DefaultComboBoxModel<String> comboBoxModelDB = new DefaultComboBoxModel<>();
	static JLabel lblServerDBNoti;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverManagerForm frame = new serverManagerForm();
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
	public serverManagerForm() {
		chessServer = new ChessServer();
		Thread thread = new Thread(() -> {
            chessServer.startServer();
        });
		
		chessServer2 = new ChessServer2();
		Thread thread2 = new Thread(() -> {
            chessServer2.startServer();
        });
		
		chessServer3 = new ChessServer3();
		Thread thread3 = new Thread(() -> {
            chessServer3.startServer();
        });
		
		chessServerDB = new ConnectDBServer();
		Thread threadDB = new Thread(() -> {
			chessServerDB.startServer();
        });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server Manager");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 36));
		lblNewLabel.setBounds(612, 37, 296, 73);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server 1");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(195, 141, 132, 56);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Status:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(195, 241, 75, 32);
		contentPane.add(lblNewLabel_2);

		
		JLabel lblNewLabel_2_1 = new JLabel("Connection:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(195, 283, 121, 32);
		contentPane.add(lblNewLabel_2_1);
		
		lblServer1Sta = new JLabel("off");
		lblServer1Sta.setForeground(new Color(255, 0, 0));
		lblServer1Sta.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Sta.setBounds(280, 241, 84, 32);
		contentPane.add(lblServer1Sta);
		
		lblServer1Con = new JLabel("0");
		lblServer1Con.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Con.setBounds(326, 283, 84, 32);
		contentPane.add(lblServer1Con);
		
		JLabel lblDes = new JLabel("Description:");
		lblDes.setFont(new Font("Arial", Font.BOLD, 20));
		lblDes.setBounds(195, 199, 121, 32);
		contentPane.add(lblDes);
		
		JLabel lblNewLabel_1_1 = new JLabel("Server 2");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(915, 141, 132, 56);
		contentPane.add(lblNewLabel_1_1);
		
		
		
		JLabel lblNewLabel_2_3 = new JLabel("Status:");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_3.setBounds(915, 241, 75, 32);
		contentPane.add(lblNewLabel_2_3);
		
		lblServer2Sta = new JLabel("off");
		lblServer2Sta.setForeground(new Color(255, 0, 0));
		lblServer2Sta.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Sta.setBounds(1000, 241, 84, 32);
		contentPane.add(lblServer2Sta);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Connection:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_1.setBounds(915, 283, 121, 32);
		contentPane.add(lblNewLabel_2_1_1);
		
		lblServer2Con = new JLabel("0");
		lblServer2Con.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Con.setBounds(1046, 283, 84, 32);
		contentPane.add(lblServer2Con);
		
		JLabel lblServer1Des = new JLabel("player with player mode");
		lblServer1Des.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Des.setBounds(326, 199, 237, 32);
		contentPane.add(lblServer1Des);
		
		JLabel lblDes_1 = new JLabel("Description:");
		lblDes_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblDes_1.setBounds(915, 201, 121, 32);
		contentPane.add(lblDes_1);
		
		JLabel lblServer2Des = new JLabel("chess engine mode");
		lblServer2Des.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Des.setBounds(1046, 201, 237, 32);
		contentPane.add(lblServer2Des);
		// btnServer1Start
		JButton btnServer1Start = new JButton("Start");
		btnServer1Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        thread.start();
		        lblServer1Sta.setText("running");
		        lblServer1Sta.setForeground(Color.GREEN);
			}
		});
		
		btnServer1Start.setBackground(new Color(0, 128, 64));
		btnServer1Start.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer1Start.setBounds(337, 155, 84, 32);
		contentPane.add(btnServer1Start);
		
		// btnServer1Stop
		JButton btnServer1Stop = new JButton("Stop");
		btnServer1Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer.stopServer();
			}
		});
		btnServer1Stop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer1Stop.setBackground(new Color(255, 0, 0));
		btnServer1Stop.setBounds(435, 155, 84, 32);
		contentPane.add(btnServer1Stop);
		
		// btnServer1CloseAllConnections
		JButton btnServer1CloseAllConnections = new JButton("Close all connections");
		btnServer1CloseAllConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer.closeAllConnections();
			}
		});
		btnServer1CloseAllConnections.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer1CloseAllConnections.setBackground(new Color(255, 128, 0));
		btnServer1CloseAllConnections.setBounds(377, 283, 260, 32);
		contentPane.add(btnServer1CloseAllConnections);
		
		// btnServer2Start
		JButton btnServer2Start = new JButton("Start");
		btnServer2Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        thread2.start();
		        lblServer2Sta.setText("running");
		        lblServer2Sta.setForeground(Color.GREEN);
			}
		});
		btnServer2Start.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer2Start.setBackground(new Color(0, 128, 64));
		btnServer2Start.setBounds(1057, 155, 84, 32);
		contentPane.add(btnServer2Start);
		// btnServer2Stop
		JButton btnServer2Stop = new JButton("Stop");
		btnServer2Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer2.stopServer();
			}
		});
		btnServer2Stop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer2Stop.setBackground(Color.RED);
		btnServer2Stop.setBounds(1155, 155, 84, 32);
		contentPane.add(btnServer2Stop);
		
		// btnServer2CloseAllConnections
		JButton btnServer2CloseAllConnections = new JButton("Close all connections");
		btnServer2CloseAllConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer2.closeAllConnections();
			}
		});
		btnServer2CloseAllConnections.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer2CloseAllConnections.setBackground(new Color(255, 128, 0));
		btnServer2CloseAllConnections.setBounds(1105, 283, 260, 32);
		contentPane.add(btnServer2CloseAllConnections);
		
		comboBoxServer1 = new JComboBox();
		comboBoxServer1.setBounds(361, 343, 202, 32);
		contentPane.add(comboBoxServer1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("List connection:");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_2.setBounds(195, 340, 171, 32);
		contentPane.add(lblNewLabel_2_1_2);
		
		JButton btnRemoveServer1 = new JButton("Remove");
		btnRemoveServer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ipToRemove = comboBoxServer1.getSelectedItem().toString();
				System.out.println(ipToRemove);
				if(chessServer.removeClient(ipToRemove)) {
					lblServer1Noti.setText("Removed " + ipToRemove);
				} else {
					System.out.println("False");
				}
			}
		});
		btnRemoveServer1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRemoveServer1.setBackground(new Color(255, 128, 0));
		btnRemoveServer1.setBounds(573, 343, 109, 32);
		contentPane.add(btnRemoveServer1);
		
		lblServer1Noti = new JLabel("Notification:");
		lblServer1Noti.setForeground(Color.RED);
		lblServer1Noti.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Noti.setBounds(361, 385, 202, 32);
		contentPane.add(lblServer1Noti);
		
		JLabel lblNewLabel_1_2 = new JLabel("Server 3");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_2.setBounds(195, 469, 132, 56);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnServer3Start = new JButton("Start");
		btnServer3Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread3.start();
		        lblServer3Sta.setText("running");
		        lblServer3Sta.setForeground(Color.GREEN);
			}
		});
		btnServer3Start.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer3Start.setBackground(new Color(0, 128, 64));
		btnServer3Start.setBounds(337, 483, 84, 32);
		contentPane.add(btnServer3Start);
		
		JButton btnServer3Stop = new JButton("Stop");
		btnServer3Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer3.stopServer();
			}
		});
		btnServer3Stop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer3Stop.setBackground(Color.RED);
		btnServer3Stop.setBounds(435, 483, 84, 32);
		contentPane.add(btnServer3Stop);
		
		JLabel lblDes_2 = new JLabel("Description:");
		lblDes_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblDes_2.setBounds(195, 527, 121, 32);
		contentPane.add(lblDes_2);
		
		JLabel lblQuickPlayMode = new JLabel("quick play mode");
		lblQuickPlayMode.setFont(new Font("Arial", Font.BOLD, 20));
		lblQuickPlayMode.setBounds(326, 527, 237, 32);
		contentPane.add(lblQuickPlayMode);
		
		JLabel lblNewLabel_2_2 = new JLabel("Status:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(195, 569, 75, 32);
		contentPane.add(lblNewLabel_2_2);
		
		lblServer3Sta = new JLabel("off");
		lblServer3Sta.setForeground(Color.RED);
		lblServer3Sta.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer3Sta.setBounds(280, 569, 84, 32);
		contentPane.add(lblServer3Sta);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Connection:");
		lblNewLabel_2_1_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_3.setBounds(195, 611, 121, 32);
		contentPane.add(lblNewLabel_2_1_3);
		
		lblServer3Con = new JLabel("0");
		lblServer3Con.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer3Con.setBounds(326, 611, 84, 32);
		contentPane.add(lblServer3Con);
		
		JButton btnServer3CloseAllConnections = new JButton("Close all connections");
		btnServer3CloseAllConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer3.closeAllConnections();
			}
		});
		btnServer3CloseAllConnections.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer3CloseAllConnections.setBackground(new Color(255, 128, 0));
		btnServer3CloseAllConnections.setBounds(377, 611, 260, 32);
		contentPane.add(btnServer3CloseAllConnections);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("List connection:");
		lblNewLabel_2_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_2_1.setBounds(195, 668, 171, 32);
		contentPane.add(lblNewLabel_2_1_2_1);
		
		comboBoxServer3 = new JComboBox();
		comboBoxServer3.setBounds(361, 671, 202, 32);
		contentPane.add(comboBoxServer3);
		
		JButton btnRemoveServer3 = new JButton("Remove");
		btnRemoveServer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ipToRemove3 = comboBoxServer3.getSelectedItem().toString();
				if(chessServer3.removeClient(ipToRemove3)) {
					lblServer3Noti.setText("Removed " + ipToRemove3);
				} else {
					System.out.println("False");
				}
			}
		});
		btnRemoveServer3.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRemoveServer3.setBackground(new Color(255, 128, 0));
		btnRemoveServer3.setBounds(573, 671, 109, 32);
		contentPane.add(btnRemoveServer3);
		
		JLabel lblServer3Noti = new JLabel("Notification:");
		lblServer3Noti.setForeground(Color.RED);
		lblServer3Noti.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer3Noti.setBounds(361, 713, 202, 32);
		contentPane.add(lblServer3Noti);
		
		JLabel lblNewLabel_1_3 = new JLabel("Server DB");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_3.setBounds(915, 469, 152, 56);
		contentPane.add(lblNewLabel_1_3);
		
		JButton btnServerDBStart = new JButton("Start");
		btnServerDBStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadDB.start();
		        lblServerDBSta.setText("running");
		        lblServerDBSta.setForeground(Color.GREEN);
			}
		});
		btnServerDBStart.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServerDBStart.setBackground(new Color(0, 128, 64));
		btnServerDBStart.setBounds(1074, 483, 84, 32);
		contentPane.add(btnServerDBStart);
		
		JButton btnServerDBStop = new JButton("Stop");
		btnServerDBStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServerDB.stopServer();
			}
		});
		btnServerDBStop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServerDBStop.setBackground(Color.RED);
		btnServerDBStop.setBounds(1172, 483, 84, 32);
		contentPane.add(btnServerDBStop);
		
		JLabel lblDes_3 = new JLabel("Description:");
		lblDes_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblDes_3.setBounds(915, 527, 121, 32);
		contentPane.add(lblDes_3);
		
		JLabel lblServerDBDes = new JLabel("database manager");
		lblServerDBDes.setFont(new Font("Arial", Font.BOLD, 20));
		lblServerDBDes.setBounds(1046, 527, 237, 32);
		contentPane.add(lblServerDBDes);
		
		JLabel lblNewLabel_2_4 = new JLabel("Status:");
		lblNewLabel_2_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_4.setBounds(915, 569, 75, 32);
		contentPane.add(lblNewLabel_2_4);
		
		lblServerDBSta = new JLabel("off");
		lblServerDBSta.setForeground(Color.RED);
		lblServerDBSta.setFont(new Font("Arial", Font.BOLD, 20));
		lblServerDBSta.setBounds(1000, 569, 84, 32);
		contentPane.add(lblServerDBSta);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("Connection:");
		lblNewLabel_2_1_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_4.setBounds(915, 611, 121, 32);
		contentPane.add(lblNewLabel_2_1_4);
		
		lblServerDBCon = new JLabel("0");
		lblServerDBCon.setFont(new Font("Arial", Font.BOLD, 20));
		lblServerDBCon.setBounds(1046, 611, 84, 32);
		contentPane.add(lblServerDBCon);
		
		JButton btnServerDBCloseAllConnections = new JButton("Close all connections");
		btnServerDBCloseAllConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServerDB.closeAllConnections();
			}
		});
		btnServerDBCloseAllConnections.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServerDBCloseAllConnections.setBackground(new Color(255, 128, 0));
		btnServerDBCloseAllConnections.setBounds(1097, 611, 260, 32);
		contentPane.add(btnServerDBCloseAllConnections);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("List connection:");
		lblNewLabel_2_1_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_2_2.setBounds(915, 668, 171, 32);
		contentPane.add(lblNewLabel_2_1_2_2);
		
		comboBoxServerDB = new JComboBox();
		comboBoxServerDB.setBounds(1081, 671, 202, 32);
		contentPane.add(comboBoxServerDB);
		
		JButton btnRemoveServerDB = new JButton("Remove");
		btnRemoveServerDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ipToRemoveDB = comboBoxServerDB.getSelectedItem().toString();
				if(chessServerDB.removeClient(ipToRemoveDB)) {
					lblServerDBNoti.setText("Removed " + ipToRemoveDB);
				} else {
					System.out.println("False");
				}
			}
		});
		btnRemoveServerDB.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRemoveServerDB.setBackground(new Color(255, 128, 0));
		btnRemoveServerDB.setBounds(1293, 671, 109, 32);
		contentPane.add(btnRemoveServerDB);
		
		JLabel lblServerDBNoti = new JLabel("Notification:");
		lblServerDBNoti.setForeground(Color.RED);
		lblServerDBNoti.setFont(new Font("Arial", Font.BOLD, 20));
		lblServerDBNoti.setBounds(1081, 713, 202, 32);
		contentPane.add(lblServerDBNoti);
		
		JLabel lblNewLabel_2_1_2_3 = new JLabel("List connection:");
		lblNewLabel_2_1_2_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_2_3.setBounds(915, 340, 171, 32);
		contentPane.add(lblNewLabel_2_1_2_3);
		
		comboBoxServer2 = new JComboBox();
		comboBoxServer2.setBounds(1081, 343, 202, 32);
		contentPane.add(comboBoxServer2);
		
		JButton btnRemoveServer2 = new JButton("Remove");
		btnRemoveServer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ipToRemove2 = comboBoxServer2.getSelectedItem().toString();
				if(chessServer2.removeClient(ipToRemove2)) {
					lblServer2Noti.setText("Removed " + ipToRemove2);
				} else {
					System.out.println("False");
				}
			}
		});
		btnRemoveServer2.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRemoveServer2.setBackground(new Color(255, 128, 0));
		btnRemoveServer2.setBounds(1293, 343, 109, 32);
		contentPane.add(btnRemoveServer2);
		
		JLabel lblServer2Noti = new JLabel("Notification:");
		lblServer2Noti.setForeground(Color.RED);
		lblServer2Noti.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Noti.setBounds(1081, 385, 202, 32);
		contentPane.add(lblServer2Noti);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
//	public static void updateServer1(String connections) {
//		lblServer1Con.setText(connections);
//	}
	public static void updateServer1(ArrayList<String> arrayList) {
		lblServer1Con.setText(arrayList.size()+"");
		comboBoxModel.removeAllElements();
		for (String address : arrayList) {
			comboBoxModel.addElement(address);
		}
		comboBoxServer1.setModel(comboBoxModel);
	}
	
	public static void updateServer2(ArrayList<String> arrayList) {
		lblServer2Con.setText(arrayList.size()+"");
		comboBoxModel2.removeAllElements();
		for (String address : arrayList) {
			comboBoxModel2.addElement(address);
		}
		comboBoxServer2.setModel(comboBoxModel2);
	}
	
	public static void updateServer3(ArrayList<String> arrayList) {
		lblServer3Con.setText(arrayList.size()+"");
		comboBoxModel3.removeAllElements();
		for (String address : arrayList) {
			comboBoxModel3.addElement(address);
		}
		comboBoxServer3.setModel(comboBoxModel3);
	}
	
	public static void updateServerDB(ArrayList<String> arrayList) {
		lblServerDBCon.setText(arrayList.size()+"");
		comboBoxModelDB.removeAllElements();
		for (String address : arrayList) {
			comboBoxModelDB.addElement(address);
		}
		comboBoxServerDB.setModel(comboBoxModelDB);
	}
}