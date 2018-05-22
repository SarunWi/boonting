package dto;

import java.util.List;

public class SalesOrderResp {
	private List<SalesOrder> records;
	private int page;
	private int totalRecords;
	
	public List<SalesOrder> getRecords() {
		return records;
	}
	public void setRecords(List<SalesOrder> records) {
		this.records = records;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
