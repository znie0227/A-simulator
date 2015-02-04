package com.architecture.model;

import java.util.HashMap;
import java.util.Map;

public class InstructionSet {

	
	public static Map instructionMap= new HashMap<String, Integer>();
	
	static {
		instructionMap.put("LDR",1);
		instructionMap.put("STR",2);
		instructionMap.put("LDA",3);
		instructionMap.put("LDX",41);
		instructionMap.put("STX",42);
		instructionMap.put("AMR",4);
		instructionMap.put("SMR",5);
		instructionMap.put("AIR",6);
		instructionMap.put("SIR",7);
	}
}
