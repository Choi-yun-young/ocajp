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

import com.uni.project.model.vo.Member;

public class CustomerManagement {
	
	



	Scanner sc = new Scanner(System.in);
	private Map<String, Member> MemberMap = new TreeMap<String, Member>();
	//회원들의 정보를 저장하는 저장공간
	public CustomerManagement() {//초기값 설정
		 Member mem1 = new Member("공유", "자바킹", "1111", "1111",010-0000-7931);
	        MemberMap.put(mem1.getId(), mem1);   
	        Member mem2 = new Member("구름", "클라우드", "22222","22222 ",02-111-1234);
	        MemberMap.put(mem2.getId(), mem2); 
		
		}
	
	 public void regMem(Member vo){       
	        MemberMap.put(vo.getId(),vo);      
	    }
	   
	  public void regMem(String id, String name,String pwd,String pwd1,int phone){     
	        MemberMap.put(id, new Member(id, name, pwd, pwd1, phone));  
	        
	        
	    }
	  
	  public void showMemList(){
	        // tree map은 key값으로 정렬해준다. 하지만 value에 있는 이름으로 정렬을 하고 싶을때...
	        // value 값 정렬로 해서가져오기      
	        Set<Member> set = new TreeSet<Member>(MemberMap.values());     
	        Iterator<Member> itr =set.iterator() ;
	     
	        System.out.println("                             Member List");
	        System.out.println("======================================================================");
	        if(MemberMap.size()>0){        
	            System.out.println("아이디\t\t이름\t\t  비밀번호\t\t비밀번호 확인 \t\t 전화번호");
	            System.out.println("======================================================================");
	           
	            while (itr.hasNext()){
	                Member vo = itr.next();
	                System.out.println(vo);
	            }
	           
	        }else{
	            System.out.println("저장된 데이터가 없습니다. ");
	        }
	        System.out.println("==============================================================총 "+MemberMap.size()+" 명=\n");
	    }
	   
	
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
		
		//list.add(new Member(name,id,password,password1,phoneNum));
		
		System.out.println("***********회원가입을 축하합니다.*************");
		
		System.out.println();
		}
		
		
	
	
	public  boolean editMem (){//회원정보 수정
		
		
		String id= sc.nextLine();
		String name = sc.nextLine();
		String pwd = sc.nextLine();
		String pwd1 = sc.nextLine();
		int phone = sc.nextInt();
		sc.nextLine();
		boolean chk = false;
		
		  if (MemberMap.containsKey(id)) {
	            Member vo = MemberMap.get(id); //해당 아이디의 정보가 들어있는 객체 가져오기
	            vo.setName(name); //이름 수정. 
	            vo.setPassword(pwd);
	            vo.setPassword1(pwd1);
	            vo.setPhoneNum(phone);
	            chk = true;
	        }
		  return chk;
	}
	
	public void saveMemList() {
	
	 File file = new File("C:\\Users\\LG\\Desktop\\Member DB.txt") ;
		
	 
		try {
			FileWriter filewriter = new FileWriter(file,true);
			if(file.isFile()&&file.canWrite()) {
				System.out.println("*********회원가입***********");
				System.out.println("이름*");
				filewriter.append(sc.nextLine());
				filewriter.append("\t");
				System.out.println("아이디*");
				filewriter.append(sc.nextLine());
				filewriter.append("\t");
				System.out.println("비밀번호*");
				filewriter.append(sc.nextLine());
				filewriter.append("\t");
				System.out.println("비밀번호 확인*");
				filewriter.append(sc.nextLine());
				System.out.println("전화번호*");
				
				filewriter.append((char) sc.nextInt());
				
				filewriter.append("\r");
				System.out.println("***********회원가입을 축하합니다.*************");
			}
			
		filewriter.close();
		
		  } catch (IOException e) {
			  e.printStackTrace();
			  
		  } 
		
		}
		
		
	
		
		public void Store() {
			
			LinkedList<Member>list = new LinkedList<Member>();		
		System.out.println("저장하시겠습니까? (예  / 아니요)>>");
		
		String input = sc.nextLine();
		if(input.equals("예")) {
			System.out.println("저장되었습니다.");
			
		}
		

	}
	
	public void Cancle() {
		
		LinkedList<Member>list = new LinkedList<Member>();
		
		
		
		
		System.out.println("취소하시겠습니까? (예  / 아니요)>>");
		
		String input = sc.nextLine();
		if(input.equals("예")) {
			System.out.println("취소되었습니다.");
			
		}
		
	}
	
    public boolean delMem() {
		
    	String id = sc.nextLine();
    	  boolean chk = false;
          if (MemberMap.containsKey(id)) {
              Member del = MemberMap.remove(id); //key를 입력받아서 키에 해당하는 데이터 삭제
              chk = true;
          }
          return chk;
      }

    
public void  Search(){
		
    	System.out.println("**********회원조회*********");
    		
    	
    	
			 showMemList();


}
}
				
