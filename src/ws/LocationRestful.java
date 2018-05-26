package ws;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import controller.LocationSrvc;
import dto.Location;
import dto.LocationResp;
import dto.Response;

@Path("Location")
public class LocationRestful {	
	
	@POST
	@Path("insertLocation")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String addLocation(Location location)
		{
			System.out.println("Location request|"+ "location_name: " + location.getLocationName()+"|location_longitude: " + location.getLocationLongitude()+"|location_latitude: " + location.getLocationLatitude()+"|location_description: " + location.getLocationDescription()+"|location_address: " + location.getLocationAddress());
			LocationSrvc locationSrvc = new LocationSrvc(location.getLocationId(),location.getLocationName(),location.getLocationLongitude(),location.getLocationLatitude(),location.getLocationDescription(),location.getLocationAddress());
			Response locationResp = locationSrvc.checkLocationName();
			String json  = "";
			ObjectMapper mapper = new ObjectMapper();
	        try 
	        {
	            json = mapper.writeValueAsString(locationResp);
	            System.out.println("JSON = " + json);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
				e.printStackTrace();
			}
			return json;
		}
	
	@POST
	@Path("updateLocation")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String updateLocation(Location location) 
	{
		System.out.println("Location request|"+ "location_id: " + location.getLocationId()+"|location_name: " + location.getLocationName()+"|location_longitude: " + location.getLocationLongitude()+"|location_latitude: " + location.getLocationLatitude()+"|location_description: " + location.getLocationDescription()+"|location_address: " + location.getLocationAddress());
		LocationSrvc locationSrvc = new LocationSrvc(location.getLocationId(),location.getLocationName(),location.getLocationLongitude(),location.getLocationLatitude(),location.getLocationDescription(),location.getLocationAddress());
		Response locationResp = locationSrvc.updateLocationName();
		String json  = "";
		ObjectMapper mapper = new ObjectMapper();
        try 
        {
            json = mapper.writeValueAsString(locationResp);
            System.out.println("JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
		return json;	
	}
	
	@GET
	@Path("getLocations")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String getLocation() 
	{
		System.out.println("getLocation");
		//System.out.println("getLocation request|LocationId: " + req.getLocationId() + "|LocationName: " + req.getLocationName() + "|LocationLongitude: " + req.getLocationLongitude() + "|LocationLatitude: " + req.getLocationLatitude()+ "|LocationDescription: " + req.getLocationDescription()+ "|LocationAddress: " + req.getLocationAddress());
		LocationSrvc locationSrvc = new LocationSrvc();
		LocationResp resp = locationSrvc.getAllLocation();
		String json  = "";
		ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(resp);
            System.out.println("JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
