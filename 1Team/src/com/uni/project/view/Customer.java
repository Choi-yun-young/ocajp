package com.uni.project.view;

import java.util.List;
import java.util.Scanner;


import com.uni.project.controller.CustomerManagement;
import com.uni.project.model.dao.MemberLookup;
import com.uni.project.model.vo.Member;

public class Customer {
	
	private Scanner sc = new Scanner(System.in);
	private CustomerManagement cm = new CustomerManagement();
	private MemberLookup up =new MemberLookup();
	
	public void Customer(){}
	public void Main(){
		
		
		while(true) {
			System.out.println("========== cakey ==========");
			System.out.println("1.회원가입");
			System.out.println("2.정보수정");
			System.out.println("3.저장하기 ");
			System.out.println("4.취소하기");
			System.out.println("5.삭제하기");
			System.out.println("번호를 입력해주세요>>");
			System.out.println("6.회원조회");
			System.out.println("7.돌아가기");
			System.out.println("번호를 입력해주세요 : ");
			int num = sc.nextInt();
			
			switch(num) {
			case 1: cm.newCustomer(); break;
			case 2: cm.updateCunstomer();  break;
			case 3: cm.Store();break;
			case 4: cm.Cancle();break;
			case 5: cm.DeleteCustomer();break;
			
			case 6: up.Search(); break;
			
			case 7: return;
			default: System.out.println("다시 선택하세요");
				
			}
			
			
		}
		
		
	}
}

