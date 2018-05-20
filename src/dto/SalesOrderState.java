package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salesorderstate")
public class SalesOrderState {
	private int salesOrderStateId;
	private String stateName;
	
	@XmlElement(name = "salesOrderStateId")
	public int getSalesOrderStateId() {
		return salesOrderStateId;
	}
	public void setSalesOrderStateId(int salesOrderStateId) {
		this.salesOrderStateId = salesOrderStateId;
	}
	
	@XmlElement(name = "stateName")
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public SalesOrderState(int salesOrderStateId, String stateName) {
		super();
		this.salesOrderStateId = salesOrderStateId;
		this.stateName = stateName;
	}
	
	public SalesOrderState() {
		super();
	}
}
