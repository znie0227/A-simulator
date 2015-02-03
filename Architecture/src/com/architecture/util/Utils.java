package com.architecture.util;

import com.architecture.mainframe.Main;
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

	public static String getBinaryFromDec(int val, int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(val % 2);
			val = val / 2;
		}
		return sb.reverse().toString();
	}

	public static String getDecimalFromBin(int[] val) {
		return null;
	}

	public static String getDecimalFromBin(String val) {
		return null;
	}

	/**
	 * 
	 * Compute the Effective Address using a given instruction
	 * 
	 * @param instruction
	 * @return effective address
	 */
	public static String getEffectiveAddress(String instruction) {
		
		// TODO 还没测试...
		
		if (instruction.charAt(10) == '0') {
			// No indirect addressing
			if (instruction.substring(8, 9).equals("00")) {
				return instruction.substring(11);
			}
			// just indexing
			else {

				String regName = instruction.substring(8, 9);
				regName = "X" + getDecimalFromBin(regName);
				String regData = Main.getRegisterByName(regName)
						.getDataInString();
				return getBinaryFromDec(
						Integer.valueOf(getDecimalFromBin(regData))
								+ Integer.valueOf(getDecimalFromBin(instruction
										.substring(11))), 11);// TODO 11 is
																// questionable...
			}
		} else {
			// indirect addressing, but No indexing
			if (instruction.substring(8, 9).equals("00")) {
				return Memory
						.getInstance()
						.read(Integer.valueOf(getDecimalFromBin(instruction
								.substring(11)))).getDataInString();
			} else {
				// both indirect addressing and indexing

				String regName = instruction.substring(8, 9);
				regName = "X" + getDecimalFromBin(regName);
				String regData = Main.getRegisterByName(regName)
						.getDataInString();
				int contentOfIR = Integer.valueOf(getDecimalFromBin(regData));
				int address = Integer.valueOf(getDecimalFromBin(instruction
						.substring(11)));
				return Memory.getInstance().read(contentOfIR+address).getDataInString();

			}
		}
	}

	public static String getCodeFromInstr(String instr) {
		StringBuffer code = new StringBuffer();

		try {
			if (instr.toUpperCase().startsWith("LDR")) {
				code.append(getBinaryFromDec(1, 6));// opcode
				String instr_2 = instr.substring(3);
				// trim all leading and tailing whitespaces.fault-tolerant.
				instr_2 = instr_2.trim();

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // R

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // IX

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0"); // I
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5)); // Address
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("STR")) {
				code.append(getBinaryFromDec(2, 6));
				String instr_2 = instr.substring(3);
				// trim all leading and tailing whitespaces.fault-tolerant.
				instr_2 = instr_2.trim();
				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("LDA")) {
				code.append(getBinaryFromDec(3, 6));
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("LDX")) {
				code.append(getBinaryFromDec(41, 6));
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("STX")) {
				code.append(getBinaryFromDec(42, 6));
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("AMR")) {
				code.append(getBinaryFromDec(4, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // R

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // IX

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0"); // I
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5)); // Address
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("SMR")) {
				code.append(getBinaryFromDec(5, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // R

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // IX

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0"); // I
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDec(bin, 5)); // Address
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("AIR")) {
				code.append(getBinaryFromDec(6, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // R

				code.append("000"); // IX and I

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.replace(0, code.length() - 1, "error!");
				} else {
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("SIR")) {
				code.append(getBinaryFromDec(6, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDec(bin, 2)); // R

				code.append("000"); // IX and I

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.replace(0, code.length(), "error!");
				} else {
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDec(bin, 5));
				}
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
	public static String getInstructionParam(String originalInstruction){
		return null;
	}

}
