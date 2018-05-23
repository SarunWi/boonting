package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.SalesOrder;
import util.DatabaseUtil;

public class SalesOrderModel {
	public List<SalesOrder> getSalesOrderList(int page, int rowperpage, String orderfield) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		
		int recFrom = 1;
		int recTo = rowperpage;
		if(page != 1) {
			recFrom = ((page - 1) * rowperpage) + 1;
			recTo = page * rowperpage;
		}
		System.out.println("Record from: " + recFrom + "|Record To: " + recTo + "orderField: " + orderfield);
		
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
		try {
			String sql = "select * from (select so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name, row_number() over (order by so."+ orderfield + ")"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username) salesorder"
					+ " where row_number BETWEEN ? AND ?";
			
			callSt = conn.prepareCall(sql);
			callSt.setInt(1, recFrom);
			callSt.setInt(2, recTo);
			System.out.println("sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setSalesOrderId(rs.getInt(1));
				salesOrder.setSalesOrderRemark(rs.getString(2));
				salesOrder.setSalesOrderStatus(rs.getString(3));
				salesOrder.setCreatedBy(rs.getString(4));
				salesOrder.setCreatedDate(rs.getDate(5));
				salesOrder.setStateName(rs.getString(6));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return salesOrderList;
	}

	public int getTotalRecord(int id) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		int salesOrderamount = 0;
		try {
			String sql = "select count(*) from boonting.sales_order";
			if(id != 0) {
				sql = sql + " where sales_order_id = "  + id;
			}
			callSt = conn.prepareCall(sql);	
			rs = callSt.executeQuery();
			
			if (rs != null && rs.next()) {
				System.out.println("count salesOrder: " + rs.getInt(1));
				salesOrderamount = rs.getInt(1);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		
		return salesOrderamount;
	}

	public List<SalesOrder> getSalesOrderListById(int id) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
		try {
			String sql = "select so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username"
					+ " where so.sales_order_id = ?";
			
			callSt = conn.prepareCall(sql);
			callSt.setInt(1, id);
			System.out.println("getSalesOrderListById sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setSalesOrderId(rs.getInt(1));
				salesOrder.setSalesOrderRemark(rs.getString(2));
				salesOrder.setSalesOrderStatus(rs.getString(3));
				salesOrder.setCreatedBy(rs.getString(4));
				salesOrder.setCreatedDate(rs.getDate(5));
				salesOrder.setStateName(rs.getString(6));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return salesOrderList;
	}
	
	public int updateSalesOrder(SalesOrder salesOrder) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		int rs = 0;
		int updateSuccess = 0;
		
		try {
			String sql = "";
			
			callSt = conn.prepareCall(sql);
			callSt.setString(1, salesOrder.getSalesOrderRemark());
			callSt.setString(2, salesOrder.getSalesOrderStatus());
			callSt.setString(3, salesOrder.getSalesOrderRemark());
			System.out.println("updateSalesOrder sql: " + callSt.toString());
			
			rs = callSt.executeUpdate();
			System.out.println("rs: " + rs);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return updateSuccess;
	}

	public int insertSalesOrder(SalesOrder salesOrder) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		int rs = 0;
		int updateSuccess = 0;
		
		try {
			String sql = "insert into boonting.sales_order()"
					+ " values(nextval(sales_order_id, "
					+ " "
					+ "))";
			
			callSt = conn.prepareCall(sql);
			callSt.setString(1, salesOrder.getSalesOrderRemark());
			callSt.setString(2, salesOrder.getSalesOrderStatus());
			callSt.setString(3, salesOrder.getSalesOrderRemark());
			System.out.println("insertSalesOrder sql: " + callSt.toString());
			
			rs = callSt.executeUpdate();
			System.out.println("rs: " + rs);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return updateSuccess;
	}

	public List<SalesOrder> getSalesOrderGroupByLocation(Date fromDate, Date toDate) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
		try {
			String sql = "select so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username"
					+ " where so.sales_order_id = ?";
			
			callSt = conn.prepareCall(sql);
			System.out.println("getSalesOrderListById sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setSalesOrderId(rs.getInt(1));
				salesOrder.setSalesOrderRemark(rs.getString(2));
				salesOrder.setSalesOrderStatus(rs.getString(3));
				salesOrder.setCreatedBy(rs.getString(4));
				salesOrder.setCreatedDate(rs.getDate(5));
				salesOrder.setStateName(rs.getString(6));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return salesOrderList;
	}
	
	public List<SalesOrder> getSalesOrderGroupByCustomer(Date fromDate, Date toDate) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
		try {
			String sql = "select so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username"
					+ " where so.sales_order_id = ?";
			
			callSt = conn.prepareCall(sql);
			System.out.println("getSalesOrderListById sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setSalesOrderId(rs.getInt(1));
				salesOrder.setSalesOrderRemark(rs.getString(2));
				salesOrder.setSalesOrderStatus(rs.getString(3));
				salesOrder.setCreatedBy(rs.getString(4));
				salesOrder.setCreatedDate(rs.getDate(5));
				salesOrder.setStateName(rs.getString(6));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return salesOrderList;
	}
}
