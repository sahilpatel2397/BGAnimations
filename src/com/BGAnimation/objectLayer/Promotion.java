package com.BGAnimation.objectLayer;

public class Promotion {
	private String promocode;
	private int percentOff;
	
	public Promotion(String promocode, int percentOff) {
		this.promocode = promocode;
		this.percentOff = percentOff;
	}
	
	public Promotion() { }
	
	public String getPromocode() {
		return promocode;
	}
	
	public int getPercentOff() {
		return percentOff;
	}
	
	public void setPromocode(String code) {
		this.promocode = code;
	}
	
	public void setPercentOff(int percentOff) {
		this.percentOff = percentOff;
	}
}
