package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.enumerations.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
//@PrimaryKeyJoinColumn(name = "id")
public class Invoice extends Transaction{

	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.ORDINAL)
    private Status status ;
	private long number;
	
		@OneToMany(mappedBy="invoice")
		//@JoinColumn(name="invoice_fk")
	    @JsonBackReference
	private Set<Payment> payments=new HashSet<>();
	
		   @ManyToOne
		   @JoinColumn(name="client_id",nullable=false)
		   private Client client ;
	
	public Invoice() {
		super();
	}
	public Invoice(double value,Status status,long number) {
		super(value);
		this.status = status;
		this.number = number;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
