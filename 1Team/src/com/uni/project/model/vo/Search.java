package com.uni.project.model.vo;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		// (cakeName, cakePrice, cakeIngred).hashCode();
		return Objects.hash(cakeName, cakePrice, cakeIngred);
	}

	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof Search)) {
			return false;
		}
		
		Search other = (Search)obj;
		
		if(this.cakeName.equals(other.cakeName) && this.cakePrice == other.cakePrice && this.cakeIngred.equals(other.cakeIngred)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	

}
