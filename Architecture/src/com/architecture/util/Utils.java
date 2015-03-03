package com.architecture.util;

import com.architecture.app.Application;
import com.architecture.model.InstructionSet;
import com.architecture.model.Log;
import com.architecture.model.Memory;

/**
 * @author BaoBao_iOZ
 *
 * A general methods collection.
 */
/**
 * @author BaoBao_iOZ
 *
 */
public class Utils {

	public static int[] getBinaryFromDec(int val, int size) {
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[size - 1 - i] = val % 2;
			val = val / 2;
		}
		return data;
	}

	public static String getBinaryFromDecInString(int val, int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(val % 2);
			val = val / 2;
		}
		return sb.reverse().toString();
	}

	public static int getDecimalFromBin(int[] val) {
		int result = 0;
		for (int i = 0; i < val.length; i++) {
			result = result * 2 + val[i];
		}
		return result;
	}

	public static int getDecimalFromBin(String val) {
		int result = 0;
		for (int i = 0; i < val.length(); i++) {
			result = result * 2 + (val.charAt(i) - 48);
		}
		return result;
	}

	public static String getDecimalFromBinInString(int[] val) {

		return String.valueOf(getDecimalFromBin(val));
	}

	public static String getDecimalFromBinInString(String val) {
		return String.valueOf(getDecimalFromBin(val));
	}

	/**
	 * 
	 * Compute the Effective Address using a given instruction
	 * 
	 * @param instruction
	 * @return effective address
	 */
	public static String getEffectiveAddressInBin(String instruction) {

		// TODO 还没测试...

		if (instruction.charAt(10) == '0') {
			// No indirect addressing
			if (instruction.substring(8, 10).equals("00")) {
				return instruction.substring(11);
			}
			// just indexing
			else {

				String regName = instruction.substring(8, 10);
				regName = "X" + getDecimalFromBinInString(regName);
				String regData = Application.getRegisterByName(regName)
						.getDataInString();
				return getBinaryFromDecInString(getDecimalFromBin(regData)
						+ getDecimalFromBin(instruction.substring(11)), 11);// TODO
																			// 11
																			// is
																			// questionable...
			}
		} else {
			// indirect addressing, but No indexing
			if (instruction.substring(8, 10).equals("00")) {
				return Memory.getInstance()
						.read(getDecimalFromBin(instruction.substring(11)))
						.getDataInString();
			} else {
				// both indirect addressing and indexing

				String regName = instruction.substring(8, 10);
				regName = "X" + getDecimalFromBinInString(regName);
				String regData = Application.getRegisterByName(regName)
						.getDataInString();
				int contentOfIR = getDecimalFromBin(regData);
				int address = getDecimalFromBin(instruction.substring(11));
				return Memory.getInstance().read(contentOfIR + address)
						.getDataInString();

			}
		}
	}

	public static int getEffectiveAddressInDec(String instruction) {
		return getDecimalFromBin(getEffectiveAddressInBin(instruction));
	}

	public static String getStringFromIntArray(int[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
		}
		return sb.toString();
	}
	
	public static int[] getIntArrayFromString(String val) {
		int[] intArray = new int[val.length()];
		for (int i = 0; i < val.length(); i++) {
			intArray[i] = val.charAt(i) - 48;
		}
		return intArray;
	}

	/**
	 * Perform add and sub actions
	 * 
	 * @param opcode
	 * @param regName
	 * @param regName1
	 */
	public static void adder(String opcode, String regName, int... immed) {
		int t1, t2;
		switch (getDecimalFromBin(opcode)) {
		case 4:
			// MDR <- M(MAR)
			Application.getRegisterByName("MDR").setData(
					Memory.getInstance()
							.read(Application.getRegisterByName("MAR")
									.getDecData()).getData());
			Log.d("MDR <- M(MAR)");
			t1 = Application.getRegisterByName(regName).getDecData();
			Log.d("Operand 1 into T1");
			t2 = Application.getRegisterByName("MDR").getDecData();
			Log.d("Operand 2 into T2");
			if (t1 + t2 > 65535) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 0);
			}
			Application.getRegisterByName("ARR").setDataByDec(t1 + t2);
			Log.d("ARR <- ADDER");

			// TODO test overflow

			break;
		case 5:
			// MDR <- M(MAR)
			Application.getRegisterByName("MDR").setData(
					Memory.getInstance()
							.read(Application.getRegisterByName("MAR")
									.getDecData()).getData());
			Log.d("MDR <- M(MAR)");
			t1 = Application.getRegisterByName(regName).getDecData();
			Log.d("Operand 1 into T1");
			t2 = Application.getRegisterByName("MDR").getDecData();
			Log.d("Operand 2 into T2");
			Application.getRegisterByName("ARR").setDataByDec(t1 - t2);
			if (t1 - t2 < 0) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 1);
			}
			Log.d("ARR <- ADDER");

			// TODO test underflow
			break;
		case 6:
			t1 = Application.getRegisterByName(regName).getDecData();
			Log.d("Operand 1 into T1");
			t2 = immed[0];
			Log.d("Operand 2 into T2");
			Application.getRegisterByName("ARR").setDataByDec(t1 + t2);
			if (t1 + t2 > 65535) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 0);
			}
			Log.d("ARR <- ADDER");

			break;
		case 7:
			t1 = Application.getRegisterByName(regName).getDecData();
			Log.d("Operand 1 into T1");
			t2 = immed[0];
			Log.d("Operand 2 into T2");
			Application.getRegisterByName("ARR").setDataByDec(t1 - t2);
			if (t1 - t2 < 0) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 1);
			}
			Log.d("ARR <- ADDER");
			break;
		}

	}

	/**
	 * Perform multi and div actions
	 * 
	 * @param opcode
	 * @param regName
	 * @param regName1
	 */
	public static void mult(String opcode, String regName, String regName1) {
		long t1, t2;
		switch (getDecimalFromBin(opcode)) {
		case 20:
			// MLT
			// MDR <- c(ry)
			Application.getRegisterByName("MDR").setData(
					Application.getRegisterByName(regName1).getData());
			Log.d("MDR <- c(ry)");
			t1 = Application.getRegisterByName(regName).getDecData();
			Log.d("Operand 1 into T1");
			t2 = Application.getRegisterByName("MDR").getDecData();
			Log.d("Operand 2 into T2");
			if (t1 * t2 > 4294967296l) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 0);
			}
			Application.getRegisterByName("MRR").setDataByLongDec(t1 * t2);
			Log.d("MRR <- MULT:"+t1*t2);

			// TODO test overflow

			break;
		case 21:
			// DVD
			// MDR <- c(ry)
			Application.getRegisterByName("MDR").setData(
					Application.getRegisterByName(regName1).getData());
			Log.d("MDR <- c(ry)");
			t1 = Application.getRegisterByName(regName).getDecData();
			Log.d("Operand 1 into T1");
			t2 = Application.getRegisterByName("MDR").getDecData();
			Log.d("Operand 2 into T2");
			if (t2 == 0) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 2);
				return;
			}
			int quotient,
			remainder;
			quotient = (int) (t1 / t2);
			remainder = (int) (t1 % t2);
//			String result = get(quotient) + "" + remainder;
			String result=getStringFromIntArray(getBinaryFromDec(quotient, 16))+getStringFromIntArray(getBinaryFromDec(remainder, 16));
			System.out.println(result);
			Application.getRegisterByName("MRR").setDataByString(result);
			Log.d("MRR <- MULT");

			// TODO test underflow
			break;

		}

	}

	/**
	 * Perform logic actions
	 * 
	 * @param opcode
	 * @param regName
	 * @param regName1
	 */
	public static void logic(String opcode, String regName, String... regName1) {
		int[] t1, t2;
		switch (getDecimalFromBin(opcode)) {
		case 23:
			// AND
			t1 = Application.getRegisterByName(regName).getData();
			Log.d("Operand 1 into T1");
			t2 = Application.getRegisterByName(regName1[0]).getData();
			Log.d("Operand 2 into T2");
			for (int i = 0; i < t1.length; i++) {
				if (t1[i] == 1 && t2[i] == 1) {
					t1[i] = 1;
				} else {
					t1[i] = 0;
				}
			}
			Application.getRegisterByName("LRR").setData(t1);
			Log.d("LRR <- Logic");
			break;
		case 24:
			// OR
			t1 = Application.getRegisterByName(regName).getData();
			Log.d("Operand 1 into T1");
			t2 = Application.getRegisterByName(regName1[0]).getData();
			Log.d("Operand 2 into T2");
			for (int i = 0; i < t1.length; i++) {
				if (t1[i] == 0 && t2[i] == 0) {
					t1[i] = 0;
				} else {
					t1[i] = 1;
				}
			}
			Application.getRegisterByName("LRR").setData(t1);
			Log.d("LRR <- Logic");
			break;
		case 25:
			// NOT
			t1 = Application.getRegisterByName(regName).getData();
			Log.d("Operand 1 into T1");
			for (int i = 0; i < t1.length; i++) {
				t1[i] = 1 - t1[i];
			}
			Application.getRegisterByName("LRR").setData(t1);
			Log.d("LRR <- Logic");

			break;
		}

	}

	/**
	 * Perform Shifting and rotating actions
	 * 
	 * @param opcode
	 * @param regName
	 * @param regName1
	 */
	public static void shifter(String opcode, String regName,
			int arithOrLogic, int leftOrRight, int count) {
		int[] t1;
		switch (getDecimalFromBin(opcode)) {
		case 31:
			// SRC
			t1 = Application.getRegisterByName(regName).getData();
			Log.d("Operand 1 into T1");
			shiftAndRotate(1, t1, arithOrLogic, leftOrRight, count);
			Application.getRegisterByName("SRR").setData(t1);
			Log.d("SRR <- Shifter");
			break;
		case 32:
			// RRC
			t1 = Application.getRegisterByName(regName).getData();
			Log.d("Operand 1 into T1");
			shiftAndRotate(0, t1, arithOrLogic, leftOrRight, count);
			
			Application.getRegisterByName("SRR").setData(t1);
			Log.d("SRR <- Shifter");
			break;
		}

	}

	/**
	 * @param shift_rotate
	 *            1:shift 0:rotate
	 * @param array
	 * @param a_l
	 *            l:1 a:0
	 * @param l_r
	 *            l:1 r:0
	 * @param count
	 * @return
	 */
	public static int[] shiftAndRotate(int shift_rotate, int[] array, int a_l,
			int l_r, int count) {
		int start = 1 - a_l;
		for (int i = 0; i < count; i++) {
			int temp;
			if (l_r == 1) {
				// left
				temp = array[start];
				for (int j = start; j < array.length - 1; j++) {
					array[j] = array[j + 1];
				}
				if (shift_rotate == 0) {
					array[array.length - 1] = temp;
				} else {
					array[array.length - 1] = 0;
				}
			} else {
				// right
				temp = array[array.length - 1];
				for (int j = array.length - 1; j > start; j--) {
					array[j] = array[j - 1];
				}
				if (shift_rotate == 0) {
					// rotate
					array[start] = temp;
				} else {
					// shift
					if (a_l == 1) {
						// logic
						array[start] = 0;
					} else {
						// arithmetic
						array[start]=array[0];
					}
				}
			}
		}
		return array;
	}

	public static String decompose_2(int opcode, String instr, int length) {
		StringBuffer code = new StringBuffer();
		code.append(getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		String instr_2 = instr.substring(length);
		instr_2 = instr_2.trim();// trim all leading and tailing
									// whitespace.fault-tolerant.

		int index = instr_2.indexOf(',');
		int bin = Integer.valueOf(instr_2.substring(0, index));
		code.append(getStringFromIntArray(getBinaryFromDec(bin, 2))); // R

		code.append("000"); // IX and I

		instr_2 = instr_2.substring(index + 1);
		if (instr_2.indexOf(',') >= 0
				|| instr_2.toUpperCase().indexOf('I') >= 0) {
			code.replace(0, code.length(), "error!");
		} else {
			bin = Integer.valueOf(instr_2);
			code.append(getStringFromIntArray(getBinaryFromDec(bin, 5)));
		}
		return code.toString();
	}
	
	public static String decompose_2or3(int opcode, String instr, int length) {
		StringBuffer code = new StringBuffer();
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		String instr_2 = instr.substring(length);
		// trim all leading and tailing whitespaces.fault-tolerant.
		instr_2 = instr_2.trim();
		
		code.append("00");

		int index = instr_2.indexOf(',');
		int bin = Integer.valueOf(instr_2.substring(0, index));
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // IX

		instr_2 = instr_2.substring(index + 1);
		if (instr_2.indexOf(',') >= 0
				&& instr_2.toUpperCase().indexOf('I') >= 0) {
			code.append("1"); // I
			index = instr_2.indexOf(',');
			bin = Integer.valueOf(instr_2.substring(0, index));
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 5))); // Address
		} else {
			code.append("0"); // I
			bin = Integer.valueOf(instr_2);
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 5)));
		}
		return code.toString();
	}

	public static String decompose_3or4(int opcode, String instr, int length) {
		StringBuffer code = new StringBuffer();
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		String instr_2 = instr.substring(length);
		// trim all leading and tailing whitespaces.fault-tolerant.
		instr_2 = instr_2.trim();

		int index = instr_2.indexOf(',');
		int bin = Integer.valueOf(instr_2.substring(0, index));
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // R

		instr_2 = instr_2.substring(index + 1);
		index = instr_2.indexOf(',');
		bin = Integer.valueOf(instr_2.substring(0, index));
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // IX

		instr_2 = instr_2.substring(index + 1);
		if (instr_2.indexOf(',') >= 0
				&& instr_2.toUpperCase().indexOf('I') >= 0) {
			code.append("1"); // I
			index = instr_2.indexOf(',');
			bin = Integer.valueOf(instr_2.substring(0, index));
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 5))); // Address
		} else {
			code.append("0"); // I
			bin = Integer.valueOf(instr_2);
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 5)));
		}
		return code.toString();
	}
	
	public static String decompose_FPIorV(int opcode, String instr, int length) {
		StringBuffer code = new StringBuffer();
		StringBuffer ix = new StringBuffer();
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		String instr_2 = instr.substring(length);
		// trim all leading and tailing whitespaces.fault-tolerant.
		instr_2 = instr_2.trim();

		int index = instr_2.indexOf(',');
		int bin = Integer.valueOf(instr_2.substring(0, index));
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // fr

		instr_2 = instr_2.substring(index + 1);
		index = instr_2.indexOf(',');
		bin = Integer.valueOf(instr_2.substring(0, index));
		ix.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // IX

		instr_2 = instr_2.substring(index + 1);
		if (instr_2.indexOf(',') >= 0
				&& instr_2.toUpperCase().indexOf('I') >= 0) {
			code.append("1"); // I
			code.append(ix);
			index = instr_2.indexOf(',');
			bin = Integer.valueOf(instr_2.substring(0, index));
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 5))); // Address
		} else {
			code.append("0"); // I
			code.append(ix);
			bin = Integer.valueOf(instr_2);
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 5)));
		}
		return code.toString();
	}
	
	public static String decompose_SandR(int opcode, String instr, int length){
		StringBuffer code = new StringBuffer();
		StringBuffer count= new StringBuffer();
		StringBuffer al= new StringBuffer();
		StringBuffer lr= new StringBuffer();
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		String instr_2 = instr.substring(length);
		// trim all leading and tailing whitespaces.fault-tolerant.
		instr_2 = instr_2.trim();
		
		int index = instr_2.indexOf(',');
		int bin = Integer.valueOf(instr_2.substring(0, index));
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // R
		
		instr_2 = instr_2.substring(index + 1);
		index = instr_2.indexOf(',');
		bin = Integer.valueOf(instr_2.substring(0, index));
		count.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 4)));//count
		
		instr_2 = instr_2.substring(index + 1);
		index = instr_2.indexOf(',');
		bin = Integer.valueOf(instr_2.substring(0, index));
		lr.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 1)));//lr
		
		instr_2 = instr_2.substring(index + 1);
		bin = Integer.valueOf(instr_2.substring(0, index));
		al.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 1)));//al
		
		code.append(al);
		code.append(lr);
		code.append("00");
		code.append(count);
		
		return code.toString();
	}
	
	public static String decompose_RtoR(int opcode, String instr, int length){
		StringBuffer code = new StringBuffer();
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		String instr_2 = instr.substring(length);
		// trim all leading and tailing whitespaces.fault-tolerant.
		instr_2 = instr_2.trim();
		
		int index = instr_2.indexOf(',');
		if(index!=-1){
			int bin = Integer.valueOf(instr_2.substring(0, index));
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2))); // Rx

			instr_2 = instr_2.substring(index + 1); // Ry
			bin = Integer.valueOf(instr_2);
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2)));
			code.append("000000");
		}else if(index==-1){
			int bin = Integer.valueOf(instr_2);
			code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, 2)));
			code.append("00000000");
		}
		
		return code.toString();
	}
	
	public static String decompose_1(int opcode, String instr, int instr_len, int addr_len){
		StringBuffer code = new StringBuffer();
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(opcode, 6)));
		for(int i=0; i<10-addr_len; i++){
			code.append("0");
		}
		String instr_2 = instr.substring(instr_len);
		// trim all leading and tailing whitespaces.fault-tolerant.
		instr_2 = instr_2.trim();
		int bin= Integer.valueOf(instr_2);
		code.append(Utils.getStringFromIntArray(getBinaryFromDec(bin, addr_len)));
		return code.toString();
	}
	

	public static String getCodeFromInstr(String instr) {
		StringBuffer code = new StringBuffer();

		try {
			if (instr.toUpperCase().startsWith("LDR")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("LDR"), instr,3));

			}
			
			else if(instr.toUpperCase().startsWith("HLT")){
				code.append("0000000000000000");
			}
			
			else if (instr.toUpperCase().startsWith("TRAP")) {
				// TODO 30 OR 36?
				code.append(decompose_1(
						(int) InstructionSet.instructionMap.get("TRAP"), instr, 4, 4));
			}

			else if (instr.toUpperCase().startsWith("STR")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("STR"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("LDA")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("LDA"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("LDX")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("LDX"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("STX")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("STX"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("AMR")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("AMR"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("SMR")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("SMR"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("AIR")) {
				code.append(decompose_2(
						(int) InstructionSet.instructionMap.get("AIR"), instr, 3));
			}

			else if (instr.toUpperCase().startsWith("SIR")) {
				code.append(decompose_2(
						(int) InstructionSet.instructionMap.get("SIR"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("JZ")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("JZ"), instr, 2));
			}
			
			else if (instr.toUpperCase().startsWith("JNE")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("JNE"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("JCC")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("JCC"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("JMA")) {
				code.append(decompose_2or3(
						(int) InstructionSet.instructionMap.get("JMA"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("JSR")) {
				code.append(decompose_2or3(
						(int) InstructionSet.instructionMap.get("JSR"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("RFS")) {
				code.append(decompose_1(
						(int) InstructionSet.instructionMap.get("RFS"), instr, 3, 5));
			}
			
			else if (instr.toUpperCase().startsWith("SOB")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("SOB"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("JGE")) {
				code.append(decompose_3or4(
						(int) InstructionSet.instructionMap.get("JGE"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("MLT")) {
				code.append(decompose_RtoR(
						(int) InstructionSet.instructionMap.get("MLT"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("DVD")) {
				code.append(decompose_RtoR(
						(int) InstructionSet.instructionMap.get("DVD"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("TRR")) {
				code.append(decompose_RtoR(
						(int) InstructionSet.instructionMap.get("TRR"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("AND")) {
				code.append(decompose_RtoR(
						(int) InstructionSet.instructionMap.get("AND"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("ORR")) {
				code.append(decompose_RtoR(
						(int) InstructionSet.instructionMap.get("ORR"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("NOT")) {
				code.append(decompose_RtoR(
						(int) InstructionSet.instructionMap.get("NOT"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("SRC")) {
				code.append(decompose_SandR(
						(int) InstructionSet.instructionMap.get("SRC"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("RRC")) {
				code.append(decompose_SandR(
						(int) InstructionSet.instructionMap.get("RRC"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("IN")) {
				code.append(decompose_2(
						(int) InstructionSet.instructionMap.get("IN"), instr, 2));
			}
			
			else if (instr.toUpperCase().startsWith("OUT")) {
				code.append(decompose_2(
						(int) InstructionSet.instructionMap.get("OUT"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("CHK")) {
				code.append(decompose_2(
						(int) InstructionSet.instructionMap.get("CHK"), instr, 3));
			}
			
			else if (instr.toUpperCase().startsWith("FADD")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("FADD"), instr, 4));
			}
			
			else if (instr.toUpperCase().startsWith("FSUB")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("FSUB"), instr, 4));
			}
			
			else if (instr.toUpperCase().startsWith("VADD")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("VADD"), instr, 4));
			}
			
			else if (instr.toUpperCase().startsWith("VSUB")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("VSUB"), instr, 4));
			}
			
			else if (instr.toUpperCase().startsWith("CNVRT")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("CNVRT"), instr, 5));
			}
			
			else if (instr.toUpperCase().startsWith("LDFR")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("LDFR"), instr, 4));
			}
			
			else if (instr.toUpperCase().startsWith("STFR")) {
				code.append(decompose_FPIorV(
						(int) InstructionSet.instructionMap.get("STFR"), instr, 4));
			}
			
		} catch (Exception e) {
			// e.printStackTrace();
			code = new StringBuffer("error!");
		}
		return code.toString();
	}

	/**
	 * Generate binary parameter from subString of instruction
	 * 
	 * @param originalInstruction
	 * @return binary parameter
	 */
	public static String getInstructionParam(String originalInstruction) {
		return null;
	}

}
