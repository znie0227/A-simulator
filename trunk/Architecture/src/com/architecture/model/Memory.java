package com.architecture.model;

import com.architecture.util.Config;

public class Memory {

	public static Memory instance;
	private Word[] data;

	public Memory() {
		this.data = new Word[Config.MEMORY_SIZE];
		for (int i = 0; i < Config.MEMORY_SIZE; i++)
			data[i] = new Word();
	}

	/**
	 * Return a static instance of Memory
	 * 
	 * @return instance
	 */
	public static Memory getInstance() {
		if (instance == null) {
			instance = new Memory();
		}
		return instance;
	}

	public Word read(int index) {

		if (index < 0 || index > Config.MEMORY_SIZE) {
			// TODO out of Bound throw new IllegalMemoryAddressException(index);
		}
		return this.data[index].clone();
	}

	/**
	 * Write Memory. Transform data into Word before writing
	 * 
	 * @param index
	 * @param data
	 * 
	 */
	public void write(int index, int[] data) {
		if (index < 0 || index > Config.MEMORY_SIZE) {
			// TODO throw new IllegalMemoryAddressException(index);
		}
		this.data[index].setValue(data);
	}

	/**
	 * Write Memory
	 * 
	 * @param index
	 * @param word
	 * 
	 */
	public void write(int index, Word word) {
		if (index < 0 || index > Config.MEMORY_SIZE) {
			// TODO throw new IllegalMemoryAddressException(index);
		}
		this.data[index].setValue(word);

	}

}
