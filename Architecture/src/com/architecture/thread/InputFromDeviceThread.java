package com.architecture.thread;

import com.architecture.app.CPU;

public class InputFromDeviceThread extends Thread implements Runnable {
	
	private static InputFromDeviceThread inputFromDeviceThread;

	public static int keyEvent=-1;
	
	@Override
	public void run() {

		while (keyEvent==-1) {
			
		}
		CPU.keyEvent=keyEvent;
		keyEvent=-1;
	}

	public static InputFromDeviceThread getInstance(){
		if (inputFromDeviceThread==null) {
			inputFromDeviceThread=new InputFromDeviceThread();
		}
		return inputFromDeviceThread;
	}
	
	public static void setKeyEvent(int ascii){
		keyEvent=ascii;
		
	}
	
}
