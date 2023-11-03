package com.api.rest.RestService.utils;

import java.util.List;

public class DataContainer<T> {
	
	private List<T> data;
	
	public DataContainer(List<T> data) {
		this.data = data;
	}
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
}
