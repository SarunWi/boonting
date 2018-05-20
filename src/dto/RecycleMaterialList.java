package dto;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recyclemateriallist")
public class RecycleMaterialList {
	private int rmlListId;
	private String rmlName;
	private int rmlQuantity;
	
	@XmlElement(name = "rmlListId")
	public int getRmlListId() {
		return rmlListId;
	}
	public void setRmlListId(int rmlListId) {
		this.rmlListId = rmlListId;
	}
	
	@XmlElement(name = "rmlName")
	public String getRmlName() {
		return rmlName;
	}
	public void setRmlName(String rmlName) {
		this.rmlName = rmlName;
	}
	
	@XmlElement(name = "rmlQuantity")
	public int getRmlQuantity() {
		return rmlQuantity;
	}
	public void setRmlQuantity(int rmlQuantity) {
		this.rmlQuantity = rmlQuantity;
	}
	
	public RecycleMaterialList(int rmlListId, String rmlName, int rmlQuantity) {
		super();
		this.rmlListId = rmlListId;
		this.rmlName = rmlName;
		this.rmlQuantity = rmlQuantity;
	}
	
	public RecycleMaterialList() {
		super();
	}
}
