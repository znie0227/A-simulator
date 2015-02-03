package com.architecture.model;

import com.architecture.util.Config;


public class Memory {

	public static Memory instance;
	private Word[] data;
	
	public Memory(){
		this.data = new Word[Config.MEMORY_SIZE];
		for(int i=0;i<Config.MEMORY_SIZE;i++)
			data[i] = new Word();
	}

	public static Memory getInstance() {
		if (instance == null) {
			instance = new Memory();
		}
		return instance;
	}

}
