package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shipping")
public class Shipping {
	private int shippingId;
	private Date pickUpDate;
	
	@XmlElement(name = "shippingId")
	public int getShippingId() {
		return shippingId;
	}
	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}
	
	@XmlElement(name = "pickUpDate")
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	
	public Shipping(int shippingId, Date pickUpDate) {
		super();
		this.shippingId = shippingId;
		this.pickUpDate = pickUpDate;
	}
	
	public Shipping() {
		super();
	}
}
