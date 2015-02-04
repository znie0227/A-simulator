package com.architecture.app;

import com.architecture.model.Log;
import com.architecture.model.Memory;
import com.architecture.model.Register;
import com.architecture.util.Config;
import com.architecture.util.Constants;
import com.architecture.util.Utils;

public class CPU {

	private static CPU instance;

	private static int state;

	private int[] opcode = new int[6];
	private int[] rfi = new int[2];
	private int[] ix = new int[2];
	private int ind;
	private int[] address = new int[5];
	int EA;

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
		int[] PCdata = Application.getRegisterByName("PC").getData();
		Log.d("get PC address");
		// move PC to MAR
		Application.getRegisterByName("MAR").setData(PCdata);
		Log.d("move PC to MAR");
		// Fetch the next instruction from the memory and load it into the MDR
		Application.getRegisterByName("MDR")
				.setData(
						Memory.getInstance()
								.read(Application.getRegisterByName("MAR")
										.getDecData()).getData());
		Log.d("Fetch the next instruction from the memory and load it into the MDR");
		// Copy the MDR into the IR
		Application.getRegisterByName("IR").setData(
				Application.getRegisterByName("MDR").getData());
		Log.d("Copy the MDR into the IR");
		// Extract param
		new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 6; i++) {
					opcode[i] = Application.getRegisterByName("IR").getData()[i];
				}
				Log.d("Extract param: OPCode");
				super.run();
			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				for (int i = 6; i < 8; i++) {
					rfi[i] = Application.getRegisterByName("IR").getData()[i];
				}
				Log.d("Extract param: rfi");
				super.run();
			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				for (int i = 8; i < 10; i++) {
					ix[i] = Application.getRegisterByName("IR").getData()[i];
				}
				Log.d("Extract param: ix");
				super.run();
			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				ind = Application.getRegisterByName("IR").getData()[10];
				Log.d("Extract param: ind");
				super.run();
			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				for (int i = 11; i < 16; i++) {
					address[i] = Application.getRegisterByName("IR").getData()[i];
				}
				Log.d("Extract param: IR");
				super.run();
			}
		}.start();

		// compute EA
		EA = Utils.getEffectiveAddressInDec(Application.getRegisterByName("IR")
				.getDataInString());
		Log.d("compute EA");
		
		// Move EA to MAR
		Application.getRegisterByName("MAR").setDataByDec(EA);
		Log.d("Move EA to MAR");
		
		executeCertainInstruction(Utils.getStringFromIntArray(opcode));

		PCIncrement();
		state = Constants.CPU_STATE_FINISHED;
	}

	/**
	 * execute a certain instruction
	 * 
	 * @param ins
	 */
	private void executeCertainInstruction(String opcode) {

		// Get EA
		// int EA = Utils.getEffectiveAddressInDec(ins);
		String registerName;
		Register reg;
		int regData, memoryData;
		switch (Integer.valueOf(opcode)) {
		// TODO
		case 000001:
			// LDR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// Get contents of memory location in MAR (MDR<- M(MAR))
			Application.getRegisterByName("MDR").setData(
					Memory.getInstance()
							.read(Application.getRegisterByName("MAR")
									.getDecData()).getData());
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("MDR").getData());

			break;
		case 000002:
			// STR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Memory.getInstance().write(
					Application.getRegisterByName("MAR").getDecData(),
					Application.getRegisterByName(registerName).getData());
			break;
		case 000003:
			// LDA
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			if (ind==1) {
				Application.getRegisterByName(registerName).setData(Memory.getInstance().read(Utils.getDecimalFromBin(address)).getData());
			} else {
				Application.getRegisterByName(registerName).setData(
						address);
				
			}
			// Get contents of memory location in MAR (MDR<- M(MAR))
			
//			Application.getRegisterByName(registerName).setData(
//					Utils.getBinaryFromDec(EA, 16));
			break;
		case 000004:
			// AMR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName);
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(Application.getRegisterByName("ARR").getData());
			Log.d("RF[RFI] = ARR");
//			reg = Application.getRegisterByName(registerName);
//			regData = reg.getDecData();
//			memoryData = Memory.getInstance().read(EA).getDataInDec();
//			reg.setData(Utils.getBinaryFromDec(regData + memoryData,
//					Config.GPR_SIZE));
			break;
		case 000005:
			// SMR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName);
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(Application.getRegisterByName("ARR").getData());
			Log.d("RF[RFI] = ARR");
//			reg = Application.getRegisterByName(registerName);
//			regData = reg.getDecData();
//			memoryData = Memory.getInstance().read(EA).getDataInDec();
//			reg.setData(Utils.getBinaryFromDec(regData - memoryData,
//					Config.GPR_SIZE));
			break;
		case 000006:
			// AIR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName,Utils.getDecimalFromBin(address));
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(Application.getRegisterByName("ARR").getData());
			Log.d("RF[RFI] = ARR");
//			reg = Application.getRegisterByName(registerName);
//			regData = reg.getDecData();
//			reg.setData(Utils.getBinaryFromDec(
//					regData + Utils.getDecimalFromBin(address), Config.GPR_SIZE));
			break;
		case 000007:
			// SIR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName,Utils.getDecimalFromBin(address));
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(Application.getRegisterByName("ARR").getData());
			Log.d("RF[RFI] = ARR");
//			reg = Application.getRegisterByName(registerName);
//			regData = reg.getDecData();
//			reg.setData(Utils.getBinaryFromDec(
//					regData - Utils.getDecimalFromBin(address), Config.GPR_SIZE));
			break;
		case 000041:
			// LDX
			registerName = "X" + Utils.getDecimalFromBin(rfi);
			Application.getRegisterByName(registerName).setData(
					Memory.getInstance().read(EA).getData());

			break;
		case 000042:
			// STX
			registerName = "X" + Utils.getDecimalFromBin(rfi);
			Memory.getInstance().write(EA,
					Application.getRegisterByName(registerName).getData());
			break;
		}
	}

	private void PCIncrement() {
		// TODO
	}

}
