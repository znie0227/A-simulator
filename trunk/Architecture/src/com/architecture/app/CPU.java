package com.architecture.app;

import java.awt.Color;

import com.architecture.device.ConsoleKeyboard;
import com.architecture.device.ConsolePrinter;
import com.architecture.device.Device;
import com.architecture.model.Log;
import com.architecture.model.Memory;
import com.architecture.util.Constants;
import com.architecture.util.Utils;
import com.architecture.view.IOSystemPrinterPanel;

public class CPU {

	private static CPU instance;

	private static int state = Constants.CPU_STATE_IDLE;

	private int[] opcode = new int[6];
	private int[] rfi = new int[2];
	private int[] ix = new int[2];
	private int ind;
	private int[] address = new int[5];
	private int EA;
	private int arithOrLogic;
	private int leftOrRight;

	private Device device;

	public static Thread currentThread;
	private Thread thread;

	public static int keyEvent = -1;

	public static CPU getInstance() {
		if (instance == null) {
			instance = new CPU();
		}
		return instance;
	}

	public void executeComplete(int instructionLength) {
		thread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				for (int i = 0; i < instructionLength; i++) {
					execute();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		thread.start();

	}

	public void execute() {
		// reset CC
		// Application.getRegisterByName("CC").setDataByDec(0);
		if (state != Constants.CPU_STATE_IDLE) {
			return;
		}
		// get PC address
		int[] PCdata = Application.getRegisterByName("PC").getData();
		Log.d("get PC address:"
				+ Application.getRegisterByName("PC").getDataInString());
		// move PC to MAR
		Application.getRegisterByName("MAR").setData(PCdata);
		Log.d("move PC to MAR:"
				+ Application.getRegisterByName("MAR").getDataInString());
		// Fetch the next instruction from the memory and load it into the MDR
		Application.getRegisterByName("MDR")
				.setData(
						Memory.getInstance()
								.read(Application.getRegisterByName("MAR")
										.getDecData()).getData());
		Log.d("Fetch the next instruction from the memory and load it into the MDR:"
				+ Application.getRegisterByName("MDR").getDataInString());
		// Copy the MDR into the IR
		Application.getRegisterByName("IR").setData(
				Application.getRegisterByName("MDR").getData());
		Log.d("Copy the MDR into the IR:"
				+ Application.getRegisterByName("IR").getDataInString());
		// Extract param
		new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 6; i++) {
					opcode[i] = Application.getRegisterByName("IR").getData()[i];
				}
				Log.d("Extract param: OPCode="
						+ Utils.getStringFromIntArray(opcode));
				super.run();
			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				for (int i = 6; i < 8; i++) {
					rfi[i - 6] = Application.getRegisterByName("IR").getData()[i];
				}
				Log.d("Extract param: rfi");
				super.run();
			}
		}.start();
		new Thread() {

			@Override
			public void run() {
				for (int i = 8; i < 10; i++) {
					ix[i - 8] = Application.getRegisterByName("IR").getData()[i];
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
					address[i - 11] = Application.getRegisterByName("IR")
							.getData()[i];
				}
				Log.d("Extract param: IR");
				super.run();
			}
		}.start();

		// compute EA
		EA = Utils.getEffectiveAddressInDec(Application.getRegisterByName("IR")
				.getDataInString());
		Log.d("compute EA:" + EA);

		// Move EA to MAR
		Application.getRegisterByName("MAR").setDataByDec(EA);
		Log.d("Move EA to MAR"
				+ Application.getRegisterByName("MAR").getDataInString());

		executeCertainInstruction(Utils.getStringFromIntArray(opcode));

		PCIncrement();
		state = Constants.CPU_STATE_IDLE;
	}

	/**
	 * execute a certain instruction
	 * 
	 * @param ins
	 */
	private void executeCertainInstruction(String opcode) {

		String registerName, registerName1, regPlus1Name;
		System.out.println("inst:" + opcode + "   "
				+ Utils.getDecimalFromBin(opcode));
		switch (Utils.getDecimalFromBin(opcode)) {
		// TODO
		case 000001:
			// LDR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// Get contents of memory location in MAR (MDR<- M(MAR))
			Application.getRegisterByName("MDR").setData(
					Memory.getInstance()
							.read(Application.getRegisterByName("MAR")
									.getDecData()).getData());
			System.out.println("memory:"
					+ Memory.getInstance()
							.read(Application.getRegisterByName("MAR")
									.getDecData()).getDataInDec());
			Log.d("Get contents of memory location in MAR (MDR<- M(MAR)):"
					+ Application.getRegisterByName("MDR").getDataInString());
			// RF[RFI] <- MDR
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("MDR").getData());
			Log.d("RF[RFI] <- MDR:"
					+ Application.getRegisterByName(registerName)
							.getDataInString());

			break;
		case 000002:
			// STR
			registerName = "R" + Utils.getDecimalFromBin(rfi);

			Memory.getInstance().write(
					Application.getRegisterByName("MAR").getDecData(),
					Application.getRegisterByName(registerName).getData());
			Log.d("write memory, Loc:"
					+ Application.getRegisterByName("MAR").getDecData()
					+ " data="
					+ Application.getRegisterByName(registerName).getData());
			break;
		case 000003:
			// LDA
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			if (ind == 1) {
				Application.getRegisterByName(registerName).setData(
						Memory.getInstance()
								.read(Utils.getDecimalFromBin(address))
								.getData());
			} else {
				Application.getRegisterByName(registerName).setData(address);

			}
			// Get contents of memory location in MAR (MDR<- M(MAR))

			// Application.getRegisterByName(registerName).setData(
			// Utils.getBinaryFromDec(EA, 16));
			break;
		case 000004:
			// AMR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName);
			System.out.println(Application.getRegisterByName("ARR")
					.getDataInString());
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("ARR").getData());
			if (Application.getRegisterByName("CC").getDataAtPosition(0) == 1) {
				Log.e("OVERFLOW!!!");
			}
			Log.d("RF[RFI] = ARR");
			// reg = Application.getRegisterByName(registerName);
			// regData = reg.getDecData();
			// memoryData = Memory.getInstance().read(EA).getDataInDec();
			// reg.setData(Utils.getBinaryFromDec(regData + memoryData,
			// Config.GPR_SIZE));
			break;
		case 000005:
			// SMR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName);
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("ARR").getData());
			if (Application.getRegisterByName("CC").getDataAtPosition(1) == 1) {
				Log.e("UNDERFLOW!!!");
			}
			Log.d("RF[RFI] = ARR");
			// reg = Application.getRegisterByName(registerName);
			// regData = reg.getDecData();
			// memoryData = Memory.getInstance().read(EA).getDataInDec();
			// reg.setData(Utils.getBinaryFromDec(regData - memoryData,
			// Config.GPR_SIZE));
			break;
		case 000006:
			// AIR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName, Utils.getDecimalFromBin(address));
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("ARR").getData());
			if (Application.getRegisterByName("CC").getDataAtPosition(0) == 1) {
				Log.e("OVERFLOW!!!");
			}
			Log.d("RF[RFI] = ARR");
			// reg = Application.getRegisterByName(registerName);
			// regData = reg.getDecData();
			// reg.setData(Utils.getBinaryFromDec(
			// regData + Utils.getDecimalFromBin(address), Config.GPR_SIZE));
			break;
		case 000007:
			// SIR
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			Utils.adder(opcode, registerName, Utils.getDecimalFromBin(address));
			// RF[RFI] = ARR
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("ARR").getData());
			if (Application.getRegisterByName("CC").getDataAtPosition(1) == 1) {
				Log.e("UNDERFLOW!!!");
			}
			Log.d("RF[RFI] = ARR");
			// reg = Application.getRegisterByName(registerName);
			// regData = reg.getDecData();
			// reg.setData(Utils.getBinaryFromDec(
			// regData - Utils.getDecimalFromBin(address), Config.GPR_SIZE));
			break;
		case 41:
			// LDX
			registerName = "X" + Utils.getDecimalFromBin(rfi);
			Application.getRegisterByName(registerName).setData(
					Memory.getInstance().read(EA).getData());

			break;
		case 42:
			// STX
			registerName = "X" + Utils.getDecimalFromBin(rfi);
			Memory.getInstance().write(EA,
					Application.getRegisterByName(registerName).getData());
			break;
		/*
		 * Transfer Instructions
		 */
		case 10:
			// JZ jump if zero
			// Jump If Zero:
			// If c(r) = 0, then PC  EA or c(EA), if I bit set;
			// Else PC <- PC+1
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			if (Application.getRegisterByName(registerName).getDecData() == 0) {
				Application.getRegisterByName("PC").setData(
						Application.getRegisterByName("MAR").getData());
			}

			break;
		case 11:
			// JNE jump if not equal
			// If c(r) != 0, then PC <- EA or c(EA) , if I bit set;
			// Else PC <- PC + 1
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			if (!(Application.getRegisterByName(registerName).getDecData() == 0)) {
				Application.getRegisterByName("PC").setData(
						Application.getRegisterByName("MAR").getData());
			}
			break;
		case 12:
			// JCC Jump If Condition Code
			// cc replaces r for this instruction
			// cc takes values 0, 1, 2, 3 as above and specifies the bit in the
			// Condition Code Register to check;
			// If cc bit = 1, PC  EA or c(EA), if I bit set;
			// Else PC <- PC + 1
			registerName = "CC";
			// TODO
			if (Application.getRegisterByName(registerName).getData()[Utils
					.getDecimalFromBin(rfi)] == 1) {
				Application.getRegisterByName("PC").setData(
						Application.getRegisterByName("MAR").getData());
			}
			break;
		case 13:
			// JMA Unconditional Jump To Address
			// PC <- EA, if I bit not set; PC  c(EA), if I bit set
			Application.getRegisterByName("PC").setData(
					Application.getRegisterByName("MAR").getData());
			break;
		case 14:
			// JSR Jump and Save Return Address
			// R3 <- PC+1;
			// PC <- EA or PC <- c(EA), if I bit set
			// R0 should contain pointer to arguments
			// Argument list should end with –17777 value

			Application.getRegisterByName("R3").setDataByDec(
					Application.getRegisterByName("PC").getDecData() + 1);
			Application.getRegisterByName("PC").setData(
					Application.getRegisterByName("MAR").getData());
			break;
		case 15:
			// RFS Return From Subroutine
			// Return From Subroutine w/ return code as Immed portion (optional)
			// stored in the instruction’s address field.
			// R0 <- Immed; PC <- c(R3)
			// IX, I fields are ignored.
			Application.getRegisterByName("R0").setData(address);
			Application.getRegisterByName("PC").setData(
					Application.getRegisterByName("R3").getData());

			break;
		case 16:
			// SOB Subtract One and Branch
			// r <- c(r) – 1
			// If c(r) > 0, PC <- EA; but PC <- c(EA), if I bit set;
			// Else PC <- PC + 1
			registerName = registerName = "R" + Utils.getDecimalFromBin(rfi);
			Application.getRegisterByName(registerName)
					.setDataByDec(
							Application.getRegisterByName(registerName)
									.getDecData() - 1);
			if (Application.getRegisterByName(registerName).getDecData() > 0) {
				Application.getRegisterByName("PC").setData(
						Application.getRegisterByName("MAR").getData());
			}
			break;
		case 17:
			// JGE Jump Greater Than or Equal To
			// If c(r) >= 0, then PC <- EA or c(EA) , if I bit set;
			// Else PC <- PC + 1

			registerName = registerName = "R" + Utils.getDecimalFromBin(rfi);
			if (Application.getRegisterByName(registerName).getDecData() > 0) {
				Application.getRegisterByName("PC").setData(
						Application.getRegisterByName("MAR").getData());
			}
			break;
		case 20:
			// MLT
			// Multiply Register by Register
			// rx, rx+1 <- c(rx) * c(ry)
			// rx must be 0 or 2
			// ry must be 0 or 2
			// rx contains the high order bits, rx+1 contains the low order bits
			// of the result
			// Set OVERFLOW flag, if overflow
			// TODO check rx and ry-->0 or 2
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// in this situation, there is no index register, so we can get ry
			// using bits that location
			registerName1 = "R" + Utils.getDecimalFromBin(ix);

			Utils.mult(opcode, registerName, registerName1);
			System.out.println(Application.getRegisterByName("MRR")
					.getDataInString());
			// RF[RFI] = higher order bits of MRR
			Application.getRegisterByName(registerName).setDataByString(
					Application.getRegisterByName("MRR").getDataInString()
							.substring(0, 16));
			// RF[RFI+1] = lower order bits of MRR
			regPlus1Name = "R" + (Utils.getDecimalFromBin(rfi) + 1);
			Application.getRegisterByName(regPlus1Name).setDataByString(
					Application.getRegisterByName("MRR").getDataInString()
							.substring(16, 32));
			if (Application.getRegisterByName("CC").getDataAtPosition(0) == 1) {
				Log.e("OVERFLOW!!!");
			}
			Log.d("Rx = MRR[0-15]\nRy = MRR[16-31]");

			break;
		case 21:
			// DVD
			// Divide Register by Register
			// rx, rx+1 <- c(rx)/ c(ry)
			// rx must be 0 or 2
			// rx contains the quotient; rx+1 contains the remainder
			// ry must be 0 or 2
			// If c(ry) = 0, set cc(3) to 1 (set DIVZERO flag)

			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// in this situation, there is no index register, so we can get ry
			// using bits that location
			registerName1 = "R" + Utils.getDecimalFromBin(ix);

			Utils.mult(opcode, registerName, registerName1);
			System.out.println(Application.getRegisterByName("MRR")
					.getDataInString());
			// RF[RFI] = higher order bits of MRR
			Application.getRegisterByName(registerName).setDataByString(
					Application.getRegisterByName("MRR").getDataInString()
							.substring(0, 16));
			// RF[RFI+1] = lower order bits of MRR
			regPlus1Name = "R" + (Utils.getDecimalFromBin(rfi) + 1);
			Application.getRegisterByName(regPlus1Name).setDataByString(
					Application.getRegisterByName("MRR").getDataInString()
							.substring(16, 32));
			if (Application.getRegisterByName("CC").getDataAtPosition(2) == 1) {
				Log.e("DIVZERO!!!");
			}
			Log.d("Rx = MRR[0-15]\nRy = MRR[16-31]");

			break;
		case 22:
			// TRR
			// Test the Equality of Register and Register
			// If c(rx) = c(ry), set cc(4) <-1; else, cc(4) <- 0
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// in this situation, there is no index register, so we can get ry
			// using bits that location
			registerName1 = "R" + Utils.getDecimalFromBin(ix);
			if (Application.getRegisterByName(registerName).getDecData() == Application
					.getRegisterByName(registerName1).getDecData()) {
				Application.getRegisterByName("CC").setDataByBitPosition(1, 3);
				Log.d("Equal. Set CC[3]=1");
			} else {
				Application.getRegisterByName("CC").setDataByBitPosition(0, 3);
				Log.d("Not Equal. Set CC[3]=0");
			}
			break;
		case 23:
			// AND
			// Logical And of Register and Register
			// c(rx) <- c(rx) AND c(ry)
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// in this situation, there is no index register, so we can get ry
			// using bits that location
			registerName1 = "R" + Utils.getDecimalFromBin(ix);

			Utils.logic(opcode, registerName, registerName1);
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("LRR").getData());
			Log.d("RF[RFI1] <- LRR");
			break;
		case 24:
			// ORR
			// Logical Or of Register and Register
			// c(rx)  c(rx) OR c(ry)
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// in this situation, there is no index register, so we can get ry
			// using bits that location
			registerName1 = "R" + Utils.getDecimalFromBin(ix);

			Utils.logic(opcode, registerName, registerName1);
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("LRR").getData());
			Log.d("RF[RFI1] <- LRR");
			break;
		case 25:
			// NOT
			// Logical Not of Register To Register
			// C(rx) <- NOT c(rx)
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			// in this situation, there is no index register, so we can get ry
			// using bits that location
			registerName1 = "R" + Utils.getDecimalFromBin(ix);

			Utils.logic(opcode, registerName, registerName1);
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("LRR").getData());
			Log.d("RF[RFI1] <- LRR");

			break;

		case 31:
			// SRC
			// Shift Register by Count
			// c(r) is shifted left (L/R =1) or right (L/R = 0) either logically
			// (A/L = 1) or arithmetically (A/L = 0)
			// XX, XXX are ignored
			// Count = 0…15
			// If Count = 0, no shift occurs
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			leftOrRight = ix[1];
			arithOrLogic = ix[0];
			Utils.shifter(opcode, registerName, arithOrLogic, leftOrRight,
					Utils.getDecimalFromBin(address));
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("SRR").getData());
			Log.d("RF[RFI1] <- SRR");
			break;
		case 32:
			// RRC
			// Rotate Register by Count
			// c(r) is rotated left (L/R = 1) or right (L/R =0) either logically
			// (A/L =1)
			// XX, XXX is ignored
			// Count = 0…15
			// If Count = 0, no rotate occurs
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			leftOrRight = ix[1];
			arithOrLogic = ix[0];
			Utils.shifter(opcode, registerName, arithOrLogic, leftOrRight,
					Utils.getDecimalFromBin(address));
			Application.getRegisterByName(registerName).setData(
					Application.getRegisterByName("SRR").getData());
			Log.d("RF[RFI1] <- SRR");
			break;
		case 61:
			// IN
			// Input Character To Register from Device, r = 0..3

			registerName = "R" + Utils.getDecimalFromBin(rfi);
			device = Device.getDevice(Utils.getDecimalFromBin(address));

			if (device instanceof ConsoleKeyboard) {
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.setSelected(true);
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.setFocusable(true);
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.requestFocus();
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.setBackground(Color.GREEN);
			}
			System.out.println(keyEvent);
			keyEvent = -1;

			this.currentThread = Thread.currentThread();
			// System.out.println("Start waiting...");
			try {
				synchronized (currentThread) {
					currentThread.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("waiting terminated:" + (char) keyEvent);

			Application.getRegisterByName(registerName).setDataByDec(keyEvent);
			if (device instanceof ConsoleKeyboard) {
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.setSelected(false);
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.setFocusable(false);
				IOSystemPrinterPanel.getInstance().getLinkToKeyboardBox()
						.setBackground(null);
				device.write((char)keyEvent+"");
			}

			// device.write(msg);
			break;
		case 62:
			// OUT
			// Output Character to Device from Register, r = 0..3

			registerName = "R" + Utils.getDecimalFromBin(rfi);
			
			device = Device.getDevice(Utils.getDecimalFromBin(address));
			
			int ascii = Application.getRegisterByName(registerName)
					.getDecData();
			if (ascii >= 0 && ascii <= 128 && device instanceof ConsolePrinter) {
				IOSystemPrinterPanel.writeToPrinter((char) ascii + "");
				device.write((char)ascii+"");
			}

			// }
			break;
		case 63:
			// CHK
			// Check Device Status to Register, r = 0..3
			// c(r) <- device status
			registerName = "R" + Utils.getDecimalFromBin(rfi);
			device = Device.getDevice(Utils.getDecimalFromBin(address));
			Application.getRegisterByName(registerName).setDataByDec(device.read());
			System.out.println("chk:"+device.read());
			break;
		}
	}

	private void PCIncrement() {
		int currentPCPos = Application.getRegisterByName("PC").getDecData();
		Application.getRegisterByName("PC").setDataByDec(currentPCPos + 1);
		Log.d("PC <- PC + 1 (PC Increment)");
	}

}
