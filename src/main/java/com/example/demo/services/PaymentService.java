package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Payment;
import com.example.demo.entities.Transaction;

public interface PaymentService {
	
	Payment savePayment(Payment payment);
	Payment updatePayment(Payment payment);
	Payment getPayment(long id);
	List<Transaction> getAllPayment();

}
