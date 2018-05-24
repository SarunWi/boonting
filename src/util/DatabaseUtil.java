package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
	public static final String JDBC_DRIVER 		= "org.postgresql.Driver";
	
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(
					"jdbc:postgresql://122.155.202.161:5432/postgres", "postgres",
					"admin");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void close(CallableStatement callSt, ResultSet rs, Connection conn) {
		try {
			rs.close();
			callSt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
