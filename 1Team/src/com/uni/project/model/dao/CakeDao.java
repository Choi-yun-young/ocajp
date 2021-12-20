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

public class CakeDao {

	private ArrayList<Cake> ckList = new ArrayList<>();
	// 케이크 객체만 받는 어레이리스트

	public CakeDao() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Cake.txt"))) {

			while (true) {
				ckList.add((Cake) ois.readObject());
			}

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getLastCakeNo() {
		return ckList.get(ckList.size() - 1).getCakeNo(); // ckList의 해당 인덱스에 들어있는 케이크 번호 가져오기
	}

	public void inputCake(Cake cake) {
		// 케이크 객체를 ckList에 추가
		ckList.add(cake);
	}

	public ArrayList<Cake> ckList() {
		// 케이크 리스트 전체리턴
		return ckList;
	}

	public Cake ckOne(int num) {
		// 하나의 케이크 특정 // 등록한 케이크 삭제시에 사용
		Cake cake = null; // 케이크 받아줄거

		for (int i = 0; i < ckList.size(); i++) {
			if (ckList.get(i).getCakeNo() == num) {
				cake = ckList.get(i); // .get(index) : 해당인덱스 정보 불러오기
			}
		}

		return cake;
	}

	public void deleteCake(int num) {
		// 케이크 삭제
		for (int i = 0; i < ckList.size(); i++) {
			if (ckList.get(i).getCakeNo() == num) {
				ckList.remove(i);
			}
		}
		
		//케이크 삭제 후에는 아래의 케이크 번호셋팅 메소드를 호출한다.		
		setCakeNo();
	}

	public void setCakeNo() {
		//케이크 삭제 후 번호를 재설정하는 메소드
		
		// 0번 인덱스의 케이크 번호는 무조건 1번으로	
		ckList.get(0).setCakeNo(1);
		
		//변수 no를 만들고 1로 초기화
		int no = 1;
		
		//1번 인덱스부터는 위에서 1로 초기화한 no변수가 ++되면서 케이크의 번호가 셋팅
		for(int i = 1; i < ckList.size(); i++) {
			no++;
			ckList.get(i).setCakeNo(no);
		}
		
		
	}

	// - 파일에 케이크 리스트 저장
	public void saveFile() {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Cake.txt"))) {

			for (int i = 0; i < ckList.size(); i++) {
				oos.writeObject(ckList.get(i));
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
