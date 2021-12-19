package com.uni.project.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.uni.project.model.vo.OrderInformation;

public class OrderDao {
	ArrayList<OrderInformation> oList = new ArrayList<>();
	
	public void writeOrder(OrderInformation order) {
		// TODO Auto-generated method stub
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Order_list.txt"))){
			oList.addAll((ArrayList<OrderInformation>)ois.readObject());
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int getLastCakeNo() { // 마지막 번호 받아오기
		// TODO Auto-generated method stub
		return oList.get(oList.size()-1).getNum();
	}
}
