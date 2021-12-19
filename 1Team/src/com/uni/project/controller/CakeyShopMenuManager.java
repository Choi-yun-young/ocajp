package com.uni.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import com.uni.project.model.dao.OrderDao;
import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.OrderInformation;
import com.uni.project.view.CakeyShopMenu;

public class CakeyShopMenuManager {
	
	Date today = new Date();
	Calendar cal = Calendar.getInstance();
	ArrayList<Cake> cList = new ArrayList<>();
	OrderDao od = new OrderDao();
	Scanner sc= new Scanner(System.in);
	ArrayList<OrderInformation> oList = new ArrayList<>();
	
	public void selectcake() { //케익 정보 나옴
		CakeyShopMenu cme = new CakeyShopMenu();
		int index = 0;
		
		//FileReader fs = new FileReader("cake.txt");
					
		
		for(int i = 0 ; i < cList.size() ; i++) { // 가게마다 등록된 케이크 정보를 출력하기위해 for문 돌림
			//텍스트 파일에 담긴 케이크 정보이기 때문에 케이크리스트.size돌려야함 
			
			System.out.println(++index + ". " + cList.get(i).getName() + "-" + cList.get(i).getPrice() + "원");
			// 케익넘버 + 케익이름 + "-" + 케익가격 + "원";
			
		}
		System.out.print("어떤 케익을 주문 하시겠습니까? ");
		int cNo = sc.nextInt(); // 케익을 번호로 받음
		sc.nextLine();
		
		// for문 이용 (케익 넘버를 추출)
		for(int i = 0 ; i < cList.size() ; i++) {
		
			cakeToOrder(cNo-1); // 고른 케익메뉴를 보여줌
			}
		
		
		
	}
	
	public void cakeToOrder(int cNo) {//  주문할 케익 창
		CakeyShopMenu cme = new CakeyShopMenu();
		
		
		while(true) {
			System.out.println("*****주문할 케익*****");
			System.out.println(cList.get(cNo).getName() + ", " + cList.get(cNo).getPrice() + "원");
			// (케익이름 + "," + 가격 + "원")
			System.out.println("주문하시겠습니까?(아니오/예) : ");
			String yn = sc.nextLine();
			
			if(yn.equals("예")) { //예 이면
				cme.cakeCheck(); // 예약할 날짜 기입창
			}else if(yn.equals("아니오")) { // 아니오면
				cme.shopIncakeOrder(); // 주문하기 창
			}else {
				System.out.println("잘못 입력하셨습니다.");
				continue; // 다시 노출
			}
		}
	}

	
	public void checkDate(int yy, int mm, int dd) {
		CakeyShopMenu cme = new CakeyShopMenu();
		
		int year = (today.getYear() + 1900);
		int month = (today.getMonth() + 1);
		int day = today.getDate();
		
		int result1 =  year - yy; 
		int result2 =  month - mm;
		int result3 =  day - dd;
		
		
		if(result1 >= 0) { // 현재 년도 - 입력받은년도 >= 0이면 다시입력
			//ex.2021 - 2021 = 0 > 0 트루이므로 실행
			if(mm < month) {
				System.out.println("날짜가 잘못 되었습니다. 다시 입력해주세요."); // 출력
				cme.cakeCheck(); //돌아가서 다시 작성
			}else if(year == yy) {
				saveOrder(yy, mm, dd); // 오더저장
			}
			System.out.println("날짜가 잘못 되었습니다. 다시 입력해주세요."); // 출력
			//돌아가서 다시 작성
		}else if(result2 > 0 && 12 < mm || year - yy < 0) { // 현재 월 - 입력받은월 <= 0 || 최대월 수를 넘김 || 재년도 - 입력받은년도 <= 0이면 다음창으로
			//ex.2021-2021 = 0 < 0
				//ex.12 - 11 = -1 < 0트루이므로 실행
			System.out.println("날짜가 잘못 되었습니다. 다시 입력해주세요."); // 출력
			cme.cakeCheck(); //돌아가서 다시 작성
		}else if(result3 > -3 && 31 < dd || year - yy > 0) { // 현재 일 - 입력받은 일 <= -3 || 최대 일을 넘김 || 다시 년도 체크
									//2021 - 2021 = 0 >= 0 트루이므로 다음 페이지
				// 최소 3일전 예약 이니 음수처리
				//ex.28-31 = -2 <= -3 이면 트루이므로 실핼
				//ex.23-1 = 22 <= -3 false\
			System.out.println("날짜가 잘못 되었습니다. 다시 입력해주세요."); // 출력
			cme.cakeCheck(); //돌아가서 다시 작성
		}else {
			saveOrder(yy, mm, dd); // 오더저장
		}
		
		
	}
	
	public void saveOrder(int yy, int mm, int dd) { // 예약 완료 파일 저장;
		Date date1 = new Date(yy, mm, dd);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년도MM월dd일");
		String so = sdf.format(date1);
		int count = 0; // 예약정보 넘버
		
		int cPrice = cList.get(/*입력받았던케익의 인덱스 어떻게 받지*/).getPrice();
		 
		//예약이 있을경우
		new OrderInformation(++count, so, "가게이름", cPrice);
		
		// 예약이 없엇을경우
		new OrderInformation(1, so, "가게이름", cPrice);
		
		try {
			od.writeOrder(new OrderInformation(od.getLastCakeNo() + 1, so, "가게이름", cPrice));
		}catch(IndexOutOfBoundsException e ) {
			od.writeOrder(new OrderInformation(1, so, "가게이름", cPrice));
		}
		
		orderConfirmation(); // 주문 확인 창
	}
	
	public void orderConfirmation() { // 주문 확인 창
		
		System.out.println("*****주문 확인*****");
		System.out.print("예약 날짜 : " + oList.get(oList.size()).getDate()); // ("예약 날짜 : " + 예약날짜)
		
		System.out.println("예약 매장 : 매장이름"); // ("예약 매장 : " + 매장이름)
		
		System.out.println("금액 : 총 " + oList.get(oList.size()).getPrice() + "원"); // ("금액 : 총 " + 가격 + "원")
		
		System.out.println("*주문 수정은 불가능 합니다.");
		System.out.println("주문 수정이필요한 사항이 생기면 취소 후, 재주문 부탁드립니다.");
		System.out.println("주문완료! 메인으로 돌아갑니다.");
		//메인 메뉴 메소드로
	}
	
	
	public void readfile() {
		CakeyShopMenu cme = new CakeyShopMenu();// 임의작성
		
		for(int i = 0 ; i < oList.size() ; i++) {
			oList.get(i).toString();
		}
		// 파일을 읽기위함
		cme.orderCheck();//실행을위한 임의 작성
		//메인메뉴 메소드로
	}
	
	public void checkOrder() {
		int index = 0;
		
		//Iterrator문 이용 (조회할 예약 노출 - 날짜별로)
		Iterator<OrderInformation> it = new LinkedList(oList).descendingIterator();
		while(it.hasNext()) { // 꺼낼 요소가 있는지 확인
			System.out.print(++index);
			System.out.println(it.next()); // 현재 요소를 꺼내서 출력
		}
		System.out.println("메인으로 돌아갑니다.");
		CakeyShopMenu cme = new CakeyShopMenu();// 임의작성
		cme.orderCancel(); // 임의작성
		
		//메인 메뉴 메소드로
	}
	

	public void orderCancelCheack() {
		CakeyShopMenu cme = new CakeyShopMenu();
		
		//Iterrator문 이용 (취소 가능한 예약 노출 - 날짜별로)
		Iterator<OrderInformation> it =  new LinkedList(oList).descendingIterator(); // a1에 있는 객체들을 Iterator에 담는 과정
		while(it.hasNext()) { // 꺼낼 요소가 있는지 확인
			System.out.println(it.next()); // 현재 요소를 꺼내서 출력
		}
		System.out.print("어떤걸 취소 하시겠습니까? ");
		int menu = sc.nextInt();//int로 입력받기
		
		for(int i = 0 ; i < oList.size() ; i++) {
			oList.remove(menu);
		}
		cme.completeDeleteOrder(); // 다음으로
	}
}
