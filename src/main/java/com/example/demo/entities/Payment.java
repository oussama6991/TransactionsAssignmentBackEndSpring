package com.example.demo.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.enumerations.Method;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("invoices")

public class Payment extends Transaction{

	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.ORDINAL)
    private Method  method;
	
	//@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	   @ManyToOne
	   @JoinColumn(name="invoice_id",nullable=false)
	 @JsonBackReference
	private Invoice invoice ;
	
	
	public Payment() {
		super();
	}
	public Payment(double value,Method method) {
		super(value);
		this.method = method;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	@Override
	public String toString() {
		return "Payment [method=" + method + ", invoices=" + invoice + ", getId()=" + getId() + ", getDateCreated()="
				+ getDateCreated() + ", getValue()=" + getValue() + "]";
	}

	

	
	
}
