package com.architecture.model;

import com.architecture.util.Config;
import com.architecture.util.Utils;

public class GeneralPurposeRegister extends Register {

	
	
	
	public GeneralPurposeRegister(String name, int[] data){
		super(name,data,Config.GPR_SIZE);
	}

	public void write(int in[]){
		//TODO :exception
		for(int i=0;i<size;i++){
			if(i<in.length)
				data[size - i - 1] = in[in.length-1-i];
			else
				data[size-i-1] = 0;
		}
	}
	
	public int getDecValue(){
		Utils.getDecimalFromBin(data);
	}
	
	public int[] getBinValue(){
		return data;
	}
	
	
	
	
}
