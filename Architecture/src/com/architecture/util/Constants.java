package com.architecture.util;


/**
 * @author BaoBao_iOZ
 * 
 * This class is used to store constants.
 *
 */
public class Constants {

	
	public static final int CPU_STATE_IDLE=1000;
	public static final int CPU_STATE_RUNNING=1001;
	public static final int CPU_STATE_FINISHED=1002;
	
	public static final int OCTAL_10=8;
	
	public static float FLOAT_MAX = Utils.getFloatValueFrom16bit(Utils.getIntArrayFromString("111111110000000000"));
	public static float FLOAT_MIN = Utils.getFloatValueFrom16bit(Utils.getIntArrayFromString("011111110000000000"));
}
