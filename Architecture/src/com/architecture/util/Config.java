package com.architecture.util;

/**
 * @author BaoBao_iOZ
 *
 *	Configuration of the simulator
 */
public class Config {

	public static final int GPR_SIZE=16;
	public static final int IndexReg_SIZE=12;
	public static final int WORD_SIZE=16;
	public static int MEMORY_SIZE=2048;
	public static int NUMBER_OF_BITS_OF_MEMORY_SIZE=11; // TODO automatic retrieve from MEMORY_SIZE
	public static final int CACHELINE_SIZE=16;
	public static final int CACHELINE_CAPACITY=8;
	
	public static final int PC_SIZE=12;
	public static final int IR_SIZE=16;
	public static final int MAR_SIZE=16;
	public static final int MDR_SIZE=16;
	public static final int CC_SIZE=4;
	
	public static final int ARR_SIZE=16;
	public static final int MRR_SIZE=32;
	public static final int SRR_SIZE=16;
//	public static final int CRR_SIZE=16;
	public static final int LRR_SIZE=16;
	
	public static final int FR_SIZE=16;
	
}
