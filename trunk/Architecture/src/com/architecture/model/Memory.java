package com.architecture.model;

import com.architecture.util.Config;

import edu.gwu.core.Setting;
import edu.gwu.core.basic.Word;
import edu.gwu.exception.IllegalMemoryAddressException;

public class Memory {

	public static Memory instance;
	private Word[] data;

	public Memory() {
		this.data = new Word[Config.MEMORY_SIZE];
		for (int i = 0; i < Config.MEMORY_SIZE; i++)
			data[i] = new Word();
	}

	public static Memory getInstance() {
		if (instance == null) {
			instance = new Memory();
		}
		return instance;
	}

	public Word read(int index) {
		if (index < 0 || index > Config.MEMORY_SIZE)
			// TODO out of Bound throw new IllegalMemoryAddressException(index);
			return this.data[index].clone();
	}

}
