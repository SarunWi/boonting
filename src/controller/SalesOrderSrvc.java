package controller;

import java.util.ArrayList;
import java.util.List;

import dto.SalesOrder;
import dto.SalesOrderResp;

public class SalesOrderSrvc {
	private int page;
	private int rowsperpage;
	private String orderfield;
		
	public SalesOrderSrvc() {}
	
	public SalesOrderResp getSalesOrder(int page, int rowsperpage, String orderfield) {
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		List<SalesOrder> salesOrderList = getSalesOrderList(page, rowsperpage, orderfield);
		
		return salesOrderResp;
	}
	
	private List<SalesOrder> getSalesOrderList(int page, int rowsperpage, String orderfield) {
		
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
