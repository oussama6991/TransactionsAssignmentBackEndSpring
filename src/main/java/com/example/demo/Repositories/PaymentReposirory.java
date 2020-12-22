package com.example.demo.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Invoice;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Transaction;
@Repository
public interface PaymentReposirory  extends TransactionRepository  {
	@Query(value = "FROM Payment p")
	public List<Payment> getAllPayments();
	

	
	@Query(value = "from Payment t where dateCreated BETWEEN :startDate AND :endDate")
	public List<Transaction> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	


}
