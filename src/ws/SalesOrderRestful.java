package ws;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import controller.SalesOrderSrvc;
import dto.Response;
import dto.SalesOrder;
import dto.SalesOrderResp;

@Path("salesorder")
public class SalesOrderRestful {
	
	@GET
	@Path("getSalesorders/{page: .*}/{rowsperpage: .*}/{order: .*}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String getSalesorders(@PathParam("page") int pageParam,
            @PathParam("rowsperpage") int rowsperpageParam,
            @PathParam("order") String orderParam) {
		
		System.out.println("getSalesorders request|page: " + pageParam + "|rowsperpage: " + rowsperpageParam + "|order: " + orderParam);

		int page = pageParam != 0 ? pageParam : 1; 
		int rowsperpage = rowsperpageParam != 0 ? rowsperpageParam : 100;
		String orderfield = orderParam.equals("") ? "sales_order_id": orderParam;
		
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
	
	@GET
	@Path("getSalesOrderById/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String getSalesOrderListById(@PathParam("id") int id) {
		System.out.println("getSalesOrderListById request|id: " + id);
		
		SalesOrderSrvc salesOrderSrvc = new SalesOrderSrvc();
		SalesOrderResp salesOrderResp = salesOrderSrvc.getSalesOrderById(id);
		
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
	
	@POST
	@Path("updateSalesOrder")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String updateSalesOrder(SalesOrder salesOrder) {
		System.out.println("updateSalesOrder");
		
		SalesOrderSrvc salesOrderSrvc = new SalesOrderSrvc();
		Response resp = salesOrderSrvc.updateSalesOrder(salesOrder);
		return "";
	}
	
	@POST
	@Path("insertSalesOrder")
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSalesOrder(SalesOrder salesOrder) {
		System.out.println("insertSalesOrder");
		
		SalesOrderSrvc salesOrderSrvc = new SalesOrderSrvc();
		Response resp = salesOrderSrvc.insertSalesOrder(salesOrder);
		return "";
	}
	
	@GET
	@Path("getSalesOrderGroupByLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSalesOrderGroupByLocation(Date fromDate, Date toDate) {
		System.out.println("getSalesOrderGroupByLocation");
		
		SalesOrderSrvc salesOrderSrvc = new SalesOrderSrvc();
		SalesOrderResp resp = salesOrderSrvc.getSalesOrderGroupByLocation(fromDate, toDate);
		return "";
	}
	
	@GET
	@Path("getSalesOrderGroupByCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSalesOrderGroupByCustomer(Date fromDate, Date toDate) {
		System.out.println("getSalesOrderGroupByCustomer");
		
		SalesOrderSrvc salesOrderSrvc = new SalesOrderSrvc();
		SalesOrderResp resp = salesOrderSrvc.getSalesOrderGroupByCustomer(fromDate, toDate);
		return "";
	}
}
