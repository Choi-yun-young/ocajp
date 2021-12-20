package com.method2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.uni.project.controller.AscSearchList;
import com.uni.project.controller.DescSearchList;
import com.uni.project.model.dao.SearchDao2;
import com.uni.project.model.vo.Cake;


public class SearchManager2 {
	
	private Scanner sc = new Scanner(System.in);
	private SearchDao2 sd = new SearchDao2();

	public SearchManager2() {}

	public void newSearch() { // 새로운 서치
		
		sd.getCake(); // 등록한 케익을 복사한 리스트에 실행시킴
		
		System.out.println("케이크 찾기");
		System.out.println("검색어가 포함된 케이크 검색: ");
		String input = sc.nextLine();
		
		ArrayList<Cake> searchList = sd.newSearch(input); // 객체들이 cake인 리스트
		
		if(!(searchList.isEmpty())) {
			System.out.println(searchList.toString());
			
		} else {
			System.out.println("검색된 케익이 없습니다");

		}
	}
	
	public void dispalysearchList() { // 검색기록 조회
		
		ArrayList<Cake> searchList = sd.allsearch(); // 객체들이 cake인 리스트
		
		if(searchList.isEmpty()) {
			System.out.println("검색기록이 없습니다");
			return;
		}
		else {
			
			for(int i = 0; i < searchList.size(); i++) {
				System.out.println(searchList.get(i).toString());
			}
	
		}
	}
	
	public void deletesearch() {
		
		System.out.println("검색 기록을 삭제 하시겠습니까?(y/n) ");
		String input = sc.nextLine();

		if(input.equalsIgnoreCase("y")) {
			sd.deletsearch();
			
		} else {
			System.out.println("삭제 취소");
		}
	
	}
	
	public void sortsearch() {
		
		ArrayList<Cake> searchList = sd.allsearch(); // 객체들이 cake인 리스트
		
		System.out.println("검색 기록 정렬하기");
		System.out.println("1. 검색한 케이크 이름 오름차순");
		System.out.println("2 .검색한 케이크 이름 내림차순");
		
		int menu = sc.nextInt();
		sc.nextLine();
		
		if(menu == 1) {
			
			Collections.sort(searchList, new AscSearchList()); 
			for(Cake b: searchList) {
				System.out.println(b.toString()); 
			}
		} 
		else if(menu == 2) {
			Collections.sort(searchList, new DescSearchList());
			for(Cake b: searchList) {
				System.out.println(b.toString()); 
			}	
		}
		
	}
	
}
