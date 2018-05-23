package dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "location")
public class Location {
	private int locationId;
	private String locationName;
	private float locationLongitude;
	private float locationLatitude;
	private String locationDescription;
	
	@XmlElement(name = "locationId")
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	@XmlElement(name = "locationName")
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	@XmlElement(name = "locationLongitude")
	public float getLocationLongitude() {
		return locationLongitude;
	}
	public void setLocationLongitude(float locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	
	@XmlElement(name = "locationLatitude")
	public float getLocationLatitude() {
		return locationLatitude;
	}
	public void setLocationLatitude(float locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	
	@XmlElement(name = "locationDescription")
	public String getLocationDescription() {
		return locationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	
	public Location(int locationId, String locationName, float locationLongitude, float locationLatitude,
			String locationDescription) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationLongitude = locationLongitude;
		this.locationLatitude = locationLatitude;
		this.locationDescription = locationDescription;
	}
	
	public Location() {
		super();
	}
}
