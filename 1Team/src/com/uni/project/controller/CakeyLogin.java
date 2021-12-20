package com.uni.project.controller;

import java.util.Map;

import com.uni.project.model.dao.CustomerManagement;
import com.uni.project.model.vo.Member;

public class CakeyLogin {
	
	public int login(CustomerManagement cm , String id, String pwd) {
		for (Member member : cm.MemberMap.values()) {
			if (member.getId().equals(id) && member.getPassword().equals(pwd)) {
				return 1;
			}
		}
		System.out.println("로그인에 실패하였습니다.");
		return 0;
	}
	
	public int storeLogin(String storeCode) {
		return 1;
	}

	public int systemLogin(String systemCode) {
		return 1;
	}


}
