package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Invoice;
import com.example.demo.entities.Transaction;

public interface InvoiceService {
	Invoice saveInvoice(Invoice invoice);
	Invoice updateInvoice(Invoice invoice);
	Invoice getInvoice(long id);
	List<Transaction> getAllPayment();

}
