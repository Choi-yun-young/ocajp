package com.uni.project.model.vo;

import java.io.Serializable;

public class OrderInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String date;//예약날짜
	private String sName;//예약매장
	private int price;
	private String getId;
	private int no;
	
	public OrderInformation() {}
	
	
	public OrderInformation(int no, String sName, int price) {
		this.no = no;
		this.sName = sName;
		this.price = price;
	}

	
	public OrderInformation(int no,String sName, int price, String getId) {
		this.no = no;
		this.sName = sName;
		this.price = price;
		this.getId = getId;
	}


	public OrderInformation(String date, String sName, int price) {
		this.price = price;
		this.date = date;
		// TODO Auto-generated constructor stub
	}
	
	
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getGetId() {
		return getId;
	}


	public void setGetId(String getId) {
		this.getId = getId;
	}

	
	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	@Override
	public String toString() {
		return "예약날짜 : " + date + "" + ", 예약케이크 : " + sName + ", 가격 : " + price;
	}
}
