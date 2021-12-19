package com.uni.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.uni.project.model.dao.SearchDao;
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
			sd.saveFile();
			
		} else {
			System.out.println("검색된 케익이 없습니다");

		}
	}
	
	public void dispalysearchList() { // 검색기록 조회
		
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
		
		Set<Search> searchList = sd.allsearch();
		ArrayList<Search> searchList2 = new ArrayList<Search>();
		
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