package com.uni.project.model.vo;

import java.io.Serializable;

public class Search implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cakeName;
	private int cakePrice;
	private String cakeIngred;
	
	public Search() {}
	
	public Search(String cakeName, int cakePrice, String cakeIngred) {
		super();
		this.cakeName = cakeName;
		this.cakePrice = cakePrice;
		this.cakeIngred = cakeIngred;
	}


	public String getCakeName() {
		return cakeName;
	}

	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}

	public int getCakePrice() {
		return cakePrice;
	}

	public void setCakePrice(int cakePrice) {
		this.cakePrice = cakePrice;
	}

	public String getCakeIngred() {
		return cakeIngred;
	}

	public void setCakeIngred(String cakeIngred) {
		this.cakeIngred = cakeIngred;
	}

	@Override
	public String toString() {
		return "상품명 : " + cakeName + ", 가격 : " + cakePrice + ", 원재료 : " + cakeIngred;
	}
	
	
	

}
