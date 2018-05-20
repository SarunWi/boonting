package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String userPassword;
	private String userAddress;
	private String userPhoneNumber;
	private Date userBirthDate;
	private String userEmail;
	private String username;
	
	@XmlElement(name = "userId")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@XmlElement(name = "userFirstName")
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	@XmlElement(name = "userLastName")
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	@XmlElement(name = "userPassword")
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@XmlElement(name = "userAddress")
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	@XmlElement(name = "userPhoneNumber")
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	
	@XmlElement(name = "userBirthDate")
	public Date getUserBirthDate() {
		return userBirthDate;
	}
	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
	
	@XmlElement(name = "userEmail")
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public User(int userId, String userFirstName, String userLastName, String userPassword, String userAddress,
			String userPhoneNumber, Date userBirthDate, String userEmail, String username) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userPhoneNumber = userPhoneNumber;
		this.userBirthDate = userBirthDate;
		this.userEmail = userEmail;
		this.username = username;
	}
	
	public User() {
		super();
	}
}
