package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Repositories.InvoiceRepository;
import com.example.demo.entities.Invoice;
import com.example.demo.entities.Transaction;

public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	private InvoiceRepository invoiceRepository;

	public InvoiceRepository getInvoiceRepository() {
		return invoiceRepository;
	}

	public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public Invoice saveInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice getInvoice(long id) {
		return (Invoice) invoiceRepository.findById(id).get();
	}

	@Override
	public List<Transaction> getAllPayment() {
		return  invoiceRepository.findAll();

	}

	
	
}
