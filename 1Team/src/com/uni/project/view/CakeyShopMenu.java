package com.uni.project.view;

import java.util.Date;
import java.util.Scanner;

import com.uni.project.controller.CakeyShopMenuManager;

public class CakeyShopMenu {
	
	private Scanner sc = new Scanner(System.in);
	private CakeyShopMenuManager cm = new CakeyShopMenuManager();
	
	public void shopIncakeOrder() { // 주문하기 창
		
		while(true) { // 잘못 적었을 시 다시 주문하기 위한 while문
			
			// 케익정보 읽어오기
			System.out.println("*****주문하기*****");
			System.out.println("-----매장이름-----"); // ((-----" + 가게이름 + "-----")
			
			cm.selectcake(); // 케익정보를 받아오는 메소드 - 케익정보 출력
			
		}
	}
	
	public void cakeCheck(int cakePrice) { // 예약할 날짜 
		Date today = new Date();
		
		while(true) {
			System.out.println("*****예약할 날짜*****");
			
			System.out.print("년도(YYYY) : ");
			int yy = sc.nextInt();// 년도 적기
			
			System.out.print("월(MM) : ");
			int mm = sc.nextInt(); // 월 적기
			System.out.println();
			
			System.out.print("일(DD) : ");
			int dd = sc.nextInt(); // 일 적기
			System.out.println();
			
			cm.checkDate(yy, mm, dd,  cakePrice); //예약할 날짜가 지난 날짜 인지 확인하러가는 메소드
			cm.orderConfirmation(); // 주문 확인 창
		}
	}
	
	
	public void orderCheck() { // 주문 예약 조회 창
		System.out.println("*****주문예약 조회*****");
		System.out.println("-----주문 정보-----");
		
		cm.checkOrder(); // 체크오더로가서 정보 값받아오기
	}
	
	public void orderCancel() { // 주문 예약 취소 창
		System.out.println("*****주문예약 취소*****");
		System.out.println("-----취소 가능한 주문-----");
		
		cm.orderCancelCheack(); // 캔슬 가능한 정보 받아옴
	}
}
