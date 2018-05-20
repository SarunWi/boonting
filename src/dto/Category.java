package dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "category")
public class Category {
	private int categoryId;
	private String categoryName;
	
	@XmlElement(name = "categoryId")
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	@XmlElement(name = "categoryName")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Category() {
		super();
	}
}
