package dto;

import java.util.List;

public class MaterialResp {
	private List<RecycleMaterial> records;
	private int totalRecords;
	public List<RecycleMaterial> getRecords() {
		return records;
	}
	public void setRecords(List<RecycleMaterial> records) {
		this.records = records;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
