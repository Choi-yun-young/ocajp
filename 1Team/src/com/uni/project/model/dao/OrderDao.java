package com.uni.project.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.uni.project.model.vo.Cake;
import com.uni.project.model.vo.OrderInformation;
import com.uni.project.model.vo.Search;

public class OrderDao {
	 
	 ArrayList<OrderInformation> oList = new ArrayList<>();
	 ArrayList<OrderInformation> copyList = new ArrayList<>();
	 private ArrayList<Cake> ckList = new ArrayList<>();
	 private String getId = null;
	
	 private CakeDao cd = new CakeDao(); 
	 
	public OrderDao() {

//		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Order.txt"))) {
//			
//			while (true) {
//				 
//				oList.add((OrderInformation) ois.readObject());
//			}
//
//		} catch (EOFException e) {
//
//		} catch (FileNotFoundException e) {
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			
//		}
	}

	
	
	public void getCake() { // 케익정보가있는걸 갖게됨 
		
		ckList = cd.ckList();
		String name = "";
		int price = 0;
		int no = 0;
		
		copyList = new ArrayList<>(ckList.size());
		
		for(int i = 0; i < ckList.size(); i++) {
			
			no = ckList.get(i).getCakeNo();
			name = ckList.get(i).getName();
			price = ckList.get(i).getPrice();
			
			copyList.add(new OrderInformation(no,name, price));
		
		}
		
	}
	
	public OrderInformation selectCake(int no) { // 오더에 케익 이름 가격담음
		
		for (int i = 0; i < copyList.size(); i++) {
			if (copyList.get(i).getNo() == no) {
				
				return copyList.get(i);
			}
		}
		return null;
	}
	
	public void insertOreder(OrderInformation order) { //오더 저장
			
		oList.add(order);
	}
	
	public OrderInformation displayOrder(String id) {
		
	
		for(int i = 0; i < oList.size(); i++) {
			
			if(oList.get(i).getGetId().equals(id)) {
				
				return oList.get(i);
			}
		}
		
		return null;
		
	}
	
	public void getId(String id) {
		
		getId = id;
		
	}
	

	public ArrayList<OrderInformation> oList() {
		// 오더 리스트 전체리턴
		
		return oList;
	}

	public void deleteOrder(int num) {
		// 오더 취소
		for (int i = 0; i < oList.size(); i++) {
			if (oList.get(i).getNo() == num) {
				oList.remove(i);
			}
		}
	}


	// - 파일에 오더 리스트 저장
	public void saveFile() {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Order.txt"))) {
			
			oos.writeObject(oList);
		
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			
		}
		return;
	}
}