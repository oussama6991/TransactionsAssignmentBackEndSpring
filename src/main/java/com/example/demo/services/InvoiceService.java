package com.example.demo.services;

import java.util.Date;
import java.util.List;

import com.example.demo.entities.Invoice;

public interface InvoiceService {
	Invoice saveInvoice(Invoice invoice);
	Invoice updateInvoice(Invoice invoice);
	Invoice getInvoice(long id);
	List<Invoice> getAllnvoice();
	List<Invoice> getByDateInvoice(Date startDate ,Date endDate) ;
	List<Object[]> getInvoicesWithTotalPayment();


}
