package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.border.SoftBevelBorder;

import javax.swing.border.BevelBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JPasswordField txtPassword;
	private JButton btnsignup;

	
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
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setToolTipText("");
		txtEnterUsername.setBounds(84, 74, 250, 30);
		contentPane.add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(84, 58, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(84, 112, 70, 14);
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(84, 127, 250, 30);
		contentPane.add(txtPassword);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateLogin();
			}
		});
		btnlogin.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBackground(new Color(51, 102, 0));
		btnlogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 204, 255), null, new Color(0, 0, 0), null));
		btnlogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnlogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnlogin.setBounds(171, 168, 70, 23);
		contentPane.add(btnlogin);
		
		btnsignup = new JButton("Sign up");
		btnsignup.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		btnsignup.setForeground(new Color(51, 102, 0));
		btnsignup.setBackground(Color.WHITE);
		btnsignup.setAlignmentY(Component.TOP_ALIGNMENT);
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openRegister();
			}
		});
		
		 
		
		btnsignup.setBounds(231, 205, 83, 23);
		contentPane.add(btnsignup);
		
		JLabel lblNewLabel_3 = new JLabel("Don't have an account?");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(84, 209, 144, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Goudy Stout", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(109, 1, 192, 62);
		contentPane.add(lblNewLabel);
	}
	
	private void validateLogin() {
        try {
//        	Class.forName(driver);
        	Connection con = DriverManager.getConnection(url1, user, password);
			st = con.createStatement();
			String sql = "Select * from Player where username = '" +txtEnterUsername.getText()+"' and pass ='"+txtPassword.getText().toString()+"'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Login successfully");
				openHome();
			}
			else 
				JOptionPane.showMessageDialog(null, "Incorrect username or password");
			con.close();

        }catch(Exception e2) {}
    }

	private void openRegister() {
        // Tạo và hiển thị JFrame đăng ký mới
		System.out.println("Sign Up button pressed");
        Register registerFrame = new Register();
        registerFrame.setVisible(true);
     
        this.setVisible(false);
	}
	private void openHome() {
        // Tạo và hiển thị JFrame đăng ký mới
        Home homeFrame = new Home();
        homeFrame.setVisible(true);
     
        this.setVisible(false);
	}
}