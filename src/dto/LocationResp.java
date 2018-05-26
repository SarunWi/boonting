package dto;
import java.util.List;
public class LocationResp 
{
	private List<Location> records;
	private int totalRecords;
	public List<Location> getRecords() {
		return records;
	}
	public void setRecords(List<Location> records) {
		this.records = records;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}