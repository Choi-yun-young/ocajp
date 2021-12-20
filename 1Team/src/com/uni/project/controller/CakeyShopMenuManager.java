package com.uni.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.uni.project.model.dao.CakeDao;
import com.uni.project.model.dao.OrderDao;
import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.OrderInformation;
import com.uni.project.view.CakeyShopMenu;

public class CakeyShopMenuManager {
 
	Date today = new Date();
	OrderDao od = new OrderDao();
	CakeDao cd = new CakeDao();
	Scanner sc= new Scanner(System.in);
	
	public void selectcake() { //케익 정보 나옴
		CakeyShopMenu cme = new CakeyShopMenu();
		int index = 0;
					
		// 한줄씩 출력
		for (Cake ca : cd.ckList()) {
			System.out.println(ca);
		}
		
		System.out.print("어떤 케익을 주문 하시겠습니까? ");
		int cNo = sc.nextInt(); // 케익을 번호로 받음
		sc.nextLine();
		
		
		cakeToOrder(cNo); // 고른 케익메뉴를 보여줌
		
	}
	
	public void cakeToOrder(int cNo) {//  주문할 케익 창
		CakeyShopMenu cme = new CakeyShopMenu();
		
		
		// 케익명
		int cakePrice = 0;
		
		while(true) {
			
			System.out.println("*****주문할 케익*****");
			for(int i=0; i< cd.ckList().size(); i++) {
				
				// 케익 번호
				int cakeNo = cd.ckList().get(i).getCakeNo();
				// 케익명
				String cakeName = cd.ckList().get(i).getName();
				// 가격
				int price = cd.ckList().get(i).getPrice();
				
				if(cNo == cakeNo) {
					System.out.println(cakeName + "," +price+"원");
					cakePrice = price;
				}
			}
			
			// (케익이름 + "," + 가격 + "원")
			System.out.println("주문하시겠습니까?(아니오/예) : ");
			String yn = sc.nextLine();
			
			if(yn.equals("예")) { //예 이면
				cme.cakeCheck(cakePrice); // 예약할 날짜 기입창
			}else if(yn.equals("아니오")) { // 아니오면
				cme.shopIncakeOrder(); // 주문하기 창
			}else {
				System.out.println("잘못 입력하셨습니다.");
				continue; // 다시 노출
			
			}
		}
	}

	
	public void checkDate(int yy, int mm, int dd, int cakePrice) {
		CakeyShopMenu cme = new CakeyShopMenu();
		
		int year = (today.getYear() + 1900);
		int month = (today.getMonth() + 1);
		int day = today.getDate();
		
		int result1 =  year - yy; 
		int result2 =  month - mm;
		int result3 =  day - dd;
		
		//날짜 비교
		//현재년도 <= 입력년도 && 현재월 <= 입력월 && 12 >= 입력월 && 현재일-입력일 < -3 && 31>= 입력일
		//2021 <= 2022(t) && 12 <= 12(t) && 12>= 12(t) && 20-23=-3 <=-3(t) && 31>= 23(t)
		if(year <= yy && month <= mm && 12 >= mm && result3 <= -3 && 31 >= dd ) {
				saveOrder(yy, mm, dd, cakePrice); // 오더저장
		}else {
			System.out.println("날짜가 잘못 되었습니다. 다시 입력해주세요."); // 출력
			cme.cakeCheck(cakePrice);//돌아가서 다시 작성
		}
		
		
	}
	public void saveOrder(int yy, int mm, int dd, int cakePrice) { // 예약 완료 파일 저장;

		String orderDate = yy + "년" + mm + "월" + dd + "일";
		
		
		int count = 0; // 예약정보 넘버
		
		OrderInformation orderInformation = new OrderInformation(); //객체먼저 생성
		
		//예약넘버
		orderInformation.setNum(++count);
		// 예약일자
		orderInformation.setDate(orderDate);
		// 케익 가격
		orderInformation.setPrice(cakePrice);
		// 가게 이름 (한 곳 이므로 지정 하드코딩)
		orderInformation.setsName("어떤 좋은날 케이크");
		
		od.inputOrder(orderInformation);
		
	}
	
	public void orderConfirmation() { // 주문 확인 창
		
		System.out.println("*****주문 확인*****");
		
		//주문한 리스트
		System.out.println("예약 날짜 : " + od.oList().get(0).getDate());
		System.out.println("예약 매장 : " + od.oList().get(0).getsName());
		System.out.println("금액 : 총 " + od.oList().get(0).getPrice() + "원");
		
		System.out.println("*주문 수정은 불가능 합니다.");
		System.out.println("주문 수정이필요한 사항이 생기면 취소 후, 재주문 부탁드립니다.");
		System.out.println("주문완료! 메인으로 돌아갑니다.");
		//메인 메뉴 메소드로
		
		CakeyShopMenu cme = new CakeyShopMenu();
		cme.orderCheck();// 임의 작성 - 주문 조회
	}
	
	public void checkOrder() { // 주문 조회 출력
		int index = 0;
		

		ArrayList<OrderInformation> order = od.oList();
		for(int i = 0; i < order.size(); i++) {
		System.out.println(order.get(i).toString());
		}
		
		System.out.println("메인으로 돌아갑니다.");
//		return;
//		CakeyShopMenu cme = new CakeyShopMenu();// 임의작성
//		cme.orderCancel(); // 임의작성
		
		//메인 메뉴 메소드로
	}
	

	public void orderCancelCheack() {//취소할 주문 출력
		

		ArrayList<OrderInformation> order = od.oList();
		for(int i = 0; i < order.size(); i++) {
			System.out.println(order.size() + "크기");
			System.out.println(order.get(i).toString());
		}
		
		System.out.print("어떤걸 취소 하시겠습니까? ");
		int menu = sc.nextInt();//int로 입력받기
		
		System.out.println("-----취소 완료-----");
		// ("취소 예약 정보 : " + 날짜 + ", " + 매장명 + ", " + 가격 + "원")
		System.out.println("취소 예약 정보 : " + od.oList().get(menu).getDate() + ", " + od.oList().get(menu).getsName() + ", " + "원"); 
		System.out.println("메인으로 돌아갑니다.");

		od.orderOne(menu); // 추출
		return;
		//메인 메뉴 메소드로
	}
}
