package br.com.erudio.helper;

public class MathUtils {
	
	public static Double sum(String numberOne, String numberTwo) {
		return Utils.convertToDouble(numberOne) + Utils.convertToDouble(numberTwo);
	}
	
	public static Double sub(String numberOne, String numberTwo) {
		return Utils.convertToDouble(numberOne) - Utils.convertToDouble(numberTwo);
	}
	
	public static Double mut(String numberOne, String numberTwo) {
		return Utils.convertToDouble(numberOne) * Utils.convertToDouble(numberTwo);
	}
	
	public static Double div(String numberOne, String numberTwo) {
		return Utils.convertToDouble(numberOne) / Utils.convertToDouble(numberTwo);
	}
	
	public static Double med(String numberOne, String numberTwo) {
		return (Utils.convertToDouble(numberOne) + Utils.convertToDouble(numberTwo)) / 2;
	}
	
	public static Double raz(String numberOne) {
		return Math.sqrt(Utils.convertToDouble(numberOne));
	}

}
