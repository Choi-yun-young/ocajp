package com.uni.project.run;

import com.uni.project.controller.CakeyManager;
import com.uni.project.controller.CustomerManagement;
import com.uni.project.controller.ShopManager;
import com.uni.project.model.dao.SearchDao;
import com.uni.project.view.CakeyMenu;
import com.uni.project.view.Customer;

public class Run {

	public static void main(String[] args) {

		CakeyMenu c = new CakeyMenu();
		c.searchMenu();
		
		//Customer c= new Customer();
		//c.Main();
		
//		ShopManager c = new ShopManager();
//		c.cakeAllList();
		
//		SearchDao s = new SearchDao();
//		s.getCake();
	}

}
