package com.kitchenstory.enums;

public enum PaymentStatus {
	Pending(0),
	Authorized(1),
	Captured(2),
	AuthorizationFailed(-1),
	CaptureFailed(-2);
	
	public final int status;
	
	private PaymentStatus(int status) {
		this.status = status;
	}
	

}
