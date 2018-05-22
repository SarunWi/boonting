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
	
	public SalesOrderResp getSalesOrder(int pageTmp, int rowsperpageTmp, String orderfieldTmp) {
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		List<SalesOrder> salesOrderList = getSalesOrderList(pageTmp, rowsperpageTmp, orderfieldTmp);
		
		return salesOrderResp;
	}
	
	private List<SalesOrder> getSalesOrderList(int pageTmp, int rowsperpageTmp, String orderfieldTmp) {
		
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
