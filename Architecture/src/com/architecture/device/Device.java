package com.architecture.device;

public abstract class Device {
	
	abstract public void write(String msg);
	abstract public int read();
	abstract public void reset();
	abstract public int getDeviceStatus();
	
	public static final int STATUS_IDLE=1000;
	public static final int STATUS_IN_USE=1001;
	
	public static Device getDevice(int devid){
		switch (devid) {
		case 0:
			return new ConsoleKeyboard();
		case 1:
			return new ConsolePrinter();
		case 2:
			return new CardReader();
		case 3:
		}
		return null;
	}
}
