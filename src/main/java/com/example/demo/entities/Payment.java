package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.example.demo.enumerations.Method;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonIgnoreProperties("invoices")
public class Payment extends Transaction{

	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.ORDINAL)
    private Method  method;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="invoice_payment",joinColumns = @JoinColumn(name="payment_id"),
	inverseJoinColumns =  @JoinColumn(name="invoice_id"))
	private Set<Invoice> invoices =new HashSet<>();
	
	public Payment() {
		super();
	}
	public Payment(float value,Method method) {
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
	public Set<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	
	
}
