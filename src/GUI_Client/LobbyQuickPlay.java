package GUI_Client;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LobbyQuickPlay extends JFrame implements Runnable {

	private JPanel contentPane;
	private Timer timer;
	private int seconds;
	private JLabel lblNotification;
	private JButton btnStartGame;
	String ipServer;
	int portServer;
	
	Socket socket;
	
	public inMatchWithPlayer matchWithPlayer;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LobbyQuickPlay frame = new LobbyQuickPlay();
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
	public LobbyQuickPlay(String ipServer, int portServer) {
		this.ipServer = ipServer;
		this.portServer = portServer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContent = new JLabel("Waiting for other player...");
		lblContent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContent.setBounds(169, 95, 237, 37);
		contentPane.add(lblContent);
		
		JLabel lblCountTimer = new JLabel("00:00:00");
		lblCountTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCountTimer.setBounds(169, 152, 237, 31);
		contentPane.add(lblCountTimer);
		
		lblNotification = new JLabel();
		lblNotification.setVisible(false);
		lblNotification.setBounds(100, 209, 400, 37);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		btnStartGame = new JButton("Start");
		btnStartGame.setBounds(250, 280, 100, 40);
		btnStartGame.setBackground(Color.GREEN);
		btnStartGame.setVisible(false);
		btnStartGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				matchWithPlayer = new inMatchWithPlayer(ipServer, 4444);
			}
		});
		
		contentPane.add(lblNotification);
		contentPane.add(btnStartGame);
		
		timer = new Timer();
		seconds = 0;
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				updateTimer(lblCountTimer);
			}
		}, 0, 1000);
		
		setVisible(true);
		
		try {
			this.socket = new Socket(ipServer, portServer);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Quick play");
			Thread t = new Thread(this);
			t.start();
		} catch (Exception e) {
			System.out.println("Loi ket noi server 8888");
			dispose();
		}
	}
	
	private void updateTimer(JLabel label) {
		SwingUtilities.invokeLater(() -> {
			seconds++;
			String formattedTime = formatTime(seconds);
			label.setText(formattedTime);
		});
	}
	
	private String formatTime(int seconds) {
		int hour = seconds / 3600;
		int minute = (seconds % 3600) / 60;
		int second = seconds % 60;
		
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}

	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String noti = dis.readUTF();
			System.out.println("Response from server: "+noti);
			if (noti.equals("Found!")) {
				lblNotification.setText("Found the enemy! Please click Start to Play!");
				lblNotification.setVisible(true);
				btnStartGame.setVisible(true);
			}
			
		} catch (Exception e) {
			System.out.println("khong nhan duoc phan hoi tu server");
		}
		while (true) {
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				String msg = dis.readUTF();
				matchWithPlayer.updateMessage("You: "+msg);
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\tLoi khong gui tin nhan duoc tu Client -> Server");
				JOptionPane.showMessageDialog(this ,"Lost connection to server!");
				this.dispose();
				new selectMode();
				break;
			}
		}
	}
}
