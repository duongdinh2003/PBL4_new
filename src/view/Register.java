package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuser;
	private JPasswordField txtpass;
	private JPasswordField txtconfirm;
	private JTextField txtname;
	private JTextField txtemail;

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/PBL4";
	String url1 = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
	String user = "sa";
	String password = "Binben2022@";
	Statement st ;
	ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIGNUP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Goudy Stout", Font.BOLD, 20));
		lblNewLabel.setBounds(123, 11, 192, 62);
		contentPane.add(lblNewLabel);
		
		txtuser = new JTextField();
		txtuser.setSelectedTextColor(Color.BLACK);
		txtuser.setForeground(UIManager.getColor("CheckBox.focus"));
		txtuser.setToolTipText("");
		txtuser.setColumns(10);
		txtuser.setBounds(85, 84, 249, 30);
		contentPane.add(txtuser);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(85, 66, 95, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Create password");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(85, 125, 115, 14);
		contentPane.add(lblNewLabel_2);
		
		txtpass = new JPasswordField();
		txtpass.setSelectedTextColor(Color.BLACK);
		txtpass.setForeground(UIManager.getColor("CheckBox.focus"));
		txtpass.setBounds(85, 140, 249, 30);
		contentPane.add(txtpass);
		
		JButton btnsignup = new JButton("Sign up");
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dk = JOptionPane.showConfirmDialog(null, "Do you want to sign up?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (dk != JOptionPane.YES_OPTION) {
					return;
				}
				try {
//					Class.forName(driver);
					Connection con = DriverManager.getConnection(url1, user, password);
					String sql="insert into Player values (?, ?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, txtuser.getText());
					ps.setString(2, txtpass.getText());
					ps.setString(3, txtconfirm.getText());
					ps.setString(4, txtname.getText());
					ps.setString(5, txtemail.getText());
			
					int n= ps.executeUpdate();
					
					if(txtuser.getText().equals("")|txtpass.getText().equals("")|txtconfirm.getText().equals("")|txtname.getText().equals("")|txtemail.getText().equals("")|txtuser.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "cannot be left vacant");
					}
					else if(n!=0) {
						JOptionPane.showMessageDialog(null, "Sign up successfully");
						openLogin();
					}
					else {
						JOptionPane.showMessageDialog(null, "Sign up unsuccessfully");
					}
		
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnsignup.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnsignup.setForeground(Color.BLACK);
		btnsignup.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnsignup.setBackground(new Color(0, 102, 255));
		btnsignup.setAlignmentY(1.0f);
		btnsignup.setAlignmentX(1.0f);
		btnsignup.setBounds(174, 366, 70, 23);
		contentPane.add(btnsignup);
		
		txtconfirm = new JPasswordField();
		txtconfirm.setSelectedTextColor(Color.BLACK);
		txtconfirm.setForeground(UIManager.getColor("CheckBox.focus"));
		txtconfirm.setBounds(85, 195, 249, 30);
		contentPane.add(txtconfirm);
		
		JLabel lblNewLabel_2_1 = new JLabel("Confirm password");
		lblNewLabel_2_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(85, 180, 115, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Already have an account?");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(85, 404, 159, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLogin();
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnLogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnLogin.setBackground(new Color(51, 102, 0));
		btnLogin.setAlignmentY(1.0f);
		btnLogin.setAlignmentX(1.0f);
		btnLogin.setBounds(245, 400, 70, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setToolTipText("");
		lblNewLabel_1_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(85, 236, 95, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtname = new JTextField();
		txtname.setToolTipText("");
		txtname.setSelectedTextColor(Color.BLACK);
		txtname.setForeground(Color.BLACK);
		txtname.setColumns(10);
		txtname.setBounds(85, 254, 249, 30);
		contentPane.add(txtname);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setToolTipText("");
		lblNewLabel_1_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(85, 295, 95, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("");
		txtemail.setSelectedTextColor(Color.BLACK);
		txtemail.setForeground(Color.BLACK);
		txtemail.setColumns(10);
		txtemail.setBounds(85, 313, 249, 30);
		contentPane.add(txtemail);
	}
	private void openLogin() {
        // Tạo và hiển thị JFrame đăng ký mới
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
     
        this.setVisible(false);
	}
}