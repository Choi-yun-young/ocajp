package com.uni.project.view;

import java.util.Scanner;

import com.uni.project.controller.CakeyLogin;
import com.uni.project.controller.CakeyShopMenuManager;
import com.uni.project.controller.CustomerBlackList;
import com.uni.project.model.dao.CustomerManagement;

public class CakeyMenu {
	
	private Scanner sc = new Scanner(System.in);
	private CakeyShopMenuManager cm = new CakeyShopMenuManager();
	private CakeyLogin cl = new CakeyLogin();
	private CustomerManagement cm1 = new CustomerManagement();
	private CustomerBlackList cb = new CustomerBlackList();
	
	public void startMenu() {
		
		while (true) {
		
			System.out.println("========== Cakey ==========");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 종료");

			
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				cm1.newCustomer();
				break;
			case 2:
				logoIn();
				break;
			case 3:
				
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
			
		}
		
	}
	
	public void logoIn() {
		
		System.out.print("아이디를 입력하시오 : ");
		String id = sc.nextLine();
		 
		System.out.print("비밀번호를 입력하시오 : ");
		String pwd = sc.nextLine();
		
		int result = cl.login(cm1, id, pwd);
		 
		if (result == 1) {

			while(true) {

			System.out.println("===== 로그인 메뉴=====");
			System.out.println("1. 매장");
			System.out.println("2. 고객");
			System.out.println("3. 시스템(관리자)");
			System.out.print("메뉴 선택 : ");
			int num = sc.nextInt();
			
				switch(num) {
			
					case 1 : mainMenu(1);break;
					case 2 : mainMenu(2);break;
					case 3 : mainMenu(3);break;
			
				}
			}
		}
	}
	
	
	
	public void mainMenu(int select) {
		
		if(select == 1) {

			while (true) {
				
				System.out.println("========== 메인 메뉴 ==========");
				System.out.println("1. 케이크 등록");
				System.out.println("2. 케이크 조회");
				System.out.println("3. 케이크 등록 취소");
				System.out.println("4. 예약주문 조회");
				System.out.println("5. 공지사항 조회");
				System.out.println("6. 블랙리스트 관리");
				System.out.println("7. 돌아가기");
				System.out.println("메뉴 입력: ");
				int menu1 = sc.nextInt();
				sc.nextLine();
				
				switch (menu1) {
				case 1:
					cm.inputCake();
					break;
				case 2:
					cm.cakeAllList();
					break;
				case 3:
					cm.deleteCake();
					break;
				case 4:
					cm.reserveList();
					break;
				case 5:
					cm.storeNotice(); // 매장용 공지사항조회 추가		
					break;
				case 6:
					cb.manageBlackList(cm1, select);
					break;
				case 7 : 
					return;
				default:
					System.out.println("메뉴를 다시 선택하세요");
			
				}
				
			}
		
		
		} else if(select == 2) {
			
			while (true) {
				
				System.out.println("========== 메인 메뉴 ==========");
				System.out.println("1. 케이크 주문하기");
				System.out.println("2. 케이크 주문예약조회");
				System.out.println("3. 케이크 주문예약취소");
				System.out.println("4. 공지사항 조회");
				System.out.println("5. 메뉴 검색");
				System.out.println("6. 회원 정보 조회");
				System.out.println("7. 돌아가기");
				System.out.println("메뉴 입력: ");
				int menu2 = sc.nextInt();
				sc.nextLine();
				
				switch (menu2) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					cm.customerNoice(); // 고객용 공지사항 조회 추가
					break;
				case 5:
					NoticeMenu();
					break;
				case 6:
					
					break;
				case 7:
					return;
				default:
					System.out.println("메뉴를 다시 선택하세요");
				
				}
				
			
			}

		}else if(select == 3) {
	      
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
	            cb.manageBlackList(cm1, select);
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
		
	}

	public void NoticeMenu() {
		
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
	
	public void searchMenu() {
		
		while (true) {
			
			System.out.println("1. 메뉴검색");
			System.out.println("2. 검색리스트 조회");
			System.out.println("3. 검색리스트 삭제");
			System.out.println("4. 검색리스트 정렬");
			System.out.println("0. 돌아가기");
			
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				cm.newSearch();
				break;
			case 2:
				cm.dispalySearchList();
				break;
			case 3:
				cm.deleteSearch();
				break;
			case 4:
				cm.sortSearch();
				break;
			case 0:
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
		}
	
	}

}