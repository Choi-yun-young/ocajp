package com.uni.project.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.uni.project.model.vo.Notice;

public class NoticeDao {
	
	private ArrayList<Notice> nList = new ArrayList<Notice>();
	
	public NoticeDao() {

		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Notice_list.txt"))) {
			
			while (true) { 
				nList.add((Notice)ois.readObject());
			}
			
		} catch (EOFException e) {
			
		} catch (FileNotFoundException e) { 
			System.out.println("등록된 공지사항이 없습니다");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public void newNotice(Notice notice) {
		
		int index = 0;
		
		try {
			index = (nList.get(nList.size()-1)).getIndex() + 1;
		} catch (IndexOutOfBoundsException e) {
			
			index = 1;
		} finally {
			
			notice.setIndex(index);
			nList.add(notice);
		}
	
	}
	
	public ArrayList<Notice> allNotice() { // 모든공지사항 받음
		
		return nList;
	}
	

	public Notice selectNotice(int index) {
		
		for(int i = 0; i < nList.size(); i++) {
			if(index == nList.get(i).getIndex()) {
				return nList.get(i);
				// 해당인덱스 공지 던져줌
			}
		}
		return null;
		// 없는인덱스일때 null 줌
	}
	
	
	public void deletNotice(int index) {
		
		for(int i = 0; i < nList.size(); i++) {
			if(index == nList.get(i).getIndex()) {
				nList.remove(i);
				// 해당인덱스 공지삭제
			
			}
		
		}
		
	}
	
	public void modifyNoticeTitle(int index, String title) {
		
		for(int i = 0; i < nList.size(); i++) {
			if(index == nList.get(i).getIndex()) {
			
				nList.get(i).setTitle(title);
					// 해당인덱스 공지제목 변경
				
			}
		}
	}
	
	public void modifyNoticeContent(int index, String content) {
		
		for(int i = 0; i < nList.size(); i++) {
			if(index == nList.get(i).getIndex()) {
				
				nList.get(i).setContents(content);
					// 해당인덱스 공지내용 변경
				
			}
		}
	}
	
	public ArrayList<Notice> storeNotice(String input) { // 매장,고객
		
		ArrayList<Notice> storeList = new ArrayList<Notice>();
		
		for(int i = 0; i < nList.size(); i++) {

			if(nList.get(i).getCategory().equals(input)) {
				storeList.add(nList.get(i));
			
			}
			
		}
		return storeList;
	}

	public ArrayList<Notice> customerNotice(String input) {
		
		ArrayList<Notice> customerList = new ArrayList<Notice>();
		
		for(int i = 0; i < nList.size(); i++) {

			if(nList.get(i).getCategory().equals(input)) {
				customerList.add(nList.get(i));
			
			}
			
		}
		return customerList;
	}
	

	public void saveNoiceFile() {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Notice_list.txt"))) {
			
			for(int i = 0; i < nList.size(); i++) {
				oos.writeObject(nList.get(i));
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
