package com.gggstudio.chuctet.smstet.db;

public class SMSObject {
	private int id;
	private String content;
	private int favorited;
	private String content_non_accent;
	private String category_id;
	private int populate;
	
	public SMSObject() {
	}
	
	public SMSObject(int id, String content, int favorited,
                     String content_non_accent, String category_id, int populate) {
		super();
		this.id = id;
		this.content = content;
		this.favorited = favorited;
		this.content_non_accent = content_non_accent;
		this.category_id = category_id;
		this.populate = populate;
	}

	public int getPopulate() {
		return populate;
	}

	public void setPopulate(int populate) {
		this.populate = populate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFavorited() {
		return favorited;
	}
	public void setFavorited(int favorited) {
		this.favorited = favorited;
	}
	public String getContent_non_accent() {
		return content_non_accent;
	}
	public void setContent_non_accent(String content_non_accent) {
		this.content_non_accent = content_non_accent;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

}
