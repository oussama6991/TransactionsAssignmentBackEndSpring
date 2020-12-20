package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.PaymentReposirory;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Transaction;
@Service
public class PaymentServiceImp implements PaymentService {
	@Autowired
	private  PaymentReposirory paymentReposirory;
	
	public PaymentReposirory getPaymentReposirory() {
		return paymentReposirory;
	}

	@Override
	public Payment savePayment(Payment payment) {
		return paymentReposirory.save(payment);
	}

	@Override
	public Payment updatePayment(Payment payment) {
		return paymentReposirory.save(payment);

	}

	@Override
	public Payment getPayment(long id) {
		return (Payment) paymentReposirory.findById(id).get();

	}

	@Override
	public List<Payment> getAllPayments() {
		return  paymentReposirory.getAllPayments();

	}
	@Override
	public List<Payment> getAllTransaction() {
		return  paymentReposirory.getAllPayments();

	}

	@Override
	public List<Transaction> getByDatePayment(Date startDate ,Date endDate) {
		return paymentReposirory.getAllBetweenDates(startDate, endDate) ;
	}


	
	

}
