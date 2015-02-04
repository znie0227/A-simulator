package com.architecture.app;

import com.architecture.model.Memory;
import com.architecture.model.Register;
import com.architecture.util.Config;
import com.architecture.util.Constants;
import com.architecture.util.Utils;

public class CPU {

	private static CPU instance;

	private static int state;

	public static CPU getInstance() {
		if (instance == null) {
			instance = new CPU();
		}
		return instance;
	}

	private void execute() {
		if (state != Constants.CPU_STATE_IDLE) {
			return;
		}
		// get PC address
		int address = Application.getRegisterByName("PC").getDecData();
		executeCertainInstruction(Memory.getInstance().read(address)
				.getDataInString());
		state = Constants.CPU_STATE_FINISHED;
	}

	/**
	 * execute a certain instruction
	 * 
	 * @param ins
	 */
	private void executeCertainInstruction(String ins) {

		// Get EA
		int EA = Utils.getEffectiveAddressInDec(ins);
		String registerName;
		Register reg;
		int regData, memoryData;
		switch (Integer.valueOf(ins.substring(0, 5))) {
		// TODO
		case 000001:
			// LDR
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			Application.getRegisterByName(registerName).setData(
					Memory.getInstance().read(EA).getData());

			break;
		case 000002:
			// STR
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			Memory.getInstance().write(EA,
					Application.getRegisterByName(registerName).getData());
			break;
		case 000003:
			// LDA
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			Application.getRegisterByName(registerName).setData(
					Utils.getBinaryFromDec(EA, 16));
			break;
		case 000004:
			// AMR
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			reg = Application.getRegisterByName(registerName);
			regData = reg.getDecData();
			memoryData = Memory.getInstance().read(EA).getDataInDec();
			reg.setData(Utils.getBinaryFromDec(regData + memoryData,
					Config.GPR_SIZE));
			break;
		case 000005:
			// SMR
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			reg = Application.getRegisterByName(registerName);
			regData = reg.getDecData();
			memoryData = Memory.getInstance().read(EA).getDataInDec();
			reg.setData(Utils.getBinaryFromDec(regData - memoryData,
					Config.GPR_SIZE));
			break;
		case 000006:
			// AIR
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			reg = Application.getRegisterByName(registerName);
			regData = reg.getDecData();
			reg.setData(Utils.getBinaryFromDec(
					regData + Utils.getDecimalFromBin(ins.substring(11)),
					Config.GPR_SIZE));
			break;
		case 000007:
			// SIR
			registerName = "R" + Utils.getDecimalFromBin(ins.substring(6, 8));
			reg = Application.getRegisterByName(registerName);
			regData = reg.getDecData();
			reg.setData(Utils.getBinaryFromDec(
					regData - Utils.getDecimalFromBin(ins.substring(11)),
					Config.GPR_SIZE));
			break;
		case 000041:
			// LDX
			registerName = "X" + Utils.getDecimalFromBin(ins.substring(6, 8));
			Application.getRegisterByName(registerName).setData(
					Memory.getInstance().read(EA).getData());

			break;
		case 000042:
			// STX
			registerName = "X" + Utils.getDecimalFromBin(ins.substring(6, 8));
			Memory.getInstance().write(EA,
					Application.getRegisterByName(registerName).getData());
			break;
		}
	}

}
