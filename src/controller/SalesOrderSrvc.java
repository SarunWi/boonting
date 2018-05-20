package controller;

import java.util.Date;

public class SalesOrderSrvc {
	private int salesOrderId;
	private String salesOrderRemark;
	private String salesOrderStatus;
	private String createdBy;
	private Date createdDate;
	
	
	
	public int getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public String getSalesOrderRemark() {
		return salesOrderRemark;
	}
	public void setSalesOrderRemark(String salesOrderRemark) {
		this.salesOrderRemark = salesOrderRemark;
	}
	public String getSalesOrderStatus() {
		return salesOrderStatus;
	}
	public void setSalesOrderStatus(String salesOrderStatus) {
		this.salesOrderStatus = salesOrderStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
