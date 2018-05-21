package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.User;

@Path("login")
public class LoginRestful {
	
	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public String login(User user) {
		
		System.out.println("Request|user: " + user.getUsername() + "|password: " + user.getUserPassword());
		//TODO : check user & password
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://122.155.202.161:5432/postgres", "postgres",
					"admin");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		return Boolean.TRUE.toString();
//		return Response.ok().entity(new GenericEntity<List<Category>>(result) {})
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
//				.build();
	}
}
