package com.architecture.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.architecture.model.Register;
import com.architecture.util.Config;
import com.architecture.util.Utils;
import com.architecture.view.MainFrame;

public class Application {

	private static List<String> registerList = new ArrayList<String>();
	private static HashMap<String, Register> registerMap = new HashMap<String, Register>();

	public static void main(String[] args) {

		// MainView mainView = new MainView();
		// mainView.validate();

		// Scanner sc = new Scanner(System.in);
		// String instruction=sc.nextLine();

		System.out.println(Utils.getBinaryFromDecInString(4, 5));
		
		init();
		
		MainFrame mv = new MainFrame();
		
	}

	private static void init() {

		initRegister();

	}

	private static void initRegister() {
		addRegister("R0", Config.GPR_SIZE);
		addRegister("R1", Config.GPR_SIZE);
		addRegister("R2", Config.GPR_SIZE);
		addRegister("R3", Config.GPR_SIZE);

		addRegister("X1", Config.IndexReg_SIZE);
		addRegister("X2", Config.IndexReg_SIZE);
		addRegister("X3", Config.IndexReg_SIZE);
		
		addRegister("PC", Config.PC_SIZE);
		addRegister("CC", Config.CC_SIZE);
		addRegister("MAR", Config.MAR_SIZE);
		addRegister("MDR", Config.MDR_SIZE);
		addRegister("Carry", Config.MDR_SIZE);
		
		addRegister("ARR", Config.ARR_SIZE);
//		addRegister("MRR", Config.MDR_SIZE);
//		addRegister("SRR", Config.MDR_SIZE);
//		addRegister("CRR", Config.MDR_SIZE);
//		addRegister("LRR", Config.MDR_SIZE);
		

	}

	private static void addRegister(String name, int size){
		registerMap.put(name, new Register(name, new int[size], size));
		registerList.add(name);
	}
	
	public static Register getRegisterByName(String name){
		return registerMap.get(name);
	}
	
}
