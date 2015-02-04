package com.architecture.util;

import com.architecture.app.Application;
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
		// TODO
		return 1;
	}
	
	public static int getDecimalFromBin(String val) {
		// TODO
		return 1;
	}
	public static String getDecimalFromBinInString(int[] val) {
		// TODO
		return null;
	}

	public static String getDecimalFromBinInString(String val) {
		// TODO
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
				regName = "X" + getDecimalFromBinInString(regName);
				String regData = Application.getRegisterByName(regName)
						.getDataInString();
				return getBinaryFromDecInString(
						getDecimalFromBin(regData)
								+ getDecimalFromBin(instruction
										.substring(11)), 11);// TODO 11 is
																// questionable...
			}
		} else {
			// indirect addressing, but No indexing
			if (instruction.substring(8, 9).equals("00")) {
				return Memory
						.getInstance()
						.read(getDecimalFromBin(instruction
								.substring(11))).getDataInString();
			} else {
				// both indirect addressing and indexing

				String regName = instruction.substring(8, 9);
				regName = "X" + getDecimalFromBinInString(regName);
				String regData = Application.getRegisterByName(regName)
						.getDataInString();
				int contentOfIR = getDecimalFromBin(regData);
				int address = getDecimalFromBin(instruction
						.substring(11));
				return Memory.getInstance().read(contentOfIR + address)
						.getDataInString();

			}
		}
	}

	public static int getEffectiveAddressInDec(String instruction) {
		return Integer.valueOf(getEffectiveAddress(instruction));
	}

	public static String getCodeFromInstr(String instr) {
		StringBuffer code = new StringBuffer();

		try {
			if (instr.toUpperCase().startsWith("LDR")) {
				code.append(getBinaryFromDecInString(1, 6));// opcode
				String instr_2 = instr.substring(3);
				// trim all leading and tailing whitespaces.fault-tolerant.
				instr_2 = instr_2.trim();

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // R

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // IX

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0"); // I
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5)); // Address
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("STR")) {
				code.append(getBinaryFromDecInString(2, 6));
				String instr_2 = instr.substring(3);
				// trim all leading and tailing whitespaces.fault-tolerant.
				instr_2 = instr_2.trim();
				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("LDA")) {
				code.append(getBinaryFromDecInString(3, 6));
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("LDX")) {
				code.append(getBinaryFromDecInString(41, 6));
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("STX")) {
				code.append(getBinaryFromDecInString(42, 6));
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2));

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0");
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5));
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("AMR")) {
				code.append(getBinaryFromDecInString(4, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // R

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // IX

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0"); // I
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5)); // Address
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("SMR")) {
				code.append(getBinaryFromDecInString(5, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // R

				instr_2 = instr_2.substring(index + 1);
				index = instr_2.indexOf(',');
				bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // IX

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.append("0"); // I
					index = instr_2.indexOf(',');
					bin = Integer.valueOf(instr_2.substring(0, index));
					code.append(getBinaryFromDecInString(bin, 5)); // Address
				} else {
					code.append("1"); // I
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("AIR")) {
				code.append(getBinaryFromDecInString(6, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // R

				code.append("000"); // IX and I

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.replace(0, code.length() - 1, "error!");
				} else {
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
				}
			}

			else if (instr.toUpperCase().startsWith("SIR")) {
				code.append(getBinaryFromDecInString(6, 6));// opcode
				String instr_2 = instr.substring(3);
				instr_2 = instr_2.trim();// trim all leading and tailing
											// whitespace.fault-tolerant.

				int index = instr_2.indexOf(',');
				int bin = Integer.valueOf(instr_2.substring(0, index));
				code.append(getBinaryFromDecInString(bin, 2)); // R

				code.append("000"); // IX and I

				instr_2 = instr_2.substring(index + 1);
				if (instr_2.indexOf(',') >= 0) {
					code.replace(0, code.length(), "error!");
				} else {
					bin = Integer.valueOf(instr_2);
					code.append(getBinaryFromDecInString(bin, 5));
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
	public static String getInstructionParam(String originalInstruction) {
		return null;
	}

}
