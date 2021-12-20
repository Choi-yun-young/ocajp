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

public class OrderDao {
	ArrayList<OrderInformation> oList = new ArrayList<>();
	
	public void OrderDao(ArrayList oList) {
		

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Order.txt"))) {
			
			while (true) {
				
				oList.add((OrderInformation) ois.readObject());
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void inputOrder(OrderInformation order) {
		// 예약 객체를 oList에 추가
		oList.add(order);
		
		saveFile(oList);
		return; // 추가후 돌아가기
	}

	public ArrayList<OrderInformation> oList() {
		// 오더 리스트 전체리턴
		
		return oList;
	}

	public void deleteOrder(int num) {
		// 오더 취소
		for (int i = 0; i < oList.size(); i++) {
			if (oList.get(i).getNum() == num) {
				oList.remove(i);
			}
		}
	}


	// - 파일에 오더 리스트 저장
	public void saveFile(ArrayList<OrderInformation> oList) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Order.txt"))) {
			
			
				oos.writeObject(oList);
				
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			
		}
		return;
	}
}