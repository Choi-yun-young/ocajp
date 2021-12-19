package com.uni.project.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.uni.project.model.dao.CakeDao;
import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.Search;

public class SearchDao2 {
	
	private ArrayList<Cake> searchList = new ArrayList<>(); // 객체는 그대로 cake
	private ArrayList<Cake> copyCake = new ArrayList<>(); // 객체는 그대로 cake
	
	private CakeDao cd = new CakeDao();

	public SearchDao2() {

	      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("search.txt"))) {
	    	  	
	    	 while (true) {
	        	 searchList.add((Cake) ois.readObject());
	         }
	
	      } catch (EOFException e) {
	
	      } catch (FileNotFoundException e) {
	
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }

	}
	
	public void getCake() { // 케익을 서치리스트로 받아옴
		
		copyCake = cd.ckList(); // 원본케익리스트를 복사해 놓는다

		
	}
	
	public ArrayList<Cake> newSearch(String input) {
	
		
		for(int i = 0; i < copyCake.size(); i++) {
			
			if(copyCake.get(i).getName().contains(input)) { 
				
				searchList.add(copyCake.get(i)); // 케익을 찾고 검색리스트에 저장해주고
				
			}
		}
		saveFile();
		return searchList; 
		
	}


	public ArrayList<Cake> allsearch() { 
		   
	     return searchList;
	     
	}
	
	public void deletsearch() { // 검색리스트 지우기(다)
		
		for(int i = 0; i < searchList.size(); i++) {
			searchList.clear();
			
		}
		saveFile();
	}

	
	public void saveFile() {
	   
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("search.txt"))) {
    	  
         for (int i = 0; i < searchList.size(); i++) {
        	 
            oos.writeObject(searchList.get(i));
         }
      } catch (FileNotFoundException e) {
         System.out.println("검색내역이 없습니다");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


}