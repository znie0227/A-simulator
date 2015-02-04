package com.architecture.model;

import com.architecture.util.Utils;

public class Register {

	protected String name;
	protected int[] data;
	protected int size;

	public Register(String name, int[] data, int size) {
		this.name = name;
		this.data = data;
		this.size = size;

	}

	public int[] getData() {
		return data;
	}

	public String getDataInString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(data[i]);
		}
		return sb.toString();
	}
	
	
	/**
	  
	 * @return data in decimal-format
	 */
	public int getDecData(){
		return Integer.valueOf(Utils.getDecimalFromBin(getData()));
		
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