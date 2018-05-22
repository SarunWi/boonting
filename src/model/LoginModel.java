package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseUtil;

public class LoginModel {
	public int checkUsernamePassword(String username, String password) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		int userExist = -1;
		try {
			String sql = "select count(*) from boonting.user where username = ? and user_password = ?";
			
			callSt = conn.prepareCall(sql);
			callSt.setString(1, username);
			callSt.setString(2, password);
			
			rs = callSt.executeQuery();
			if (rs != null && rs.next()) {
				userExist = rs.getInt(1);
			}
			System.out.println("rs: " + rs.toString());
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return userExist;
	}
}
