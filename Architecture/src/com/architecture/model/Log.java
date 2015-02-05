package com.architecture.model;

import com.architecture.view.ConsolePanel;

public class Log {

	public static void d(String msg){
		// TODO to be improved
		System.out.println(msg);
		ConsolePanel.writeToConsole(msg);
	}
}
