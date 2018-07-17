package com.gggstudio.chuctet.smstet.db;

public class CategoryObject {
	private int id_category;
	private String name_category;
	private String image_category;
	
	
	
	public CategoryObject() {
	}
	public CategoryObject(int id_category, String name_category,
			String image_category) {
		super();
		this.id_category = id_category;
		this.name_category = name_category;
		this.image_category = image_category;
	}


	public int getId_category() {
		return id_category;
	}
	
	
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public String getName_category() {
		return name_category;
	}
	public void setName_category(String name_category) {
		this.name_category = name_category;
	}
	public String getImage_category() {
		return image_category;
	}
	public void setImage_category(String image_category) {
		this.image_category = image_category;
	}
	
	
}
