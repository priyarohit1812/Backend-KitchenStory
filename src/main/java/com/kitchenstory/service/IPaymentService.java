package com.kitchenstory.service;

import com.kitchenstory.model.Payment;

public interface IPaymentService {
	public Payment savePayment(Payment payment);
	public Payment getPaymentById(Long paymentId);
	
}
