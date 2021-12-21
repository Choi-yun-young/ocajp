package com.uni.project.controller;

import java.util.Comparator;

import com.uni.project.model.vo.Search;

public class DescSearchList implements Comparator<Search> {
  
	@Override
	public int compare(Search o1, Search o2) {
		// TODO Auto-generated method stub
		return -(o1.getCakeName().compareTo(o2.getCakeName()));
	}

}
 