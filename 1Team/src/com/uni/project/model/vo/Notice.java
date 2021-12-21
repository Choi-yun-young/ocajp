package com.uni.project.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice implements Serializable {
	   
	private static final long serialVersionUID = 1L;
	private int index;
	private String category;
	private String title;
	private String Contents;
	private Date upDate;
	
	public Notice() {}

	public Notice(String category, String title, Date upDate,String contents) {
		this.category = category;
		this.title = title;
		this.upDate = upDate;
		this.Contents = contents;

	}
	 
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	
	@Override
	public String toString() { // 일반조회시
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");

		return "["+index +". "+ title + "]"+"\n("+ sdf.format(upDate)+")\n";
	}

	public String toStringSystem() { // 관리자용조회
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
	
		return "[" + index +". " + title + "]"+"\n(" + sdf.format(getUpDate())+")   [" + category+ "]\n";
	}

	public String toStringAll() { // 상세조회
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		return "\n"+title + "\n(" + sdf.format(getUpDate())+")\n" + Contents;
	}
	

}
