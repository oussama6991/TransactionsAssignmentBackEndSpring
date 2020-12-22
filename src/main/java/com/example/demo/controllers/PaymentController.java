package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entities.Invoice;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Transaction;
import com.example.demo.enumerations.Method;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.PaymentService;



@RestController
@RequestMapping(path = "/payments") 
@CrossOrigin()
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	@Autowired
	InvoiceService invoiceService ;
	
	public PaymentController(PaymentService paymentService, InvoiceService invoiceService) {
		this.paymentService = paymentService;
		this.invoiceService = invoiceService;
	}

	@GetMapping("/hello")
	 public String hello() {
		 		return "hello";
		}

	@GetMapping("/findall")
	 public List<Payment> getAllPayment() {
		 for(Payment pl : paymentService.getAllPayments()) {
	         System.out.println(pl.getId());
	         System.out.println("*************");
	     }
		 		return paymentService.getAllPayments();
		}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 public @ResponseBody Payment newPayment(@RequestBody Map<String, String> json ){
		System.out.println(json.entrySet());
		Payment p =new Payment(Float.parseFloat(json.get("value")),  Method.valueOf(json.get("method") ));
		Long id_invoice=Long.parseLong(json.get("invoice_id"));
		System.out.println(id_invoice);
		Invoice i = invoiceService.getInvoice(id_invoice);
		p.setInvoice(i);
		i.getPayments().add(p);
		invoiceService.saveInvoice(i);
		paymentService.savePayment(p);
		System.out.println("good");
		return paymentService.savePayment(p);
	  }

	@RequestMapping(value = "/between", method = RequestMethod.POST, produces={"application/json","application/xml"}, consumes={"application/json","application/xml"})
	 //public @ResponseBody String betweenDates( @RequestParam(name = "dateStart")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate  dateStart ){
	 public  List<Transaction> betweenDates( @RequestBody Map<String, String> json ){
	System.out.println(json.entrySet());
	try {
		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(json.get("dateStart"));
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(json.get("dateEnd"));

			System.out.println(dateEnd );
			System.out.println(dateStart);
			for(Transaction transaction : paymentService.getByDatePayment(dateStart, dateEnd)) {
		         System.out.println(transaction.getId());
		         System.out.println("*************");
		     }
			 return paymentService.getByDatePayment(dateStart, dateEnd);

	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
	  }
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST,        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	 public  String edit(@RequestBody Map<String, String> json){
		System.out.println(json.get("id"));
		Payment payment =  paymentService.getPayment( Long.parseLong(json.get("id")));
		String s=json.get("dateCreated");
		Long id_invoice=Long.parseLong(json.get("invoice_id"));
		Invoice i ,oldInvoice;
		System.out.println(id_invoice);
		try {
			Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(s);
				payment.setDateCreated(newDate);
				payment.setMethod(Method.valueOf(json.get("method")));
				payment.setValue(Float.parseFloat(json.get("value")) );
				System.out.println(json.get("value"));
				if(id_invoice!=payment.getInvoice().getId()) {
					i = invoiceService.getInvoice(id_invoice);
					oldInvoice=payment.getInvoice();
					oldInvoice.getPayments().remove(payment);
					i.getPayments().add(payment);
					payment.setInvoice(i);
					invoiceService.saveInvoice(oldInvoice);
					invoiceService.saveInvoice(i);
				}
				paymentService.savePayment(payment); 
		} catch (ParseException e) {
			System.out.println("Erorr in parsing");		}
		catch (NoSuchElementException e) {
			System.out.println("null value not allowed");		}
		System.out.println("ok");
		return "ok";
	  } 
	
	@GetMapping(value = "/{id}")
	public Map<String, Object> findById(@PathVariable("id") Long id) {
		 Map<String, Object> dataMap=null;
		 Payment p=null;
		 try {
			p= paymentService.getPayment(id);
			dataMap=new HashMap<String, Object>();
			dataMap.put("payment", p);
			dataMap.put("id_invoice", p.getInvoice().getId());
		 }catch (NoSuchElementException e) {
        System.out.println("Payment Not Found");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment Not Found");
        }
		

        return dataMap;
    }

	
}
