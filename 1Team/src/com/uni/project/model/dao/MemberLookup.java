package com.uni.project.model.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.uni.project.model.vo.Member;

public class MemberLookup {//회원 조회
	
	private Scanner sc = new Scanner(System.in);
	ArrayList<Member> arr = new ArrayList<Member>();
	   
	public MemberLookup() {}
	
	

	public void  Search(){
		ArrayList<Member>list = new ArrayList<Member>();
		
	System.out.println("**********회원조회*********");
		
		System.out.println("1.이름/아이디 검색 : ");
		System.out.println("2.회원 전체 조회");
	
		while(true) {
			System.out.println("선택 : ");
		
	
		 if(list.isEmpty()) {
				
				System.out.println("검색결과가 없습니다.");
				
			}else {
				System.out.println("--------------------------");
			
				System.out.printf("%-10s %-10s %-10 s%n","id","name","regDate");
			
				System.out.println("--------------------------");
			
			for(Member m : list) {
				System.out.printf("%-10s %-10s %-10 s%n",m.getId(),m.getName(),new SimpleDateFormat("yy-MM-dd HH:mm").format(m.getRegDate()));
				
				
			}
			
			System.out.println("--------------------------");
			}	
	}

	
}
}
