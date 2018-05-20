package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salesorder")
public class SalesOrder {
	
	private int salesOrderId;
	private String salesOrderRemark;
	private String salesOrderStatus;
	private String createdBy;
	private Date createdDate;
	
	@XmlElement(name = "salesOrderId")
	public int getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	
	@XmlElement(name = "salesOrderRemark")
	public String getSalesOrderRemark() {
		return salesOrderRemark;
	}
	public void setSalesOrderRemark(String salesOrderRemark) {
		this.salesOrderRemark = salesOrderRemark;
	}
	
	@XmlElement(name = "salesOrderStatus")
	public String getSalesOrderStatus() {
		return salesOrderStatus;
	}
	public void setSalesOrderStatus(String salesOrderStatus) {
		this.salesOrderStatus = salesOrderStatus;
	}
	
	@XmlElement(name = "createdBy")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@XmlElement(name = "createdBy")
	public Date getCreated_Date() {
		return createdDate;
	}
	public void setCreated_Date(Date created_Date) {
		this.createdDate = created_Date;
	}
	
	public SalesOrder(int salesOrderId, String salesOrderRemark, String salesOrderStatus, String createdBy,
			Date createdDate) {
		super();
		this.salesOrderId = salesOrderId;
		this.salesOrderRemark = salesOrderRemark;
		this.salesOrderStatus = salesOrderStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
	
	public SalesOrder() {
		super();
	}
}
