package com.uni.project.controller;

import java.util.Scanner;

import com.uni.project.model.dao.CakeDao;
import com.uni.project.model.dao.OrderDao;
import com.uni.project.model.vo.Cake;

public class ShopManager {
	Scanner sc = new Scanner(System.in);
	Cake ck = new Cake();
	CakeDao cd = new CakeDao();
	CakeyManager cm = new CakeyManager();
	OrderDao od = new OrderDao();

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

	public void noticeList() {
		// 공지사항 조회
		cm.storeNotice();

	}

}
