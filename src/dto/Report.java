package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
public class Report {
	private int reportId;
	private String reportName;
	private String createBy;
	private Date createDate;
	
	@XmlElement(name = "reportId")
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	@XmlElement(name = "reportName")
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	@XmlElement(name = "createBy")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@XmlElement(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Report(int reportId, String reportName, String createBy, Date createDate) {
		super();
		this.reportId = reportId;
		this.reportName = reportName;
		this.createBy = createBy;
		this.createDate = createDate;
	}
	
	public Report() {
		super();
	}
}
