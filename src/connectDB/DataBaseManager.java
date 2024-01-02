package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

//	private static final String URL_STRING = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=PBL4;integratedSecurity=true;";
	private static final String URL_STRING = "jdbc:mysql://localhost:3306/pbl4";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	private static Connection connection;
	
	// Mo ket noi
	public static Connection openConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(URL_STRING, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	// Dong ket noi
	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
