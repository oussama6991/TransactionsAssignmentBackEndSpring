package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.InvoiceRepository;
import com.example.demo.entities.Invoice;
@Service
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
	public List<Invoice> getAllnvoice() {
		return  invoiceRepository.getAllInvoices();

	}

	@Override
	public List<Invoice> getByDateInvoice(Date startDate, Date endDate) {
				return  invoiceRepository.getAllBetweenDates(startDate, endDate);

	}

	public List<Object[]> getInvoicesWithTotalPayment(){
		return invoiceRepository.getAllPaymentsInInvoice();
		
	}
	
}
