package ws;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import controller.SalesOrderSrvc;
import dto.SalesOrderRequest;
import dto.SalesOrderResp;

@Path("salesorder")
public class SalesOrderRestful {
	
	@GET
	@Path("getSalesorders")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String getSalesorders(SalesOrderRequest req) {
		
		System.out.println("getSalesorders request|page: " + req.getPage() + "|rowsperpage: " + req.getRowsperpage() + "|order: " + req.getOrder());

		int page = String.valueOf(req.getPage()) == "" ? req.getPage() : 1; 
		int rowsperpage = String.valueOf(req.getRowsperpage()) == "" ? req.getRowsperpage() : 100;
		String orderfield = req.getOrder();
		
		SalesOrderSrvc salesOrderSrvc = new SalesOrderSrvc();
		SalesOrderResp salesOrderResp = salesOrderSrvc.getSalesOrder(page, rowsperpage, orderfield);
		
		String json  = "";
		ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(salesOrderResp);
            System.out.println("JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
		return json;
	}
	
//	@GET
//	@Path("getSalesOrderListById")
//	@Produces(MediaType.TEXT_PLAIN)
//	public void getSalesOrderListById() {
//		//TODO : Get Sales Order List by id
//		
//		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
//		
//	}
//	
//	@POST
//	@Path("getSalesOrderListByState")
//	@Produces(MediaType.TEXT_PLAIN)
//	public void getSalesOrderListByState() {
//		//TODO : Get Sales Order List by id
//		
//		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
//		
//	}
//	
//	@POST
//	@Path("updateStatus")
//	@Produces(MediaType.TEXT_PLAIN)
//	public void updateStatus(String status, String remark) {
//		//TODO : Get Sales Order List by id
//		
//		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
//		
//	}
//	
//	@GET
//	@Path("getSalesOrderGroupByLocation")
//	@Produces(MediaType.TEXT_PLAIN)
//	public void getSalesOrderGroupByLocation(String status, String remark) {
//		//TODO : Get Sales Order List by id
//		
//		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
//		
//	}
//	
//	@GET
//	@Path("getSalesOrderGroupByCustomer")
//	@Produces(MediaType.TEXT_PLAIN)
//	public void getSalesOrderGroupByCustomer(String status, String remark) {
//		//TODO : Get Sales Order List by id
//		
//		List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();
//		
//	}
}
