package controller;

import dto.Response;
import model.LoginModel;

public class LoginSrvc {
	
	private String username;
	private String password;

	public LoginSrvc(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Response checkUsernamePassword() {
		LoginModel login = new LoginModel();
		int userExist = login.checkUsernamePassword(this.username, this.password);
		
		Response loginResp = new Response();
		if(userExist == 1) {
			loginResp.setSuccess(true);
			loginResp.setErrorMessage("");
		} else {
			loginResp.setSuccess(false);
			loginResp.setErrorMessage("invalid username or password");
		}
		
		return loginResp;
	}
	
}
