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

public class SearchDao {
	
	private ArrayList<Search> searchList = new ArrayList<>();
	private ArrayList<Search> copyCakeList = new ArrayList<>();
	private ArrayList<Cake> ckList = new ArrayList<>();
	
	private CakeDao cd = new CakeDao();

	public SearchDao() {

	      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("search.txt"))) {
	    	  	
	    	 while (true) {
	        	 searchList.add((Search) ois.readObject());
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
		
		ckList = cd.ckList();
		String name = "";
		int price = 0;
		String Ingred = "";
		
		copyCakeList = new ArrayList<>(ckList.size());
		
		for(int i = 0; i < ckList.size(); i++) {
			
			name = ckList.get(i).getName();
			price = ckList.get(i).getPrice();
			Ingred = ckList.get(i).getIngred();
			
			copyCakeList.add(new Search(name, price, Ingred));
		
		}
		
	}
	
	public Search newSearch(String input) {
	
		
		for(int i = 0; i < copyCakeList.size(); i++) {
			
			if(copyCakeList.get(i).getCakeName().contains(input)) { 
				
				searchList.add(copyCakeList.get(i)); // 케익을 찾고 검색리스트에 저장해주고
				return copyCakeList.get(i);
			}
			
		}
		saveFile();
		return null; // 검색된거 없음
		
		
	}


	public ArrayList<Search> allsearch() { 
		   
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
