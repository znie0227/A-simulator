package com.architecture.util;

/**
 * @author BaoBao_iOZ
 *
 * A general methods collection.
 */
public class Utils {
	
	public static String getBinaryFromDec(int val, int size){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<size;i++){
			sb.append( val%2);
			val = val/2;
		}
		return sb.reverse().toString();
	}
	public static String getDecimalFromBin(int val){
		return null;
	}
}
