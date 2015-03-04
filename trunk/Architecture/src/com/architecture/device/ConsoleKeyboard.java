package com.architecture.device;

public class ConsoleKeyboard extends Device {
	
	private static StringBuilder sb = new StringBuilder();

	private int status=Device.STATUS_IDLE;
	
	@Override
	public void write(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int read() {
		return sb.charAt(sb.length()-1);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		sb=new StringBuilder();
	}

	@Override
	public int getDeviceStatus() {
		return status;
	}

}
