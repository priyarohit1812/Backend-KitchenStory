package com.kitchenstory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitchenstory.model.Payment;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {

}
