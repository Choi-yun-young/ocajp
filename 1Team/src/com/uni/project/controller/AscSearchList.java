package com.uni.project.controller;

import java.util.Comparator;

import com.uni.project.model.vo.Search;

public class AscSearchList implements Comparator<Search> {

	@Override 
	public int compare(Search o1, Search o2) {
		
		return o1.getCakeName().compareTo(o2.getCakeName());
	}

}
