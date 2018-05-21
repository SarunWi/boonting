package ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.Location;

@Path("location")
public class LocationRestful {
	@GET
	@Path("addLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public void addLocation() {
		//TODO : addLocation
		Location location = new Location();
	}
	
	@GET
	@Path("updateLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public void updateLocation() {
		//TODO : Get Sales Order List
		
	}
	
	@GET
	@Path("getLocationBySalesOrderId")
	@Produces(MediaType.TEXT_PLAIN)
	public void getLocationBySalesOrderId() {
		//TODO : getLocationBySalesOrderId
	}
}
