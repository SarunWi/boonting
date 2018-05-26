package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.User;
import util.DatabaseUtil;

public class CustomerModel 
{
	public List<User> getAllCustomer() 
	{
		List<User> ListUser = new ArrayList<User>();
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from boonting.user where user_role = 2";
			
			callSt = conn.prepareCall(sql);
			System.out.println("sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserFirstName(rs.getString(2));
				user.setUserLastName(rs.getString(3));
				user.setUserPassword(rs.getString(4));
				user.setUserAddress(rs.getString(5));
				user.setUserPhoneNumber(rs.getString(6));
				user.setUserBirthDate(rs.getDate(7));
				//user.setUserBirthDate(rs.getTimestamp(6));
				user.setUserEmail(rs.getString(8));
				user.setUsername(rs.getString(10));
				ListUser.add(user);
			}
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return ListUser;
	}
}
