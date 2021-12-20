package com.uni.project.run;

import com.uni.project.controller.CakeyShopMenuManager; 
import com.uni.project.view.Customer;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		//Customer c= new Customer();
		//c.Main();

		//CakeyMenu c = new CakeyMenu();
		//c.searchMenu();

		//Customer c= new Customer();
		//c.Main();

//		ShopManager c = new ShopManager();
//		c.cakeAllList();
		
//		SearchDao s = new SearchDao();
//		s.getCake();
		
		CakeyShopMenuManager c = new CakeyShopMenuManager();
		c.shopIncakeOrder();

	}

}

