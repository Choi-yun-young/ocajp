package com.uni.project.model.vo;

public class Member {
	
	//기존고객, 신규고객의 정보

	private String name;
	private String Id;
	private String password;
	private String password1;
	private int phoneNum;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String name, String id, String password, String password1, int phoneNum) {
		super();
		this.name = name;
		Id = id;
		this.password = password;
		this.password1 = password1;
		this.phoneNum = phoneNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", Id=" + Id + ", password=" + password + ", password1=" + password1
				+ ", phoneNum=" + phoneNum + "]";
	}
	

}
