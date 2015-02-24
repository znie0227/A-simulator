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
		return data.clone();
	}

	public String getDataInString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(data[i]);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return data in decimal-format
	 */
	public int getDecData() {
		return Utils.getDecimalFromBin(getData());

	}
	
	public int getDataAtPosition(int position) {
		return data[position];
	}

	public void setData(int[] data) {
		for (int i = 0; i < size; i++) {
			if (i < data.length)
				this.data[size - i - 1] = data[data.length - 1 - i];
			else
				this.data[size - i - 1] = 0;
		}
	}

	public void setDataByString(String val) {
		for (int i = 0; i < size; i++) {
			if (i < val.length())
				this.data[size - i - 1] = Integer.valueOf(val.charAt(val
						.length() - 1 - i));
			else
				this.data[size - i - 1] = 0;
		}
	}

	/**
	 * For register CC
	 * 
	 * @param val
	 */
	public void setDataByBitPosition(int val, int position) {
		data[position] = val;
	}

	/**
	 * For normal value(16 bits)
	 * 
	 * @param val
	 */
	public void setDataByDec(int val) {
		for (int i = 0; i < size; i++) {
			data[size - i - 1] = val % 2;
			val = val / 2;
		}
	}

	/**
	 * For Big value(32 bits)
	 * 
	 * @param val
	 */
	public void setDataByLongDec(long val) {
		for (int i = 0; i < size; i++) {
			data[size - i - 1] = (int) (val % 2);
			val = val / 2;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
