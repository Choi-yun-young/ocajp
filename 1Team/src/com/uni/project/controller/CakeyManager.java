package com.uni.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.uni.project.model.dao.NoticeDao;
import com.uni.project.model.vo.Notice;

public class CakeyManager {
	
	private NoticeDao nd = new NoticeDao();
	private Scanner sc = new Scanner(System.in);
	
	public CakeyManager() {}
	
	public void newNotice() { // 공지사항 등록
		
		Date today = new Date();
		
		System.out.println("새로운 공지사항 등록");
		
		System.out.println("\n공지사항 카테고리 입력(공통,매장,고객): ");
		String category = sc.nextLine();
		
		System.out.println("\n공지사항 제목 입력: ");
		String title = sc.nextLine();
		
		System.out.println("\n공지사항 내용 입력(exit입력시 입력종료): ");
		
		StringBuilder sb = new StringBuilder();
		String Contents = null;
		
		while (true) {
			
			Contents = sc.nextLine();
		
			if(Contents.equalsIgnoreCase("exit")) {
				break;
			} else {
				sb.append("\n");	
				sb.append(Contents);
			}
		
		}
		
		System.out.println("\n공지사항을 등록하시겠습니까?(y/n): ");
		String answer = sc.nextLine();
		
		if(answer.equalsIgnoreCase("y")) {
			
			nd.newNotice(new Notice(category, title, today, sb.toString()));
			nd.saveNoiceFile(); // 작업마무리때마자 저장
			
		} else {
			System.out.println("\n등록 취소\n");

		}

		
	}
	
	public void displayNotice() { // 관리자용 공지사항 조회
		
		ArrayList<Notice> nList = nd.allNotice();
		
		if(nList.isEmpty()) {
			System.out.println("\n등록된 공지사항이 없습니다\n");
			return;
		}
		
		// 모든 공지사항 제목, 날짜만 조회
		for(Notice n : nList) {
			System.out.println(n.toStringSystem());
		}
		
		while (true) { 
		// 모든 공지사항제목을 보면서 선택한 공지 사항 상세조회 반복

			System.out.println("상세 조회 할 공지사항 번호: ");
			System.out.println("(0 입력시 이전메뉴)\n");
	
			int index = sc.nextInt();
			sc.nextLine();
			
			if(index == 0) {
				return;
			}
			
			Notice n = nd.selectNotice(index); 
			// 해당공지의 갖고옴
			// 상세내용출력
			try {
				// 선택한 번호의 공지사항이 있는 것만 상세히 출력
				System.out.println(n.toStringAll());
		
			} catch (NullPointerException e) {
				// 없으면 한줄띄우고 반복
				System.out.println("\n");
			}
		
		}

	}
	
	
	public void deleteNotice() { // 공지사항 삭제

		ArrayList<Notice> nList = nd.allNotice();
		
		if(nList.isEmpty()) {
			System.out.println("\n등록된 공지사항이 없습니다\n");
			return;
		}
		for(Notice n : nList) {
			System.out.println(n.toStringSystem());
		}
		// 모든 공지리스트에서 제목과 날짜만 보고 삭제선택
		
		while (true) {
		
			System.out.println("삭제 할 공지사항 번호: ");
			System.out.println("(0 입력시 이전메뉴)\n");
			int index = sc.nextInt();
			sc.nextLine();
			
			if(index == 0) {
				return;
			}
	
			Notice n = nd.selectNotice(index);
			
			// 없는 번호 공지사항 삭제하려하는지 확인
			if(n == null) {
				continue;
			} else { 
				
				// null 확인받고 입력받기
				System.out.println("\n"+index+"번 공지사항을 삭제하시겠습니까?(y/n): ");
				String answer = sc.nextLine();
				
				if(answer.equalsIgnoreCase("y")) {
					nd.deletNotice(index);
					nd.saveNoiceFile(); // 작업마무리때마자 저장
					
				} else {
					System.out.println("\n삭제 취소\n");
				
				}
			}
			
		}	
		
	}

	public void modifyNotice() { // 공지사항 수정

		ArrayList<Notice> nList = nd.allNotice();
		
		if(nList.isEmpty()) {
			System.out.println("\n등록된 공지사항이 없습니다\n");
			return;
		}
		for(Notice n : nList) {
			System.out.println(n.toStringSystem());
		}
		// 모든 공지사항 제목 날짜만 보고 수정 선택
		
		while(true) {
			
			System.out.println("수정 할 공지사항 번호: ");
			System.out.println("(0 입력시 이전메뉴)");
			int index = sc.nextInt();
			sc.nextLine();
			
			if(index == 0) {
				return;
			} 
			
			Notice n = nd.selectNotice(index);
			// 없는번호 공지 수정하려는 건지 확인
			if(n != null) {
				
				System.out.println("1. 제목수정");
				System.out.println("2. 내용수정");
				
				int modi = sc.nextInt();
				sc.nextLine();
				
				if(modi == 1) {
					
					System.out.println("수정할 제목 입력: ");
					String title = sc.nextLine();
					nd.modifyNoticeTitle(index, title);
					nd.saveNoiceFile(); // 작업마무리때마자 저장
					
				} else if (modi == 2){
						
					System.out.println("수정할 공지사항 내용 입력: ");
					System.out.println("(exit입력시 입력종료)");
					
					StringBuilder sb = new StringBuilder();
					String sujung = null;
					
					while (true) {
						
						sujung = sc.nextLine();
					
						if(sujung.equalsIgnoreCase("exit")) {
							break;
						} else {
							sb.append(sujung);
							sb.append("\n");	
						}
						
					}
					String content = sb.toString();
					nd.modifyNoticeContent(index, content); 
					nd.saveNoiceFile(); // 작업마무리때마자 저장
					
				} 
			
			} 
			System.out.println();
		}

	}
	
	public void storeNotice() { // 매장공지사항조회기능
		
		String input = "매장";
		ArrayList<Notice> selecList = nd.storeNotice(input);
		
		if(selecList.isEmpty()) {
			System.out.println("\n등록된 공지사항이 없습니다\n");
			System.out.println();
			return;
		} else {
			
			for(int i = 0; i < selecList.size(); i++) {
				System.out.println(selecList.get(i).toString());
			}
			
		}
		
		while (true) { 
			// 선택된 공지사항의 제목을 보면서 원하는 공지 사항 상세조회 반복

			System.out.println("상세 조회 할 공지사항 번호: ");
			System.out.println("(0 입력시 이전메뉴)");
	
			int index = sc.nextInt();
			sc.nextLine();
			
			if(index == 0) {
				return;
			}
			
			for(int i = 0; i < selecList.size(); i++) {
				
				if(index == selecList.get(i).getIndex()) {
					Notice n = nd.selectNotice(index); 
					System.out.println(n.toStringAll());	
				} 
				
			}
			System.out.println();
		}
		
	}

	public void customerNoice() { // 고객공지사항기능
		
		String input = "고객";
		ArrayList<Notice> selecList = nd.customerNotice(input);
		
		if(selecList.isEmpty()) {
			System.out.println("\n등록된 공지사항이 없습니다\n");
			System.out.println();
			return;
		} else {
			
			for(int i = 0; i < selecList.size(); i++) {
				System.out.println(selecList.get(i).toString());
			}
			
		}
		
		while (true) { 
			// 선택된 공지사항의 제목을 보면서 원하는 공지 사항 상세조회 반복

			System.out.println("상세 조회 할 공지사항 번호: ");
			System.out.println("(0 입력시 이전메뉴)");
		
			int index = sc.nextInt();
			sc.nextLine();
			
			if(index == 0) {
				return;
			}
			// 출력된 공지사항의 번호만 입력하게 하기
			// 상세내용출력
			
			for(int i = 0; i < selecList.size(); i++) {
	
				if(index == selecList.get(i).getIndex()) {
					Notice n = nd.selectNotice(index); 
					System.out.println(n.toStringAll());
	
				}

			}
			System.out.println();
		}
		
		
	}
	
}
