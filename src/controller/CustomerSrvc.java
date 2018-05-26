package controller;
import java.util.List;
import dto.CustomerResp;
import dto.User;
import model.CustomerModel;
public class CustomerSrvc
{
	
	public CustomerSrvc()
	{
		
	}
	
	public CustomerResp getAllCustomer()
    {		
	   CustomerResp customerResp = new CustomerResp();
	   CustomerModel customer = new CustomerModel();	   
	   List<User> UserList = customer.getAllCustomer();
	   customerResp.setRecords(UserList);
	   customerResp.setTotalRecords(UserList.size()); 	   
		return customerResp;
    }
}
