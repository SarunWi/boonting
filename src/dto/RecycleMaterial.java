package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recyclematerial")
public class RecycleMaterial {
	private int rmId;
	private String rmName;
	private String rmDescription;
	private float rmPrice;
	private String rmImage;
	
	@XmlElement(name = "rmId")
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	
	@XmlElement(name = "rmName")
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	
	@XmlElement(name = "rmDescription")
	public String getRmDescription() {
		return rmDescription;
	}
	public void setRmDescription(String rmDescription) {
		this.rmDescription = rmDescription;
	}
	
	@XmlElement(name = "rmPrice")
	public float getRmPrice() {
		return rmPrice;
	}
	public void setRmPrice(float rmPrice) {
		this.rmPrice = rmPrice;
	}
	
	@XmlElement(name = "getRmImage")
	public String getRmImage() {
		return rmImage;
	}
	public void setRmImage(String rmImage) {
		this.rmImage = rmImage;
	}
	
	public RecycleMaterial(int rmId, String rmName, String rmDescription, float rmPrice, String rmImage) {
		super();
		this.rmId = rmId;
		this.rmName = rmName;
		this.rmDescription = rmDescription;
		this.rmPrice = rmPrice;
		this.rmImage = rmImage;
	}
	
	public RecycleMaterial() {
		super();
	}
}
