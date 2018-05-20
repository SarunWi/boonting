package dto;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ebill")
public class Ebill {
	private int billId;
	private Date billDate;
	private float billTotal;
	private int salesOrderId;
	
	@XmlElement(name = "billId")
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	
	@XmlElement(name = "billDate")
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	@XmlElement(name = "billTotal")
	public float getBillTotal() {
		return billTotal;
	}
	public void setBillTotal(float billTotal) {
		this.billTotal = billTotal;
	}
	
	@XmlElement(name = "salesOrderId")
	public int getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	
	public Ebill(int billId, Date billDate, float billTotal, int salesOrderId) {
		super();
		this.billId = billId;
		this.billDate = billDate;
		this.billTotal = billTotal;
		this.salesOrderId = salesOrderId;
	}
	
	public Ebill() {
		super();
	}
}
