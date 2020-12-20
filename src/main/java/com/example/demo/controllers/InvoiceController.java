package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Invoice;
import com.example.demo.enumerations.Status;
import com.example.demo.services.InvoiceService;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@RequestMapping(path = "/invoices") 
@CrossOrigin(origins = "*")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService ;
	
	
	@GetMapping("/hello")
	 public String hello() {
		 		return "hello";
		}
	
	@GetMapping("/findall")
	 public List<Invoice> getAllnvoice() {
		 		return invoiceService.getAllnvoice();
		}
	@GetMapping("/total")
	 public  List<Object[]> getAllTotalPaymentInInvoice( ) {
				System.out.println("ok");
		 		return invoiceService.getInvoicesWithTotalPayment();
		}
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Invoice newInvoice(@JsonProperty("status") String status, @JsonProperty("number") long number ,@JsonProperty("value") float value){
		System.out.println(status);
	    return invoiceService.saveInvoice(new Invoice(value, Status.valueOf(status), number));
	  }
	
	@GetMapping(value = "/{id}")
	public Invoice findById(@PathVariable("id") Long id) {
		Invoice invoice =null;
		try {
			invoice =invoiceService.getInvoice(id);
		  }catch (NoSuchElementException e) {
		        System.out.println("Invoice Not Found");
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found");
		        }
       return invoice ;
   }
	
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	
	@RequestMapping(value = "/between", method = RequestMethod.POST, produces={"application/json","application/xml"}, consumes={"application/json","application/xml"})
	 //public @ResponseBody String betweenDates( @RequestParam(name = "dateStart")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate  dateStart ){
	 public  List<Invoice> betweenDates( @RequestBody Map<String, String> json ){
	System.out.println(json.entrySet());
	try {
		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(json.get("dateStart"));
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(json.get("dateEnd"));

			System.out.println(dateEnd );
			System.out.println(dateStart);

			 return invoiceService.getByDateInvoice(dateStart, dateEnd);

	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;

	//System.out.println("param are " + allParams.entrySet());
		//System.out.println(dateEnd);
		// List<Transaction>
	    //return paymentService.savePayment(new Payment(value, Method.valueOf(method)));
	  }
	
	/*@RequestMapping(value = "")
	public void findById(@RequestParam("name")String name) {
		System.out.println(name);
      //  return paymentService.getPayment(id);
    }*/
		/*@GetMapping(value = "/idinvoicebypament/{id}")
		public Long idinvoicebypament(@PathVariable("id") String id_payment) {
			return invoiceService.getIdInvoiceByPayment(Long.parseLong(id_payment));
			 	
					}*/
}

		/*@RequestMapping(value="/test1", produces={"application/json","application/xml"}, consumes={"application/json","application/xml"})
		    public ModelAndView     redirectWithUsingRedirectView(@RequestBody Map<String, String> json,ModelMap model) {
		        System.out.println("test1 ");
				System.out.println(json.entrySet());
				Payment p =new Payment(Double.parseDouble((String)json.get("value")),  Method.valueOf(json.get("method") ));
				Invoice i =invoiceService.getInvoice((Long)Long.parseLong( json.get("invoice_id")));
				p.setInvoice(i);
				i.getPayments().add(p);
				invoiceService.saveInvoice(i);
		        model.addAttribute("newPäyment", p);
		        return new ModelAndView("redirect:/payments/test2", model);
		    }
		}*/

