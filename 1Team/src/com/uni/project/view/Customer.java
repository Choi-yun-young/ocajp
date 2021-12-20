package com.uni.project.view;

import java.util.Scanner;

import com.uni.project.controller.MemberDAO;
import com.uni.project.model.dao.CustomerManagement;
import com.uni.project.model.vo.Member;

public class Customer {
	
	private Scanner sc = new Scanner(System.in);
	private com.uni.project.model.dao.
	CustomerManagement cm = new CustomerManagement();
	MemberDAO da = new MemberDAO();
	public void Customer(){}
	public void Main(){
		
		
		while(true) {
			System.out.println("========== cakey ==========");
			System.out.println("1.회원가입");
			System.out.println("2.정보수정");
			System.out.println("3.탈퇴하기");
			System.out.println("4.회원조회");
			System.out.println("5.다시선택하기");
			System.out.println("번호를 입력해주세요 : ");
			int num = sc.nextInt();
			
			switch(num) {
			case 1: cm.newCustomer(); break;
			
			case 2: cm.editMem();  break;
		
			case 3: cm.delMem();break;
			
			case 4: cm.showMemList(); break;
			
			case 5: break;
			default: System.out.println("다시 선택하세요");return;
				
			}
			
			
		}
		
		
	}

}  

