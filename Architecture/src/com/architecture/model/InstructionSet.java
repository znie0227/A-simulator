package com.architecture.model;

import java.util.HashMap;
import java.util.Map;

public class InstructionSet {

	
	public static Map<String, Integer> instructionMap= new HashMap<String, Integer>();
	
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
		instructionMap.put("HLT",0);
		instructionMap.put("TRAP",36);
		instructionMap.put("MLT",20);
		instructionMap.put("DVD",21);
		instructionMap.put("TRR",22);
		instructionMap.put("AND",23);
		instructionMap.put("ORR",24);
		instructionMap.put("NOT",25);
		instructionMap.put("SRC",31);
		instructionMap.put("RRC",32);
		instructionMap.put("FSUB",34);
		instructionMap.put("VADD",35);
		instructionMap.put("VSUB",36);
		instructionMap.put("CNVRT",37);
		instructionMap.put("LDFR",50);
		instructionMap.put("STFR",51);
		instructionMap.put("IN",61);
		instructionMap.put("OUT",62);
		instructionMap.put("CHK",63);
		instructionMap.put("FADD",33);
		instructionMap.put("JZ",10);
		instructionMap.put("JNE",11);
		instructionMap.put("JCC",12);
		instructionMap.put("JMA",13);
		instructionMap.put("JSR",14);
		instructionMap.put("RFS",15);
		instructionMap.put("SOB",16);
		instructionMap.put("JGE",17);
	}
}
