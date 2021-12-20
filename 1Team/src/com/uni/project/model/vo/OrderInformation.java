package com.uni.project.model.vo;

public class OrderInformation {
	private String date;//예약날짜
	private String sName;//예약매장
	private int price;
	private int num; // 번호
	
	public OrderInformation() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public OrderInformation(int num, String date, String sName, int price) {
		this.price = price;
		this.date = date;
		this.num = num;
		// TODO Auto-generated constructor stub
}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
		return "예약 번호 : " + num + ", 예약날짜 : " + date + "" + ", 예약매장 : " + sName + ", 가격 : " + price;
	}
}
