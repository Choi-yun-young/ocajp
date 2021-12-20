package com.uni.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.uni.project.model.dao.CakeDao;
import com.uni.project.model.dao.NoticeDao;
import com.uni.project.model.dao.OrderDao;
import com.uni.project.model.dao.SearchDao;
import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.Member;
import com.uni.project.model.vo.Notice;
import com.uni.project.model.vo.OrderInformation;
import com.uni.project.model.vo.Search;

public class CakeyShopMenuManager {
	
	
	private SearchDao sd = new SearchDao();
	private NoticeDao nd = new NoticeDao();
	private Scanner sc = new Scanner(System.in);
	Date today = new Date();
	OrderDao od = new OrderDao();
	CakeDao cd = new CakeDao();
	ArrayList<OrderInformation> oList = od.oList();
	private MemberDAO m = new MemberDAO();
	
	public int login(String id, String pwd) {
		
		Map<String, Member> MemberMap = m.getMemberMap();
		
		for (Member member : MemberMap.values()) {
			
			if(id.equals("시스템") && pwd.equals("시스템")) {
				
				return 3;
				
			} else if (id.equals("매장") && pwd.equals("매장")){
				
				return 2;
				
			}
			if (member.getId().equals(id) && member.getPassword().equals(pwd)) {
				
				
				return 1;
			}
		}
		System.out.println("로그인에 실패하였습니다.");
		return 0;
	}
	
	public void inputCake() { // 케이크 등록 선택시

		System.out.println("***** 케이크 등록 *****");
		System.out.println("1. 케이크정보 등록");
		System.out.println("0. 메인 메뉴로 돌아가기");

		int num = sc.nextInt();
		sc.nextLine();

		switch (num) {
		case 1:
			while (true) {
				System.out.println("***** 케이크 정보 등록 *****");
				System.out.println("상품명 : ");
				String name = sc.nextLine();

				System.out.println("가격 : ");
				int price = sc.nextInt();
				sc.nextLine();

				System.out.println("원재료('완료'를 입력하면 종료합니다) : "); // 원할때까지 계속 입력할 수 있게
				String ingred = "";

				while (true) {
					String ing = sc.nextLine();
					if (!(ing.equals("완료"))) { // 완료는 문자열에 추가되지 않게
						ingred += ing + ". ";
					} else if (ing.equals("완료")) {
						break;
					}
				}

				// 새 케이크 객체 만들고 CakeDao에 순서대로 전달
				// 케이크 등록번호, 이름, 가격, 원재료
				// 등록된 케이크가 없으면 1번
				try {
					cd.inputCake(new Cake(cd.getLastCakeNo() + 1, name, price, ingred));
				} catch (IndexOutOfBoundsException e) {
					cd.inputCake(new Cake(1, name, price, ingred));
				}

				System.out.println("정보 등록이 완료되었습니다!");
				cd.saveFile();

				System.out.println("계속 등록하시겠습니까? (Y/N)");
				char ch = sc.nextLine().toUpperCase().charAt(0);

				if (ch == 'Y') {
					continue;
				} else {
					System.out.println("등록 취소, 메인메뉴로 돌아갑니다.");
					return;
				}

			}
		}
	}

	public void cakeAllList() {

		// 현재 로그인한 매장정보 출력 메소드 추가하기

		// 등록된 케이크 리스트 전체출력
		// System.out.println(cd.ckList());

		// 한줄씩 출력
		for (Cake ck : cd.ckList()) {
			System.out.println(ck);
		}

	}

	public void deleteCake() {
		// 케이크 삭제메뉴

		for (Cake ck : cd.ckList()) {
			System.out.println(ck);
		}

		System.out.println("삭제할 케이크 번호를 입력하세요 : ");
		int num = sc.nextInt();
		sc.nextLine();

		Cake cake = cd.ckOne(num);// 삭제하려는게 몇번째 케이크인지 정의됨.

		if (cake == null) {
			System.out.println("삭제할 정보가 없습니다. 메인메뉴로 돌아갑니다.");
			cd.saveFile();
		} else {
			System.out.println(cake);
			System.out.println("정말 삭제하시겠습니까? ( Y / N )");
			char ch = sc.nextLine().toUpperCase().charAt(0);
			if (ch == 'Y') {
				cd.deleteCake(num);
				cd.saveFile();
				System.out.println(num + "번 케이크" + cake.getName() + "이(가) 삭제되었습니다.");

			}
		}

	}

	public void reserveList() {
		// 예약리스트 조회
		od.oList();
	}
	
	
	
		
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
															// 스트링빌더를 스트링형태로 저장
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
	
	public void newSearch() { // 새로운 서치
		
		sd.getCake();
		
		System.out.println("케이크 찾기");
		System.out.println("검색어가 포함된 케이크 검색: ");
		String input = sc.nextLine();
		
		Search s = sd.newSearch(input);
		
		if(s != null) {
			System.out.println(s.toString());
			sd.saveFile();
			
		} else {
			System.out.println("검색된 케익이 없습니다");

		}
	}
	
	public void dispalySearchList() { // 검색기록 조회
		
		Set<Search> searchList = sd.allsearch(); // 검색기록 중복됐을때 중복된건 안나옴
		
		if(searchList.isEmpty()) {
			System.out.println("검색기록이 없습니다");
			return;
		}
		else {
			
			Iterator<Search> it = searchList.iterator();
			while (it.hasNext()) {
				System.out.println((Search) it.next());
				
			}
	
		}
	}
	
	public void deleteSearch() {
		
		System.out.println("검색 기록을 삭제 하시겠습니까?(y/n) ");
		String input = sc.nextLine();

		if(input.equalsIgnoreCase("y")) {
			sd.deletsearch();
			
		} else {
			System.out.println("삭제 취소");
		}
	
	}
	
	public void sortSearch() {
		
		Set<Search> searchList = sd.allsearch();
		ArrayList<Search> searchList2 = new ArrayList<Search>(); // Collections.sort()기능 사용위해 List필요
		
		Iterator<Search> it = searchList.iterator();
		while (it.hasNext()) {
			searchList2.add((Search) it.next());
			
		}
		
		
		System.out.println("검색 기록 정렬하기");
		System.out.println("1. 검색한 케이크 이름 오름차순");
		System.out.println("2 .검색한 케이크 이름 내림차순");
		
		int menu = sc.nextInt();
		sc.nextLine();
		
		if(menu == 1) {
			
			Collections.sort(searchList2, new AscSearchList()); 
			for(Search b: searchList2) {
				System.out.println(b.toString()); 
			}
		} 
		else if(menu == 2) {
			Collections.sort(searchList2, new DescSearchList());
			for(Search b: searchList2) {
				System.out.println(b.toString()); 
			}	
		}
		
	}
}
