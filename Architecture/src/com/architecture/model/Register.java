package com.architecture.model;

public class Register {

	protected String name;
	protected int[] data;
	protected int size;
	
	public Register(String name, int[] data, int size){
		this.name=name;
		this.data=data;
		this.size=size;
		
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
