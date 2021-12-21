package com.uni.project.model.vo;

import java.io.Serializable;

public class Cake implements Serializable {

	private static final long serialVersionUID = -777200058529646842L; //객체 입출력용
	// 직렬화
 
	// 상품명, 가격, 재료... 재료 여러개 받기?
	private int cakeNo;

	private String name;
	private int price;
	private String ingred;

	public Cake() {
 
	}

	public Cake(int cakeNo, String name, int price, String ingred) {
		super();
		this.cakeNo = cakeNo;
		this.name = name;
		this.price = price;
		this.ingred = ingred;
	}

	public int getCakeNo() {
		return cakeNo;
	}

	public void setCakeNo(int cakeNo) {
		this.cakeNo = cakeNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIngred() {
		return ingred;
	}

	public void setIngred(String ingred) {
		this.ingred = ingred;
	}

	@Override
	public String toString() {
		return cakeNo + ". 상품명 : " + name + ", 가격 : " + price + ", 원재료 : " + ingred;
	}

}