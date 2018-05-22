package ws;

import java.io.IOException;

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
	public Response updateSalesOrder(SalesOrder salesOrder) {
		System.out.println("updateSalesOrder ");
		Response resp = new Response();
		
		return resp;
	}
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
