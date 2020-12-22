package com.example.demo.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Invoice;

@Repository
public interface InvoiceRepository extends TransactionRepository  {
	@Query(value = "FROM Invoice i")
	public List<Invoice> getAllInvoices();
	
	@Query(value = "from Invoice i where dateCreated BETWEEN :startDate AND :endDate")
	public List<Invoice> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	//@Query(value = "select p.id FROM  Invoice i  inner join Payment p WHERE  i.id = :idinvoice ",nativeQuery = true)
	//public List<Long> getAllPaymentsInInvoice(@Param("idinvoice")long idinvoice );//:idinvoice
	
	@Query(value = "select i.id , i.status , i.number , sum(t.value) AS total " + 
					"FROM  Invoice i " + 
					"join transaction t\r\n" + 
						"on i.id=t.id\r\n" + 
					"join payment p\r\n" + 
						"on  i.id=p.invoice_id\r\n" + 
					"group by i.id"
	
			,nativeQuery = true)
	public List<Object[]> getAllPaymentsInInvoice( );//:idinvoice
	
//	
}

