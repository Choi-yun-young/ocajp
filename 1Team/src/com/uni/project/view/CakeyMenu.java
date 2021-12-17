package com.uni.project.view;

import java.util.Scanner;

import com.uni.project.controller.CakeyManager;

public class CakeyMenu {
	
	private Scanner sc = new Scanner(System.in);
	private CakeyManager cm = new CakeyManager();
	
	public void startMenu() {
		
		while (true) {
		
			System.out.println("========== Cakey ==========");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			
			int menu = sc.nextInt();
			sc.nextLine();
			
			if(menu == 1) {
				
			} else if(menu == 2) {
				
			} else {
				System.out.println("메뉴를 다시 선택하세요");
			}
		
		}
		
	}
	
	public void mainMenu() {
		
		// if(매장로그인)
		System.out.println("========== 메인 메뉴 ==========");
		System.out.println("1. 케이크 등록");
		System.out.println("2. 케이크 조회");
		System.out.println("3. 케이크 등록 취소");
		System.out.println("4. 공지사항 조회");
		System.out.println("5. 블랙리스트 관리");
		System.out.println("메뉴 입력: ");
		int menu1 = sc.nextInt();
		sc.nextLine();
		
		//if(고객로그인)
		System.out.println("========== 메인 메뉴 ==========");
		System.out.println("1. 케이크 주문하기");
		System.out.println("2. 케이크 주문예약조회");
		System.out.println("3. 케이크 주문예약취소");
		System.out.println("4. 공지사항 조회");
		System.out.println("5. 매장 / 메뉴 검색");
		System.out.println("6. 회원 정보 조회");
		System.out.println("메뉴 입력: ");
		int menu2 = sc.nextInt();
		sc.nextLine();
		
		
		//if(관리자로근)
		while (true) {
			
			System.out.println("========== 메인 메뉴 ==========");
			System.out.println("1. 블랙리스트 관리");
			System.out.println("2. 공지사항 관리");
			System.out.println("0. 종료하기");
			System.out.println("메뉴 입력: ");
			int menu3 = sc.nextInt();
			sc.nextLine();
			
			switch (menu3) {
			case 1:
			
				break;
			case 2:
				NoticeMenu();
				break;
			case 0:
				System.out.println("Cakey 종료");
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
		}
		
	}

	private void NoticeMenu() {
		
		while (true) {

			System.out.println("========== 공지사항 관리 ==========");
			System.out.println("1. 공지사항 등록");
			System.out.println("2. 공지사항 조회");
			System.out.println("3. 공지사항 삭제");
			System.out.println("4. 공지사항 수정");
			System.out.println("0. 돌아가기");
			System.out.println("메뉴 입력: ");
			
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				cm.newNotice();
				break;
			case 2:
				cm.displayNotice();
				break;
			case 3:
				cm.deleteNotice();
				break;
			case 4:
				cm.modifyNotice();
				break;
			case 0:
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
			
		}

		
		
	}

}
