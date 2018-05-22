package ws;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import controller.LoginSrvc;
import dto.Response;
import dto.User;

@Path("login")
public class LoginRestful {
	
	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public String login(User user) {
		
		System.out.println("Login request|user: " + user.getUsername() + "|password: " + user.getUserPassword());
		LoginSrvc loginSrvc = new LoginSrvc(user.getUsername(), user.getUserPassword());
		Response loginResp = loginSrvc.checkUsernamePassword();
		
		String json  = "";
		ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(loginResp);
            System.out.println("JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
		return json;
	}
}
