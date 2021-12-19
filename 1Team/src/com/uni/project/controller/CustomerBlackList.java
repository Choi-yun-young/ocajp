package com.uni.project.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

import com.uni.project.model.vo.Member;

public class CustomerBlackList {
	
	  private String FILE = "blacklist.txt";

	    public void manageBlackList(CustomerManagement cm, int mode) {
	        Scanner sc = new Scanner(System.in);
	        
	        
	        if (mode == 3) {
	            System.out.println("**블랙리스트 관리** (관리자용)");
	            System.out.println("1. 블랙리스트 조회");
	            System.out.println("3. 블랙리스트 삭제");
	        } else {
	        	
	            System.out.println("**블랙리스트 관리** (매장용)");
	            System.out.println("1. 블랙리스트 조회");
	        }
	        System.out.println("원하시는 메뉴를 선택해주세요. : ");
	        int menuNmber = sc.nextInt();
	        if (mode != 3 && menuNmber != 1) {
	            System.out.println("메뉴를 잘못 선택하셨습니다.");
	            return;
	        }
	        
	        
	        LinkedList<Member> blackList = readBlackList();
	        switch (menuNmber) {
	            case 1:
	                showBlackList(blackList);
	                break;
	            case 2:
	                registerBlackList(cm, blackList);
	                break;
	            case 3:
	                blackList = deleteBlackList(blackList);
	                break;
	            default:
	                System.out.println("메뉴를 잘못 선택하셨습니다.");
	        }
	    }


	    private LinkedList<Member> readBlackList() {
	        LinkedList<Member> blackList = new LinkedList<Member>();
	        try( BufferedReader reader =  new BufferedReader(new FileReader(FILE))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] splitedine = line.split(" ");
	                Member member = new Member(splitedine[0], splitedine[1], Integer.parseInt(splitedine[2]));
	                blackList.add(member);
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        return blackList;
	    }

	    private void showBlackList(LinkedList<Member> blackList) {//블랙리스트 조회
	        for (Member member : blackList) {
	            System.out.println("아이디 : " + member.getId() + ", 이름 : " + member.getName() + ", 핸드폰번호 : " + member.getPhoneNum());
	            continue;
	        }
	       
	    }

	    private void registerBlackList(CustomerManagement cm, LinkedList<Member> blackList) {//블랙리스트 등록
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("블랙리스트에 등록할 아이디를 입력하세요: ");
	        String id = scanner.nextLine();

	        for (Member member : blackList) {
	            if (member.getId().equals(id)) {
	                System.out.println("동일한 아이디가 존재합니다.");
	                return;
	            }
	        }

	        Member member = null;
	        for (Member each : cm.list) {
	            if (each.getId().equals(id)) {
	                member = each;
	                
	            }
	        }

	        if (member == null) {
	            System.out.println("회원가입 되지 않은 않은 아이디 입니다.");
	            return;
	        }

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
	            writer.write(member.getId() + " " + member.getName() + " " + member.getPhoneNum() + '\n');
	            System.out.println("성공적으로 등록 되었습니다.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private LinkedList<Member> deleteBlackList(LinkedList<Member> blackList) {//블랙리스트 삭제
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("블랙리스트에서 삭제할 아이디를 입력하세요: ");
	        String id = scanner.nextLine();

	        boolean isContains = false;
	        for (Member member : blackList) {
	            if (member.getId().equals(id)) {
	                isContains = true;
	            }
	        }

	        if (!isContains) {
	            System.out.println("해당하는 아이디가 없습니다.");
	        }

	        LinkedList<Member> newBlackList = new LinkedList<>();
	        for (Member member : blackList) {
	            if (!member.getId().equals(id)) {
	                newBlackList.add(member);
	            }
	        }

	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
	                for(Member member : newBlackList) {
	                    writer.write(member.getId() + " " + member.getName() + " " + member.getPhoneNum() + '\n');
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	        System.out.println("성공적으로 삭제 되었습니다.");


	        return newBlackList;
	    }
		
		

}
