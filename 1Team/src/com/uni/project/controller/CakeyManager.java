package com.uni.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.uni.project.model.vo.Notice;

public class CakeyManager {
	
	private Scanner sc = new Scanner(System.in);
	
	public CakeyManager() {}
	
	public void newNotice(/*manager m*/) {
		
//		if(m.getId.equals("manager01") && m.getPwd.equals("m1234") {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		
		System.out.println("공지사항 카테고리 입력: ");
		String category = sc.nextLine();
		System.out.println("공지사항 제목 입력: ");
		String title = sc.nextLine();
		System.out.println("공지사항 내용 입력: ");
		String Contents = sc.nextLine();
		
		Date today = new Date();
		
		/*담을 공간 만들기=*/ new Notice(category, title, Contents, today);
		

	
	}

	public void displayNotice() {
		// TODO Auto-generated method stub
		
	}

	public void deleteNotice() {
		// TODO Auto-generated method stub
		
	}

	public void modifyNotice() {
		// TODO Auto-generated method stub
		
	}



	
}
