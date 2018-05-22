package dto;

public class SalesOrderRequest {
	private int id;
	private String status;
	private String location;
	private int page;
	private int rowsperpage;
	private String order;
	
	public SalesOrderRequest(int id, String status, String location) {
		super();
		this.id = id;
		this.status = status;
		this.location = location;
	}

	public SalesOrderRequest(int page, int rowsperpage, String order) {
		super();
		this.page = page;
		this.rowsperpage = rowsperpage;
		this.order = order;
	}

	public SalesOrderRequest(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRowsperpage() {
		return rowsperpage;
	}

	public void setRowsperpage(int rowsperpage) {
		this.rowsperpage = rowsperpage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
