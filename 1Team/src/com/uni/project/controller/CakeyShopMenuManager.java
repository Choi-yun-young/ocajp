package com.uni.project.controller;

import java.util.ArrayList; 
import java.util.Date;
import java.util.Scanner;

import com.uni.project.model.dao.CakeDao;
import com.uni.project.model.dao.OrderDao;
import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.OrderInformation;

public class CakeyShopMenuManager {
	
	Date today = new Date();
	OrderDao od = new OrderDao();
	CakeDao cd = new CakeDao();
	Scanner sc= new Scanner(System.in);
	ArrayList<OrderInformation> oList = od.oList();
		
	public void shopIncakeOrder() { // 주문하기 창
		
		int  cNo = 0;
		
		while(true) { // 잘못 적었을 시 다시 주문하기 위한 while문
			
			// 케익정보 읽어오기
			System.out.println("*****주문하기*****");
			System.out.println("-----매장이름-----"); // ((-----" + 가게이름 + "-----")
			// 한줄씩 출력
			for (Cake ca : cd.ckList()) {
				System.out.println(ca);
			}
			
			System.out.print("어떤 케익을 주문 하시겠습니까? ");
			
			cNo = sc.nextInt(); // 케익을 번호로 받음
			sc.nextLine();
			
//			if(cNo == cd.ckList().get(cNo).getCakeNo()) {
			cakeToOrder(cNo); // 고른 케익메뉴를 보여줌
			//주문 완료 확인창 까지 뜨면 이쪽으로 돌아오기 때문에
			//메인으로 메소드
		}

	}
	
	
	public void cakeToOrder(int cNo) {//  주문할 케익 창
		
		
		// 케익명
		int cakePrice = 0;
		
		//주문할 케익이 맞는지 대답을 받기위한 while문
		while(true) {
			
			System.out.println("*****주문할 케익*****");
			
			//for문 - 케익 리스트 사이즈만큼 돌자
			for(int i=0; i< cd.ckList().size(); i++) {
				
				// 케익 번호
				int cakeNo = cd.ckList().get(i).getCakeNo();
				// 케익명
				String cakeName = cd.ckList().get(i).getName();
				// 가격
				int price = cd.ckList().get(i).getPrice();
				
				
				//if - 입력번호랑 케익번호가 같으면
				if(cNo == cakeNo) {
					// 케익번호,  가격 원
					System.out.println(cakeName + ", " + price +"원");
					//케익가격을 = 가격과 같게
					cakePrice = price;
				}
			}
			
			// (케익이름 + "," + 가격 + "원")
			System.out.println("주문하시겠습니까?(아니오/예) : ");
			String yn = sc.nextLine();
			
			if(yn.equals("예")) { //예 이면
				checkDate(cakePrice);// 예약할 날짜 기입창 -케익값 받고 감
				return;
			}else if(yn.equals("아니오")) { // 아니오면
				shopIncakeOrder(); // 주문하기 창
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
				continue; // 다시 노출
			}
		}
		return;
	}

	
	public void checkDate(int cakePrice) {
		
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
				break;
			}else {
				System.out.println("날짜가 잘못 되었습니다. 다시 입력해주세요."); // 출력
				continue;
			}
		}
		return;
		
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
		

		od.inputOrder(orderInformation); // 객체 리턴값을 여기로
		return;
		
	}
	
	public void orderConfirmation() { // 주문 확인 창
		
		while(true) {
			System.out.println("*****주문 확인*****");
			
			//주문한 리스트
			System.out.println("예약 날짜 : " + od.oList().get(0).getDate());
			System.out.println("예약 매장 : " + od.oList().get(0).getsName());
			System.out.println("금액 : 총 " + od.oList().get(0).getPrice() + "원");
			
			System.out.println("*주문 수정은 불가능 합니다.");
			System.out.println("주문 수정이필요한 사항이 생기면 취소 후, 재주문 부탁드립니다.");
			System.out.println("주문완료! 메인으로 돌아갑니다.");
			// 메인 메뉴 메소드로
		}
		
	}
	
	public void checkOrder() { // 주문 조회 출력
		int index = 0;
		
		System.out.println("*****주문예약 조회*****");
		System.out.println("-----주문 정보-----");

		ArrayList<OrderInformation> order = od.oList();
		for(int i = 0; i < order.size(); i++) {
		System.out.println(order.get(i));
		}
		
		System.out.println("메인으로 돌아갑니다.");
		// 메인 메뉴 메소드로
		
		
	}
	

	public void orderCancelCheack() {//취소할 주문 출력
		System.out.println("*****주문예약 취소*****");
		System.out.println("-----취소 가능한 주문-----");

		for(int i = 0; i < oList.size(); i++) {
			System.out.println(oList.get(i));
		}
		
		System.out.print("어떤걸 취소 하시겠습니까? ");
		int menu = sc.nextInt();//int로 입력받기
		
		System.out.println("-----취소 완료-----");
					// ("취소 예약 정보 : " + 날짜 + ", " + 매장명 + ", " + 가격 + "원")
		System.out.println("취소 예약 정보 : " + oList.get(menu-1).getDate() + ", " + oList.get(menu-1).getsName() + ", " + oList.get(menu-1).getPrice() + "원"); 
		System.out.println("메인으로 돌아갑니다.");

		od.deleteOrder(menu-1); // 추출
		//메인 메뉴 메소드로
	}
}
