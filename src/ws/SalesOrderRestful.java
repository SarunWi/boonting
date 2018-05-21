package ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.SalesOrder;

@Path("salesorder")
public class SalesOrderRestful {
	
	@GET
	@Path("getSalesOrderList")
	@Produces(MediaType.TEXT_PLAIN)
	public void getSalesOrderList() {
		//TODO : Get Sales Order List
		
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
	}
	
	@GET
	@Path("getSalesOrderListById")
	@Produces(MediaType.TEXT_PLAIN)
	public void getSalesOrderListById() {
		//TODO : Get Sales Order List by id
		
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
	}
	
	@GET
	@Path("getSalesOrderListByState")
	@Produces(MediaType.TEXT_PLAIN)
	public void getSalesOrderListByState() {
		//TODO : Get Sales Order List by id
		
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
	}
	
	@GET
	@Path("updateStatus")
	@Produces(MediaType.TEXT_PLAIN)
	public void updateStatus(String status, String remark) {
		//TODO : Get Sales Order List by id
		
		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
		
	}
}
