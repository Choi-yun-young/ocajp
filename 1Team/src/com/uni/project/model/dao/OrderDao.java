package com.uni.project.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.uni.project.controller.CakeyShopMenuManager;
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
	}

	public ArrayList<OrderInformation> oList() {
		// 오더 리스트 전체리턴
		
		return oList;
	}

	public OrderInformation orderOne(int num) {// 등록한 예약 삭제시 사용
		
		
		OrderInformation order = null; // 오더 받아줄거
		
		//for문 이용 - 하나의 오더를 뽑아오기 위해
		for (int i = 0; i < oList.size(); i++) {
			if (oList.get(i).getNum() == num) { // if문 이용 - 오더리스트의 넘버와 받은값이 같으면
				order = oList.get(i); //해당 인덱스 정보 불러오기
			}
		}

		return order;
	}

	public void deleteOrder(int num) {
		// 오더 취소
		for (int i = 0; i < oList.size(); i++) {
			if (oList.get(i).getNum() == num) {
				oList.remove(i);
			}
		}

		setOrderNo();
	}

	public void setOrderNo() {

		// 삭제하고 오더 번호 앞으로 당겨
		// 당겨야 되는데... 삭제한 오더 번호보다 뒷번호인 애들만 당겨야지
		// 수정 필요
		for (int i = 0; i < oList.size(); i++) {
			int no = oList.get(i).getNum();
			no--; // 이걸 다시 세팅해줘야
			oList.get(i).setNum(no);
			if (no == 0) {
				no++;
			oList.get(i).setNum(no);
			}
		}
	}

	// - 파일에 오더 리스트 저장
	public void saveFile(ArrayList<OrderInformation> oList) {
		CakeyShopMenuManager cm = new CakeyShopMenuManager();
		
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Order.txt"))) {

				System.out.println("생성"+oList);
				oos.writeObject(oList);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
		}
	}
}