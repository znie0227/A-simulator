package com.architecture.model;

import com.architecture.util.Config;
import com.architecture.util.Utils;

public class Word {

	public int[] data;

	public Word() {
		data = new int[Config.WORD_SIZE];
	}

	public Word(Word w) {
		data = w.getData().clone();
	}

	public Word(int[] data) {
		this.data = new int[Config.WORD_SIZE];
		this.setValue(data);
	}

	public int[] getData() {
		return data;
	}
	
	public Word(float value) {
		data = new int[Config.WORD_SIZE];
		this.setValue(value);
	}
	
	public void setValue(float value){
		this.setValue(Utils.get16bitFromFloatValue(value));
	}

	public String getDataInString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < Config.WORD_SIZE; i++) {
			sb.append(data[i]);
		}
		return sb.toString();
	}

	public int getDataInDec() {
		return Utils.getDecimalFromBin(getDataInString());
	}

	public void setValue(int[] data) {
		for (int i = 0; i < Config.WORD_SIZE; i++) {
			this.data[Config.WORD_SIZE - i - 1] = (i < data.length) ? (data[data.length
					- 1 - i])
					: 0;
		}
	}

	/**
	 * copy the binary values in word into current data
	 * 
	 * @param word
	 */
	public void setValue(Word word) {
		int n[] = word.getData();
		for (int i = 0; i < Config.WORD_SIZE; i++) {
			data[i] = n[i];
		}
	}

	public Word clone() {
		return new Word(this);
	}
}
