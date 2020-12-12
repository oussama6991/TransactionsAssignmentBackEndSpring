package com.example.demo.BootStrapData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Repositories.ClientRepository;
import com.example.demo.Repositories.InvoiceRepository;
import com.example.demo.Repositories.PaymentReposirory;
import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Invoice;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Transaction;
import com.example.demo.enumerations.Method;
import com.example.demo.enumerations.Status;



@Component
public class BoostrapData implements CommandLineRunner{
	

	private final PaymentReposirory paymentReposirory;
	private final InvoiceRepository invoiceRepository ;
	private final ClientRepository clientRepository;

	public BoostrapData(PaymentReposirory paymentReposirory,InvoiceRepository invoiceRepository,ClientRepository clientRepository) {
		super();

		this.paymentReposirory=paymentReposirory;
		this.invoiceRepository=invoiceRepository;
		this.clientRepository=clientRepository;
	}


	@Override
	public void run(String... args) throws Exception {

		Payment p1 =new Payment(250,Method.VISA);
		Invoice i1 = new Invoice(123,Status.PAID,336);
		Client client1 = new Client("oussama","mahraoui");
		
		
		p1.getInvoices().add(i1);
		i1.getPayments().add(p1);
		
		client1.getInvoices().add(i1);
		i1.setClient(client1);
	
		



	paymentReposirory.save(p1);
	invoiceRepository.save(i1);
	clientRepository.save(client1);
	System.out.println("started in Boostrap");
	System.out.println("Number of payments "+paymentReposirory.count());


	}

}
