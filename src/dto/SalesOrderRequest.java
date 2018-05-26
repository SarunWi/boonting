package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "req")
public class SalesOrderRequest {
	private int id;
	private String status;
	private String location;
	private int page;
	private int rowsperpage;
	private String order;
	private String username;
	private String created_date;
	private String remark;
	private String state;
	private String items;

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@XmlElement(name = "page")
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@XmlElement(name = "rowsperpage")
	public int getRowsperpage() {
		return rowsperpage;
	}

	public void setRowsperpage(int rowsperpage) {
		this.rowsperpage = rowsperpage;
	}

	@XmlElement(name = "order")
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement(name = "created_date")
	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	@XmlElement(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@XmlElement(name = "items")
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
	@XmlElement(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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

	public SalesOrderRequest(int id, String status, String location, int page, int rowsperpage, String order) {
		super();
		this.id = id;
		this.status = status;
		this.location = location;
		this.page = page;
		this.rowsperpage = rowsperpage;
		this.order = order;
	}

	public SalesOrderRequest() {
		super();
	}
}
