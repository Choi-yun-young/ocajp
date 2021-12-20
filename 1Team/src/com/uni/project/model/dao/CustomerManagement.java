package com.uni.project.model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.uni.project.controller.MemberDAO;
import com.uni.project.model.vo.Member;

public class CustomerManagement {
	
	 MemberDAO dao ;
 
	Scanner sc = new Scanner(System.in);
	public Map<String, Member> MemberMap = new TreeMap<String, Member>();
	//회원들의 정보를 저장하는 저장공간
	public CustomerManagement() {//초기값 설정
		
	        dao = new MemberDAO(); //데이터 접근 객체 생성.
	        
		}
	
	    
	        
	
	   
	
	public void newCustomer() {//회원가입
	
	       
        System.out.println("***********회원가입************");
       
        while(true){
            System.out.print("아이디*");
           

                String id=sc.nextLine();
           
            if(!(dao.containKey(id))){//중복이 아니면 브레이크
            	System.out.println("사용가능한 아이디 입니다!");
                break;
            }else{
                System.out.println("중복된 아이디입니다. 다시 입력해주세요!");
            }
           
        }      
        System.out.print("이름*");
        String name = sc.nextLine();
       
        System.out.print("아이디*");
         String id = sc.nextLine();
      
        System.out.print("비밀번호*");
        String pw = sc.nextLine();    
    

        System.out.print("비밀번호*");
        String pw1 = sc.nextLine(); 
        
        System.out.print("전화번호*");
        String phone = sc.nextLine(); 
        sc.nextLine();
        
        Member vo = new Member(id, name, pw,pw1, phone);
        dao.regMem(vo); //입력받은 데이터 추가
       
        System.out.println("회원등록이 정상적으로 완료되었습니다.");
       
    }
	
	 public void showMemList(){
	        dao.showMemList();
	    }
	   
	       
		  public void editMem(){
			    
			  
			    System.out.println("수정할 회원의 아이디를 입력해주세요:");
			    String id = sc.nextLine();
			   
			    if (dao.containKey(id)) {
			        System.out.println("[ "+id+ " ]님 의 정보====");
			        System.out.println(dao.get(id)); 
			        
			        System.out.println("수정해주세요");
			        System.out.println("이름*");
			        String name = sc.nextLine();
			        System.out.println("비밀번호*");
			        String pwd = sc.nextLine();
			        System.out.println("비밀번호 확인*");
			        String pwd1 = sc.nextLine();
			        System.out.println("전화번호*");
			        String phone = sc.nextLine(); 
			        dao.editMem(id, name, pwd, pwd1, phone); //연락처 수정.
			       
			        System.out.println("[ " + id + " ]님의 정상적으로 수정되었습니다.");
			    }else{
			        System.out.println("[ " + id + " ]는 존재하지 않는 아이디입니다.");
			    }
			}//editMem()----------------------



		
		public void Store() {
			
				
		System.out.println("저장하시겠습니까? (예  / 아니요)>>");
		
		String input = sc.nextLine();
		if(input.equals("예")) {
			System.out.println("저장되었습니다.");
			
		}
		

	}
	
	public void Cancle() {
		
	
		
		System.out.println("취소하시겠습니까? (예  / 아니요)>>");
		
		String input = sc.nextLine();
		if(input.equals("예")) {
			System.out.println("취소되었습니다.");
			
		}
		
	}
	
	/**
	* 회원 삭제
	*/
	public void delMem(){
	        
	    System.out.println("삭제할 회원의 아이디를 입력해주세요:");
	    String id = sc.nextLine();
	    if (dao.delMem(id)) {
	        System.out.println("[ " + id + " ]이 정상적으로 삭제되었습니다.");        
	    } else {
	        System.out.println("[ " + id + " ]는 존재하지 않는 아이디입니다.");           
	    }      
	}


	
    

}
				
