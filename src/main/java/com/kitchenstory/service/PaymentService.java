package com.kitchenstory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.Payment;
import com.kitchenstory.repository.IPaymentRepository;

@Service(value = "paymentService")
public class PaymentService implements IPaymentService {
	@Autowired
	private IPaymentRepository paymentRepository;

	@Override
	public Payment savePayment(Payment payment) {
		return this.paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentById(Long paymentId) {
		Optional<Payment> optPay = this.paymentRepository.findById(paymentId);
		if (optPay != null && optPay.isPresent()) {
			return optPay.get();
		}
		return null;
	}

}
