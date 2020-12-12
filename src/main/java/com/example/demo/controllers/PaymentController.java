package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.PaymentReposirory;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Transaction;
import com.example.demo.enumerations.Method;
import com.example.demo.services.PaymentService;
import com.example.demo.services.PaymentServiceImp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.el.parser.ParseException;


@RestController
@RequestMapping(path = "/payments") 
@CrossOrigin(origins = "*")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/hello")
	 public String hello() {
		 		return "hello";
		}

	@GetMapping("/findall")
	 public List<Transaction> getAllPayment() {
		 		return paymentService.getAllPayment();
		}
	@PostMapping(value = "/add")
	 Payment newPayment(@JsonProperty("method") String method, @JsonProperty("value") int value ){
		System.out.println(method+value);
	    return paymentService.savePayment(new Payment(value, Method.valueOf(method)));
	  }
	
	@GetMapping(value = "/{id}")
	public Payment findById(@PathVariable("id") Long id) {
        return paymentService.getPayment(id);
    }
}
