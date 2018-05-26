package ws;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import controller.MaterialSrvc;
import dto.MaterialResp;

@Path("Material")
public class MaterialRestful {
	@GET
	@Path("getMaterialItems")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String getMaterialItems() 
	{
		System.out.println("getMaterialItems");
		MaterialSrvc materialSrvc = new MaterialSrvc();
		MaterialResp resp = materialSrvc.getAllMaterial();
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
