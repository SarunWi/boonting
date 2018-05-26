package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseUtil {
	public static final String JDBC_DRIVER 		= "org.postgresql.Driver";
	
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
//			Class.forName(JDBC_DRIVER);
//			connection = DriverManager.getConnection(
//					"jdbc:postgresql://localhost:5432/postgres", "postgres",
//					"admin");
			InitialContext cxt = new InitialContext();
			DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
			connection = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public static void close(PreparedStatement callSt,Connection conn) {
		try {
			callSt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
