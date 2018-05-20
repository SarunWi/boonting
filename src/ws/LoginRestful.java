package ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.Category;

@Path("login")
public class LoginRestful {
	
	@GET
	@Path("{user}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public void login(
			@PathParam("user") String user,
			@PathParam("password") String password) {
		
		System.out.println("Request|user: " + user + "|password: " + password);
		//TODO : check user & password
		
//		return Response.ok().entity(new GenericEntity<List<Category>>(result) {})
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
//				.build();
	}
	
	@GET
	@Path("{user}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public void register(
			@PathParam("user") String user,
			@PathParam("password") String password) {
		
		System.out.println("Request|user: " + user + "|password: " + password);
		//TODO : check user & password
		
//		return Response.ok().entity(new GenericEntity<List<Category>>(result) {})
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
//				.build();
	}
}
