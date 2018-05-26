package dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salesorder")
public class SalesOrder {
	
	private int salesOrderId;
	private String salesOrderRemark;
	private String salesOrderStatus;
	private String createdBy;
	private Date createdDate;
	private String stateName;
	private int shippingId;
	private int locationId;
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@XmlElement(name = "stateName")
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	@XmlElement(name = "shippingId")
	public int getShippingId() {
		return shippingId;
	}
	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}
	
	@XmlElement(name = "locationId")
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public SalesOrder(int salesOrderId, String salesOrderRemark, String salesOrderStatus, String createdBy,
			Date createdDate, String stateName, int shippingId, List<RecycleMaterialList> recycleMaterialList) {
		super();
		this.salesOrderId = salesOrderId;
		this.salesOrderRemark = salesOrderRemark;
		this.salesOrderStatus = salesOrderStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.stateName = stateName;
		this.shippingId = shippingId;
	}
	
	public SalesOrder(int salesOrderId, String salesOrderRemark, String salesOrderStatus, String createdBy,
			Date createdDate, String stateName, int shippingId) {
		this.salesOrderId = salesOrderId;
		this.salesOrderRemark = salesOrderRemark;
		this.salesOrderStatus = salesOrderStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.stateName = stateName;
		this.shippingId = shippingId;
	}
	
	public SalesOrder() {}
}
