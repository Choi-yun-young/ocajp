package com.uni.project.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


import com.uni.project.model.vo.Member;
import com.uni.project.model.vo.Search;

public class MemberDAO {

	 private Map<String, Member> MemberMap = new HashMap<String, Member>();
	    //회원들의 정보를 저장하는 저장공간.
	            
	    //기본생성자
	    public MemberDAO() {
//	        초기값 설정.
	    	Member mem1 = new Member("공유", "자바킹", "123456", "123456", "010-0000-7931");
	        MemberMap.put(mem1.getId(), mem1);   
	        Member mem2 = new Member("구름", "구름이", "123456", "123456", "010-0002-7991");
	        MemberMap.put(mem2.getId(), mem2);  
	        
	        
	        
	        
	        
	        Member mem3 = new Member("시스템", "시스템", "시스템", "시스템", "010-0000-0000");
	        MemberMap.put(mem3.getId(), mem3);  
	        Member mem4 = new Member("매장", "매장", "매장", "매장", "010-0000-0000");
	        MemberMap.put(mem4.getId(), mem4);  
	    }
	   
	 //회원등록
	    public void regMem(Member vo){       
	    	
	        MemberMap.put(vo.getId(),vo);    
	        
	      
	       
	    }
	   
//	    public void regMem(String id, String name,String pwd,String pwd1,String phone){     
//	        MemberMap.put(id, new Member(id, name, pwd, pwd1, phone));      
//	    }
	   
	   
	    //키중복 체크
	     
	    public boolean containKey(String key){     
	        return MemberMap.containsKey(key);     
	    }
	   
	    /**
	     * 키값에 해당하는 value값 가져오기
	     */
	    public Member get(String key){
	       
	        return MemberMap.get(key);
	    }
	   
	   
	    public boolean editMem(String name,String id,String pwd,String pwd1,String phone){
	       
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

	       
	   //회원삭제
	    public boolean delMem(String id){  
	       
	        boolean chk = false;
	        if (MemberMap.containsKey(id)) {
	            Member del = MemberMap.remove(id); //key를 입력받아서 키에 해당하는 데이터 삭제
	            chk = true;
	        }
	        return chk;
	    }
	   
	   
	   
	   
	    public Map<String, Member> getMemberMap() {
	        return MemberMap;
	    }
	 
	    public void setMemberMap(Map<String, Member> memberMap) {
	        MemberMap = memberMap;
	    }
	   
	    public void  Search(){
			
	    	System.out.println("**********회원조회*********");
	    	
	    	showMemList();

	}

		public void showMemList() {
			 // tree map은 key값으로 정렬해준다. 
	        // value 값 정렬로 해서가져오기      
	        Set<Member> set = new TreeSet<Member>(MemberMap.values());     
	        Iterator<Member> itr =set.iterator() ;
	     
	        System.out.println("                             Member List");
	        System.out.println("=================================================================================");
	        if(MemberMap.size()>0){        
	            System.out.println("아이디\t\t이름\t\t연락처");
	            System.out.println("==============================================================================");
	           
	            while (itr.hasNext()){
	                Member vo = itr.next();
	                System.out.println(vo);
	            }
	           
	        }else{
	            System.out.println("저장된 데이터가 없습니다. ");
	        }
	        System.out.println("==============================================================총 "+MemberMap.size()+" 명=\n");
	    }
	  

	        
		
	public void saveFile() {
			   
		Map<String, Member> memberMap = getMemberMap();

	      try (BufferedWriter bf = new BufferedWriter( new FileWriter("Member.txt"))) {
	    	 
	    	  	
	    	  for (Entry<String, Member> entry :  memberMap.entrySet()) {
	  
	                // put key and value separated by a colon
	                bf.write(entry.getKey() + ":"+ entry.getValue());

	            }
	  
	            bf.flush();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        } 
	      	catch (Exception e) {
	      	}
	
	   }
}
