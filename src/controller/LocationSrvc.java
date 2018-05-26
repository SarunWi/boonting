package controller;
import java.util.ArrayList;
import java.util.List;

import dto.Location;
import dto.LocationResp;
import dto.Response;
import model.LocationModel;
public class LocationSrvc {
		private int id;
		private String name;
		private float longitude;
		private float latitude;
		private String description;
		private String address;
		
		public LocationSrvc()
		{
		}
		
		
		public LocationSrvc(int id,String name,float longitude,float latitude,String description,String address)
		{
			this.id = id;
			this.name = name;
			this.longitude = longitude;
			this.latitude = latitude;
			this.description = description;
			this.address = address;
		}

       public Response checkLocationName()
       {
    	   LocationModel location = new LocationModel();
    	   int chk = location.checkLocationName(this.name);
    	   
    	   Response locationResp = new Response();
	   	   if(chk == 1) 
   		   {
		   		locationResp.setIsSuccess(true);
		   		locationResp.setErrorMessage("Duppicate Location name,Please your enter new location name!!");
   		   } 
	   	   else
   		   {
	   		locationResp = insertLocation();
   		   }
   		
   		return locationResp;
       }

       private Response insertLocation() 
       {
			LocationModel location = new LocationModel();
			int locationExist = location.insertLocation(this.name,this.longitude,this.latitude,this.description,this.address);
			Response locationResp = new Response();
			if(locationExist == 1) 
			{
				locationResp.setIsSuccess(true);
				locationResp.setErrorMessage("is save success");
			} else 
			{
				locationResp.setIsSuccess(false);
				locationResp.setErrorMessage("when save not success");
			}
			return locationResp;
       }
       
       public Response updateLocationName()
       {
    	   LocationModel location = new LocationModel();
    	   System.out.println("LocationId: " + id);
    	   int chk = location.checkLocationId(this.id);
    	   System.out.println("chk: " + chk);
    	   Response locationResp = new Response();
	   	   if(chk == 1) 
   		   {
	   		   //Update
	   		   	locationResp = updateLocation();
   		   } 
	   	   else
   		   {
	   		   locationResp.setIsSuccess(false);
	   		   locationResp.setErrorMessage("location is not in db");
	   		   //Really error message "No find Location Id"
   		   }
   		
   		return locationResp;
       }
       
       private Response updateLocation() 
       {
			LocationModel location = new LocationModel();
			int locationExist = location.updateLocation(this.id,this.name,this.longitude,this.latitude,this.description,this.address);
			Response locationResp = new Response();
			if(locationExist == 1) 
			{
				locationResp.setIsSuccess(true);
				locationResp.setErrorMessage("is save success");
			} 
			else 
			{
				locationResp.setIsSuccess(false);
				locationResp.setErrorMessage("when save not success");
			}
			return locationResp;
       }
       
       public LocationResp getAllLocation()
       {
    	   LocationResp locationResp = new LocationResp();
    	   LocationModel location = new LocationModel();
    	   
    	   List<Location> locationList = location.getAllLocation();
    	   
    	   locationResp.setRecords(locationList);
    	   locationResp.setTotalRecords(locationList.size());
    	   
   		return locationResp;
       }
}
