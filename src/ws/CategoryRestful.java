package ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.Category;

@Path("category")
public class CategoryRestful {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCategory")
	public void getCategory() {
		//TODO : getCategory from DB
		
//		return Response.ok().entity(new GenericEntity<List<Category>>(result) {})
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
//				.build();
	}
}
