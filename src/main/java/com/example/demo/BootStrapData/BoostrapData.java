package com.example.demo.BootStrapData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Repositories.ClientRepository;
import com.example.demo.Repositories.InvoiceRepository;
import com.example.demo.Repositories.PaymentReposirory;
import com.example.demo.entities.Client;
import com.example.demo.entities.Invoice;
import com.example.demo.entities.Payment;
import com.example.demo.enumerations.Method;
import com.example.demo.enumerations.Status;
import com.github.javafaker.Faker;







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
		
		Faker faker = new Faker();

		System.out.println(faker.name().firstName());
		/**************************************/
		Client client1 = new Client(faker.name().firstName(),faker.name().lastName());
		Client client2 = new Client(faker.name().firstName(),faker.name().lastName());
		
		
		Payment p1 =new Payment(154.36,Method.VISA);
		Payment p2 =new Payment(13.02,Method.WIRE);
		Payment p3 =new Payment(50.32,Method.WIRE);
		Payment p4 =new Payment(40.7,Method.WIRE);



		Invoice i1 = new Invoice(320.45,Status.PAID,16);
		Invoice i2 = new Invoice(210.8,Status.CANCELED,9);
		Invoice i3 = new Invoice(20.5,Status.PAID,14);
		Invoice i4 = new Invoice(140.3,Status.PAID,42);

		

		
		
		p1.setInvoice(i4);
		i4.getPayments().add(p1);
		
		p2.setInvoice(i3);
		i3.getPayments().add(p2);
		
		p3.setInvoice(i2);
		i2.getPayments().add(p3);
		
		p4.setInvoice(i1);
		i1.getPayments().add(p4);
		
		client1.getInvoices().add(i1);client2.getInvoices().add(i3);
		client1.getInvoices().add(i2);client2.getInvoices().add(i4);
		
		i1.setClient(client1);i3.setClient(client2);
		i2.setClient(client1);i4.setClient(client2);

		clientRepository.save(client1);clientRepository.save(client2);

		
		invoiceRepository.save(i1);invoiceRepository.save(i3);
		invoiceRepository.save(i2);invoiceRepository.save(i4);

		paymentReposirory.save(p1);
		paymentReposirory.save(p2);
		paymentReposirory.save(p3);
		paymentReposirory.save(p4);	


	


	
	// Date dateStart =new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2020");
	// Date dateEnd =new SimpleDateFormat("dd/MM/yyyy").parse("14/12/2020");
	 /*System.out.println("Payments");

	List<Payment>list1 = paymentReposirory.getAllPayments();
	 for(Payment transaction : list1) {
         System.out.println(transaction.getId());
         System.out.println("*************");
     }*/
	 
	 System.out.println("\n\nnew test ");

		List<Object[]>list2 = invoiceRepository.getAllPaymentsInInvoice();
		for(Object[] obj  : list2) {
			for( Object objinside  : obj) {
	         System.out.println(objinside);

			 } 
			}


	         System.out.println("*************");
	     
		
	/****************************/
		
		
		   //Create typesafe ServiceRegistry object    
	   
	            

	System.out.println("started in Boostrap");
	System.out.println("Number of Invoices "+clientRepository.count());
	System.out.println("Number of Payments "+paymentReposirory.count());
	System.out.println("Number of Clients "+clientRepository.count());

	System.out.println("/*****test****/");
	
	
	 



	}

}
