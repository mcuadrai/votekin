package com.forum.model;

import java.util.List;

import com.forum.domain.Opinion;

public class OpinionResponseModel {
	List<Opinion> list;
	OpinionFilterModel filter;
	public List<Opinion> getList() {
		return list;
	}
	public OpinionFilterModel getFilter() {
		return filter;
	}
	public void setList(List<Opinion> list) {
		this.list = list;
	}
	public void setFilter(OpinionFilterModel filter) {
		this.filter = filter;
	}
	
	
}
