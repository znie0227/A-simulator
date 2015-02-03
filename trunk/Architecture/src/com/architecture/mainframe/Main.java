package com.architecture.mainframe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.architecture.model.Memory;
import com.architecture.model.Register;
import com.architecture.util.Utils;

public class Main {

	private static List<String> registerList = new ArrayList<String>();
	private static HashMap<String, Register> registerMap = new HashMap<String, Register>();

	public static void main(String[] args) {

		// MainView mainView = new MainView();
		// mainView.validate();

		// Scanner sc = new Scanner(System.in);
		// String instruction=sc.nextLine();

		System.out.println(Utils.getBinaryFromDec(4, 5));
		
		init();
		
	}

	private static void init() {

		initRegister();

	}

	private static void initRegister() {
		addRegister("R0", 16);
		addRegister("R1", 16);
		addRegister("R2", 16);
		addRegister("R3", 16);

		addRegister("X1", 16);
		addRegister("X2", 16);
		addRegister("X3", 16);

	}

	private static void addRegister(String name, int size){
		registerMap.put(name, new Register(name, new int[size], size));
		registerList.add(name);
	}
	
	public static Register getRegisterByName(String name){
		return registerMap.get(name);
	}
}
