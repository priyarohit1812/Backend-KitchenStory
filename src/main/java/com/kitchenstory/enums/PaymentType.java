package com.kitchenstory.enums;

public enum PaymentType {
	CreditCard(1), CashOnDelivery(2);

	public final int type;
	
	private PaymentType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
}
