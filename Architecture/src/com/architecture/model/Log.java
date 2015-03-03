package com.architecture.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.architecture.view.ConsolePanel;

public class Log {
	
	private static Map logMap = new LinkedHashMap<String, String>();

	public static void d(String msg){
		// TODO to be improved
		System.out.println(msg);
		ConsolePanel.writeToConsole(msg);
		logMap.put("Debug", msg);
	}
	
	public static void e(String msg){
		// TODO to be improved
		System.out.println(msg);
		ConsolePanel.writeToConsole(msg);
		logMap.put("Error", msg);
	}
}
