package dto;
import java.util.List;


public class CustomerResp {
	private List<User> records;
	private int totalRecords;
	public List<User> getRecords() {
		return records;
	}
	public void setRecords(List<User> records) {
		this.records = records;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
