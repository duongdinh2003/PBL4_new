package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI_Client.PlayerSession;
import GUI_Client.selectMode;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FormLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JTextField tfUserSignUp;
	private JTextField tfEnterName;
	private JPasswordField pwfPassSignUp;
	private JPasswordField pwfConfirmPass;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormLogin frame = new FormLogin();
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
	public FormLogin() {
		setTitle("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 614);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(0, 0, 0, 80));
		panelLogin.setBounds(100, 83, 737, 458);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JPanel panelSignUp = new JPanel();
		panelSignUp.setBackground(new Color(0, 0, 0, 80));
		panelSignUp.setBounds(837, 83, 1, 458);
		contentPane.add(panelSignUp);
		panelSignUp.setLayout(null);
		
		JLabel lblUserSignUp = new JLabel("Username");
		lblUserSignUp.setForeground(new Color(255, 255, 255));
		lblUserSignUp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserSignUp.setBounds(52, 36, 178, 35);
		panelSignUp.add(lblUserSignUp);
		
		JLabel lblPassSignUp = new JLabel("Password");
		lblPassSignUp.setForeground(new Color(255, 255, 255));
		lblPassSignUp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassSignUp.setBounds(52, 97, 178, 35);
		panelSignUp.add(lblPassSignUp);
		
		JLabel lblConfirmPass = new JLabel("Confirm password");
		lblConfirmPass.setForeground(new Color(255, 255, 255));
		lblConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmPass.setBounds(52, 165, 178, 35);
		panelSignUp.add(lblConfirmPass);
		
		JLabel lblEnterYourName = new JLabel("Enter Your Name");
		lblEnterYourName.setForeground(new Color(255, 255, 255));
		lblEnterYourName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterYourName.setBounds(52, 229, 178, 35);
		panelSignUp.add(lblEnterYourName);
		
		tfUserSignUp = new JTextField();
		tfUserSignUp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfUserSignUp.setBounds(301, 36, 379, 35);
		panelSignUp.add(tfUserSignUp);
		tfUserSignUp.setColumns(10);
		
		tfEnterName = new JTextField();
		tfEnterName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfEnterName.setColumns(10);
		tfEnterName.setBounds(301, 229, 379, 35);
		panelSignUp.add(tfEnterName);
		
		pwfPassSignUp = new JPasswordField();
		pwfPassSignUp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwfPassSignUp.setBounds(301, 97, 379, 35);
		panelSignUp.add(pwfPassSignUp);
		
		pwfConfirmPass = new JPasswordField();
		pwfConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwfConfirmPass.setBounds(301, 165, 379, 35);
		panelSignUp.add(pwfConfirmPass);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBackground(new Color(100, 149, 237));
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSignUp.setBounds(214, 334, 112, 44);
		panelSignUp.add(btnSignUp);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfUserSignUp.setText("");
				pwfPassSignUp.setText("");
				pwfConfirmPass.setText("");
				tfEnterName.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReset.setBackground(new Color(60, 179, 113));
		btnReset.setBounds(400, 334, 112, 44);
		panelSignUp.add(btnReset);
		
		JLabel lblReturnLogin = new JLabel("Already have an Account? Sign in");
		lblReturnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveToLogin(panelSignUp, panelLogin);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReturnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReturnLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblReturnLogin.setForeground(Color.GREEN);
		lblReturnLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReturnLogin.setBounds(214, 409, 298, 24);
		panelSignUp.add(lblReturnLogin);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(275, 10, 174, 60);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelLogin.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsername.setBounds(54, 142, 133, 40);
		panelLogin.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(54, 250, 133, 40);
		panelLogin.add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBackground(new Color(211, 211, 211));
		textFieldUsername.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUsername.setBounds(275, 142, 391, 40);
		panelLogin.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(211, 211, 211));
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(275, 250, 391, 40);
		panelLogin.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//					String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//					Connection con = DriverManager.getConnection(url, "sa", "Binben2022@");
//					
//					String user = textFieldUsername.getText();
//					String pass = passwordField.getText();
//					
//					Statement stm = con.createStatement();
//					
//					String query = "select * from Player where username='"+user+"' and pass='"+pass+"'";
//					ResultSet rs = stm.executeQuery(query);
//					
//					if (rs.next()) {
//						// Neu dung tai khoan mat khau thi toi Home Page
////						JOptionPane.showMessageDialog(contentPane, "Login Successfully");
//						dispose();
////						HomePage home = new HomePage();
////						home.show();
//						
//						selectMode mode = new selectMode();
//						mode.show();
//					}
//					else {
//						JOptionPane.showMessageDialog(contentPane, "Login Failed");
//						textFieldUsername.setText("");
//						passwordField.setText("");
//					}
//					
//					con.close();
					
//					Socket socket = new Socket("localhost", 5678);
//					
//					String username = textFieldUsername.getText();
//					String password = passwordField.getText();
//					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//					dos.writeUTF(username);
//					dos.writeUTF(password);
//					
//					DataInputStream dis = new DataInputStream(socket.getInputStream());
//					String kq = dis.readUTF();
//					
//					if (kq.equals("OK")) {
//						dispose();
//						HomePage home = new HomePage();
//						home.show();
//					} else {
//						JOptionPane.showMessageDialog(contentPane, "Login Failed");
//						textFieldUsername.setText("");
//						passwordField.setText("");
//					}
					
					String username = textFieldUsername.getText();
					String password = passwordField.getText();
					
					PlayerSession playerSession = PlayerSession.getInstance(username, "localhost", 5678);
					if (playerSession.checkExistedPlayer(username, password)) {
						dispose();
						new selectMode();
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Login Failed");
						textFieldUsername.setText("");
						passwordField.setText("");
					}
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnLogin.setBackground(new Color(30, 144, 255));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(275, 352, 174, 34);
		panelLogin.add(btnLogin);
		
		JLabel lblSignUp = new JLabel("Don't have Account? Sign Up");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSignUp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				moveToSignUp(panelSignUp, panelLogin);
			}
		});
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(Color.GREEN);
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSignUp.setBounds(170, 396, 399, 40);
		panelLogin.add(lblSignUp);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(FormLogin.class.getResource("/res/background.png")));
		lblBackground.setBounds(10, 10, 906, 557);
		contentPane.add(lblBackground);
	}
	
	private void moveToSignUp(JPanel panelSignUp, JPanel panelLogin) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<=737; i++) {
					panelSignUp.setSize(i, 458);
					panelSignUp.setLocation(837-i, 83);
					panelLogin.setSize(737-i, 458);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void moveToLogin(JPanel panelSignUp, JPanel panelLogin) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<=737; i++) {
					panelSignUp.setSize(737-i, 458);
					panelSignUp.setLocation(100+i, 83);
					panelLogin.setSize(i, 458);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
