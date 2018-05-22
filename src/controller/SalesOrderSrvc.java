package controller;

import java.util.ArrayList;
import java.util.List;

import dto.SalesOrder;
import dto.SalesOrderResp;
import model.SalesOrderModel;

public class SalesOrderSrvc {
	private int page;
	private int rowsperpage;
	private String orderfield;
		
	public SalesOrderSrvc() {}
	
	public SalesOrderResp getSalesOrder(int page, int rowsperpage, String orderfield) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		
		salesOrderResp.setRecords(getSalesOrderList(page, rowsperpage, orderfield, salesOrderModel));
		salesOrderResp.setPage(page);
		salesOrderResp.setTotalRecords(getTotalRecord(0, salesOrderModel));
		
		return salesOrderResp;
	}
	
	public SalesOrderResp getSalesOrderById(int id) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		
		salesOrderResp.setRecords(getSalesOrderListById(id, salesOrderModel));
		salesOrderResp.setPage(1);
		salesOrderResp.setTotalRecords(getTotalRecord(id, salesOrderModel));
		
		return salesOrderResp;
	}
	
	private List<SalesOrder> getSalesOrderList(int page, int rowsperpage, String orderfield, SalesOrderModel salesOrderModel) {
		System.out.println("getSalesOrderList");
		List<SalesOrder> salesOrderList = salesOrderModel.getSalesOrderList(page, rowsperpage, orderfield);
		System.out.println(salesOrderList.size());
		return salesOrderList;
	}
	
	private List<SalesOrder> getSalesOrderListById(int id, SalesOrderModel salesOrderModel) {
		return salesOrderModel.getSalesOrderListById(id);
	}
	
	private int getTotalRecord(int id, SalesOrderModel salesOrderModel) {
		System.out.println("getTotalRecord");
		return salesOrderModel.getTotalRecord(id);
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowsperpage() {
		return rowsperpage;
	}
	public void setRowsperpage(int rowsperpage) {
		this.rowsperpage = rowsperpage;
	}
	public String getOrderfield() {
		return orderfield;
	}
	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}
}
