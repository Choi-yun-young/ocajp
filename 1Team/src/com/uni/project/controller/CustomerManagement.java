package com.uni.project.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.uni.project.model.vo.Member;

public class CustomerManagement {
	
	Scanner sc = new Scanner(System.in);
	
	LinkedList<Member>list = new LinkedList<Member>();
	
	public void newCustomer() {//회원가입
		
		//고객리스트
		System.out.println("*********회원가입***********");
		
		System.out.println("이름*");
		String name = sc.nextLine();
		
		System.out.println("아이디*");
		String id = sc.nextLine();
		
		System.out.println("비밀번호*");
		String password = sc.nextLine();
		
		System.out.println("비밀번호 확인*");
		String password1 = sc.nextLine();
		
		System.out.println("전화번호*");
		int phoneNum = sc.nextInt();
		sc.nextLine();
		
		list.add(new Member(name,id,password,password1,phoneNum));
		System.out.println("***********회원가입을 축하합니다.*************");
		
	
		
	}
	
	public void updateCunstomer() {//회원정보 수정
		
		System.out.println("----------회원정보 수정------------");
		
		System.out.println("이름*");
		String name = sc.nextLine();
		
		System.out.println("비밀번호*");
		String password = sc.nextLine();
		
		System.out.println("비밀번호 확인*");
		String password1 = sc.nextLine();
		
		System.out.println("전화번호*");
		int phoneNum = sc.nextInt();
		
		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(name)&&list.get(i).getPassword().equals(password)&&list.get(i).getPassword1().equals(password1)&&list.get(i).getPhoneNum()==(phoneNum)) {
				
				//로그인 성공
				
				System.out.println("아이디*");
				String id = sc.nextLine();
				
				list.remove(i);
				
				list.add(i,new Member(name,id,password,password1,phoneNum));
				
				System.out.println("//저장하기");
				System.out.println("//취소하기");
				System.out.println("//삭제하기");
				int selec = sc.nextInt();
				
				

				switch(selec) {
					case 1: System.out.println("저장되었습니다."); break;
					case 2: Cancle(); break;
					case 3: DeleteCustomer(); return;
					default: System.out.println("다시 입력해주세요."); break;
				}
			}
		}
	}
	
	public LinkedList<Member> Cancle() {
		
		System.out.println("취소하시겠습니까? (예  / 아니요)>>");
		
		String input = sc.nextLine();
		if(input.equals("예")) {
			
		}
		return list;
	}
	
	public void DeleteCustomer() {
		
		
		System.out.println("탈퇴하시겠습니까? (예  / 아니요)>>");
		
		String input = sc.nextLine();
   if(input.equals("예")) {
	   
	   list.remove();
			
		}else {
			return;
		}
		
	}
}
				