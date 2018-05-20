package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "timetable")
public class Timetable {
	private int timetableId;
	private Date timetableFromTime;
	private Date timetableToTime;
	private String timetableStatus;
	
	@XmlElement(name = "timetableId")
	public int getTimetableId() {
		return timetableId;
	}
	public void setTimetableId(int timetableId) {
		this.timetableId = timetableId;
	}
	
	@XmlElement(name = "timetableFromTime")
	public Date getTimetableFromTime() {
		return timetableFromTime;
	}
	public void setTimetableFromTime(Date timetableFromTime) {
		this.timetableFromTime = timetableFromTime;
	}
	
	@XmlElement(name = "timetableToTime")
	public Date getTimetableToTime() {
		return timetableToTime;
	}
	public void setTimetableToTime(Date timetableToTime) {
		this.timetableToTime = timetableToTime;
	}
	
	@XmlElement(name = "timetableStatus")
	public String getTimetableStatus() {
		return timetableStatus;
	}
	public void setTimetableStatus(String timetableStatus) {
		this.timetableStatus = timetableStatus;
	}
	
	public Timetable(int timetableId, Date timetableFromTime, Date timetableToTime, String timetableStatus) {
		super();
		this.timetableId = timetableId;
		this.timetableFromTime = timetableFromTime;
		this.timetableToTime = timetableToTime;
		this.timetableStatus = timetableStatus;
	}
	
	public Timetable() {
		super();
	}
}
