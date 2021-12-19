package com.uni.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import com.uni.project.model.dao.CakeDao;
import com.uni.project.model.dao.NoticeDao;
import com.uni.project.model.dao.SearchDao;
import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.Notice;
import com.uni.project.model.vo.Search;

public class SearchManager {
	
	private Scanner sc = new Scanner(System.in);
	private SearchDao sd = new SearchDao();

	public SearchManager() {}

	public void newSearch() { // 새로운 서치
		
		sd.getCake();
		
		System.out.println("케이크 찾기");
		System.out.println("검색어가 포함된 케이크 검색: ");
		String input = sc.nextLine();
		
		Search s = sd.newSearch(input);
		
		if(s != null) {
			System.out.println(s.toString());
			
		} else {
			System.out.println("검색된 케익이 없습니다");

		}
	}
	
	public void dispalysearchList() { // 검색기록 조회
		
		ArrayList<Search> searchList = sd.allsearch();
		
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
		
		ArrayList<Search> searchList = sd.allsearch();
		
		System.out.println("검색 기록 정렬하기");
		System.out.println("1. 검색한 케이크 이름 오름차순");
		System.out.println("2 .검색한 케이크 이름 내림차순");
		
		int menu = sc.nextInt();
		sc.nextLine();
		
		if(menu == 1) {
			
			Collections.sort(searchList, new AscSearchList()); 
			for(Search b: searchList) {
				System.out.println(b.toString()); 
			}
		} 
		else if(menu == 2) {
			Collections.sort(searchList, new DescSearchList());
			for(Search b: searchList) {
				System.out.println(b.toString()); 
			}	
		}
		
	}
	
}
