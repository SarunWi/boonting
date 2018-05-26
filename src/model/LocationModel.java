package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;
import dto.Location;
import dto.SalesOrder;
public class LocationModel {
	public int checkLocationName(String name) 
	{
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		int Exist = -1;
		try {
			String sql = "select count(*) from boonting.location where location_name = ?";
			
			callSt = conn.prepareCall(sql);
			callSt.setString(1, name);
			
			rs = callSt.executeQuery();
			if (rs != null && rs.next()) {
				Exist = rs.getInt(1);
			}
			System.out.println("rs: " + rs.toString());
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return Exist;
	}
	
	public int insertLocation(String name,float longitude,float latitude ,String description,String address) 
	{
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement callSt = null;
		int locationExist = -1;
		try {
			String sql = "INSERT INTO boonting.location (location_id, location_name, location_longitude, location_latitude, location_description,location_address) VALUES (nextval('boonting.location_id'), ?, ?, ?, ?, ?)";
			callSt = conn.prepareStatement(sql);
			callSt.setString(1, name);
			callSt.setFloat(2, longitude);
			callSt.setFloat(3, latitude);
			callSt.setString(4, description);
			callSt.setString(5, address);
			callSt.executeUpdate();
			locationExist = 1;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt,conn);
		}
		return locationExist;
	}
	
	public int updateLocation(int id, String name,float longitude,float latitude ,String description,String address) 
	{
		System.out.println("updateLocation");
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement callSt = null;
		int locationExist = -1;
		try {
			String sql = "update boonting.location SET location_name = ?, location_longitude= ?,location_latitude= ?, location_description = ? ,location_address = ? where location_id = ?";
			callSt = conn.prepareStatement(sql);
			callSt.setString(1, name);
			callSt.setFloat(2, longitude);
			callSt.setFloat(3, latitude);
			callSt.setString(4, description);
			callSt.setString(5, address);
			callSt.setInt(6, id);
			System.out.println("callSt: " + callSt.toString());
			locationExist = callSt.executeUpdate();
			System.out.println("locationExist: " + locationExist);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt,conn);
		}
		return locationExist;
	}
	
	public int checkLocationId(int id) 
	{
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		int Exist = -1;
		try {
			String sql = "select count(*) from boonting.location where location_id = ?";
			
			callSt = conn.prepareCall(sql);
			callSt.setInt(1, id);
			
			rs = callSt.executeQuery();
			if (rs != null && rs.next()) {
				Exist = rs.getInt(1);
			}
			System.out.println("rs: " + rs.toString());
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return Exist;
	}
	
	public List<Location> getAllLocation() 
	{
		List<Location> Listlocation = new ArrayList<Location>();
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from boonting.location";
			
			callSt = conn.prepareCall(sql);
			System.out.println("sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				Location location = new Location();
				location.setLocationId(rs.getInt(1));
				location.setLocationName(rs.getString(2));
				location.setLocationLongitude(rs.getFloat(3));
				location.setLocationLatitude(rs.getFloat(4));
				location.setLocationDescription(rs.getString(5));
				location.setLocationAddress(rs.getString(6));
				Listlocation.add(location);
			}
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return Listlocation;
	}
}
