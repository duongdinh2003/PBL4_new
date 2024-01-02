//package view;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.Image;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.SwingConstants;
//import javax.swing.JTable;
//
//public class MatchHistory extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	private JTable table;
//	
//	String driver = "com.mysql.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/PBL4";
//	String url1 = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
//	String user = "sa";
//	String password = "Binben2022@";
//	Statement st ;
//	ResultSet rs;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MatchHistory frame = new MatchHistory();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public MatchHistory() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 660, 485);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		 
//		
//		
//		JLabel lblNewLabel = new JLabel("");
//	
//		ImageIcon originalIcon = new ImageIcon(MatchHistory.class.getResource("/img/brg.png"));
//		Image originalImage = originalIcon.getImage();
//        Image resizedImage = originalImage.getScaledInstance(650, 450, Image.SCALE_SMOOTH);
//        ImageIcon resizedIcon = new ImageIcon(resizedImage);
//        
//        
//     // Tạo một DefaultTableModel để chứa dữ liệu cho JTable
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Match ID");
//        model.addColumn("Player 1");
//        model.addColumn("Player 2");
//        model.addColumn("Time Limit");
//        model.addColumn("Date");
//        
//        table = new JTable();
//        table.setFont(new Font("Arial Black", Font.PLAIN, 13));
//        table.setBounds(102, 71, 444, 357);
//        contentPane.add(table);
//        JScrollPane scrollPane = new JScrollPane(table);
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//        // Kết nối cơ sở dữ liệu và lấy lịch sử thi đấu
//        displayMatchHistory();
//     
//        
//
//        
//        JLabel lblMatchHistory = new JLabel("MATCH HISTORY");
//        lblMatchHistory.setHorizontalAlignment(SwingConstants.CENTER);
//        lblMatchHistory.setFont(new Font("Goudy Stout", Font.BOLD, 20));
//        lblMatchHistory.setBounds(142, 11, 365, 62);
//        contentPane.add(lblMatchHistory);
//        lblNewLabel.setIcon(resizedIcon);
//		lblNewLabel.setBounds(0, 0, 650, 450);
//		contentPane.add(lblNewLabel);
//				
//	}
//
//	private void displayMatchHistory() {
//		 try {
//	            // Kết nối cơ sở dữ liệu
//	        	Connection con = DriverManager.getConnection(url1, user, password);
//				st = con.createStatement();
//
//	            // Thực hiện truy vấn
//	            String query = "SELECT * FROM MatchChess";
//	            PreparedStatement preparedStatement = con.prepareStatement(query);
//	            ResultSet resultSet = preparedStatement.executeQuery();
//
//	            // Thêm dữ liệu từ kết quả vào DefaultTableModel
//	            DefaultTableModel model = (DefaultTableModel) table.getModel();
//	            while (resultSet.next()) {
//	                int matchId = resultSet.getInt("id");
//	                String player1 = resultSet.getString("player1");
//	                String player2 = resultSet.getString("player2");
//	                int timeLimit = resultSet.getInt("time_limit");
//	                int date_started = resultSet.getInt("date_started");
//
//	                // Thêm dữ liệu vào model
//	                model.addRow(new Object[]{matchId, player1, player2, timeLimit, date_started});
//	            }
//
//	            // Đóng kết nối
//	            resultSet.close();
//	            preparedStatement.close();
//	            con.close();
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//		
//	}
//}