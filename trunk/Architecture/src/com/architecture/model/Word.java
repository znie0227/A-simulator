package com.architecture.model;

import com.architecture.util.Config;

public class Word {

	private int[] data;

	public Word() {
		data = new int[Config.WORD_SIZE];
	}

	public Word(int[] data) {
		this.data = new int[Config.WORD_SIZE];
		this.setValue(data);
	}

	public int[] getData() {
		return data;
	}

	public void setValue(int[] data) {
		for (int i = 0; i < Config.WORD_SIZE; i++) {
			this.data[Config.WORD_SIZE - i - 1] = (i < data.length) ? (data[data.length
					- 1 - i])
					: 0;
		}
	}
}
