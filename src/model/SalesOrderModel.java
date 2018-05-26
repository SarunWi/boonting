package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dto.SalesOrder;
import dto.SalesOrderRequest;
import util.Constants;
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
			String sql = "select * from (select so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name, s.shipping_id, l.location_id, row_number() over (order by so."+ orderfield + ")"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username"
					+ " left join boonting.shipping s"
					+ " on s.shipping_id = so.shipping_id"
					+ " left join boonting.location l"
					+ " on l.location_id = s.location_id) salesorder"
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
				salesOrder.setShippingId(rs.getInt(7));
				salesOrder.setLocationId(rs.getInt(8));
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
			String sql = "select so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name, s.shipping_id, l.location_id"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username"
					+ " left join boonting.shipping s"
					+ " on s.shipping_id = so.shipping_id"
					+ " left join boonting.location l"
					+ " on l.location_id = s.location_id"
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
				salesOrder.setShippingId(rs.getInt(7));
				salesOrder.setLocationId(rs.getInt(8));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return salesOrderList;
	}

	public int insertUpdateSalesOrder(SalesOrderRequest salesOrderReq) {
		Connection conn = DatabaseUtil.getConnection();
		
		int response = -1;
		CallableStatement callSt = null;
		
		try {
			conn.setAutoCommit(false);
			String sql = "";
			if(salesOrderReq.getId() == 0) {
				System.out.println("insert sales order");
				sql = "{ ? = call boonting.insert_sales_order(?, ?, ?, ? ,? ,? ,? ,?) }";
			} else {
				System.out.println("update sales order");
				sql = "{ ? = call boonting.update_sales_order(?, ?, ?, ? ,? ,? ,? ,?) }";
			}
			
			callSt = conn.prepareCall(sql);
			callSt.registerOutParameter(1, Types.VARCHAR);
			callSt.setString(2, String.valueOf(salesOrderReq.getId()));
			callSt.setString(3, salesOrderReq.getUsername());
			callSt.setString(4, salesOrderReq.getCreated_date());
			callSt.setString(5, salesOrderReq.getLocation());
			callSt.setString(6, salesOrderReq.getRemark());
			callSt.setString(7, salesOrderReq.getStatus());
			callSt.setString(8, salesOrderReq.getState());
			callSt.setString(9, salesOrderReq.getItems());
			
			callSt.execute();
			if(callSt.getString(1).equals(Constants.RETURN_STATUS_SUCCESS)) {
				response = 1;
			} else {
				response = 0;
			}
			
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}

	public List<SalesOrder> getSalesOrderGroupByLocation(String fromDate, String toDate) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		try {
			String sql = "select l.location_id, l.location_name, so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name,  so.created_by, s.shipping_id"
				+ " from boonting.sales_order so"
				+ " join boonting.sales_order_state sos"
				+ " on so.sales_order_state_id = sos.sales_order_state_id"
				+ " join boonting.user u"
				+ " on so.created_by = u.username"
				+ " join boonting.shipping s"
				+ " on s.shipping_id = so.shipping_id"
				+ " join boonting.location l"
				+ " on l.location_id = s.location_id";
			
			if((fromDate.equals("") || fromDate.length() == 0) && (toDate.equals("") || toDate.length() == 0)) {
				
			} else if(!(fromDate.equals("") || fromDate.length() == 0) && !(toDate.equals("") || toDate.length() == 0) && !fromDate.equals(toDate)) {				
				sql = sql + " where so.created_date >= to_timestamp(" + "'"+fromDate +"'"+ ", 'YYYY-MM-DD')"
				+ " and so.created_date < to_timestamp(" +"'"+ toDate +"'"+ ", 'YYYY-MM-DD')";
			} else if(fromDate.equals(toDate)) {
				sql = sql + " where so.created_date >= to_timestamp(" + "'"+fromDate +" 00:00:00'"+ ", 'YYYY-MM-DD HH24:MI:SS')"
				+ " and so.created_date < to_timestamp(" +"'"+ toDate +" 23:99:99'"+ ", 'YYYY-MM-DD HH24:MI:SS')";
			}
			
			sql = sql + " order by l.location_id";
			System.out.println("sql: " + sql);
			
			callSt = conn.prepareCall(sql);
			rs = callSt.executeQuery();
			while(rs.next()) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setSalesOrderId(rs.getInt(3));
				salesOrder.setStateName(rs.getString(8));
				salesOrder.setSalesOrderRemark(rs.getString(4));
				salesOrder.setSalesOrderStatus(rs.getString(5));
				//salesOrder.setCreatedBy(rs.getString(4));
				salesOrder.setCreatedDate(rs.getDate(7));
				salesOrder.setCreatedBy(rs.getString(9));
				salesOrder.setShippingId(rs.getInt(10));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return salesOrderList;
	}
	
	public List<SalesOrder> getSalesOrderGroupByCustomer(String fromDate, String toDate) {
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
		try {
			String sql = "select l.location_id, l.location_name, so.sales_order_id, so.sales_order_remark, so.sales_order_status, u.username, so.created_date, sos.state_name, so.created_by, s.shipping_id"
					+ " from boonting.sales_order so"
					+ " join boonting.sales_order_state sos"
					+ " on so.sales_order_state_id = sos.sales_order_state_id"
					+ " join boonting.user u"
					+ " on so.created_by = u.username"
					+ " join boonting.shipping s"
					+ " on s.shipping_id = so.shipping_id"
					+ " join boonting.location l"
					+ " on l.location_id = s.location_id";
			if(fromDate != null && toDate != null) {
					sql = sql + " where so.created_date >= to_timestamp(" + "'"+fromDate +"'"+ ", 'YYYY-MM-DD')"
					+ " and so.created_date < to_timestamp(" +"'"+ toDate +"'"+ ", 'YYYY-MM-DD')";
			}
			sql = sql + " order by so.sales_order_id";
			callSt = conn.prepareCall(sql);
			System.out.println("getSalesOrderListById sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setSalesOrderId(rs.getInt(3));
				salesOrder.setStateName(rs.getString(2));
				salesOrder.setSalesOrderRemark(rs.getString(4));
				salesOrder.setSalesOrderStatus(rs.getString(5));
				salesOrder.setCreatedDate(rs.getDate(7));
				salesOrder.setCreatedBy(rs.getString(9));
				salesOrder.setShippingId(rs.getInt(10));
				salesOrderList.add(salesOrder);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		System.out.println("salesOrderList: " + salesOrderList);
		return salesOrderList;
	}
	
	public int deleteSaleOrder(int salesOrder) {
		Connection conn = DatabaseUtil.getConnection();
				
		int response = -1;
		CallableStatement callSt = null;
		
		try {
			conn.setAutoCommit(false);
			String sql = "{ ? = call boonting.delete_sales_order(?) }";
			
			callSt = conn.prepareCall(sql);
			callSt.registerOutParameter(1, Types.VARCHAR);
			callSt.setString(2, String.valueOf(salesOrder));
			callSt.execute();
			if(callSt.getString(1).equals(Constants.RETURN_STATUS_SUCCESS)) {
				response = 1;
			} else if(callSt.getString(1).equals(Constants.RETURN_STATUS_FAIL)){
				response = 0;
			}
			
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
}
