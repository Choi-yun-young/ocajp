package com.uni.project.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice {
	
	private int index;
	private String category;
	private String title;
	private String Contents;
	private Date upDate;

	public Notice() {}

	public Notice(String category, String title, String contents, Date upDate) {
		this.category = category;
		this.title = title;
		this.Contents = contents;
		this.upDate = upDate;
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
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		return "공지사항 [번호= " + index + " ,제목= " + title + ", 내용= " + Contents + "]"+"\n공지사항등록일= " + sdf.format(getUpDate());
	}
	
	
	
}
