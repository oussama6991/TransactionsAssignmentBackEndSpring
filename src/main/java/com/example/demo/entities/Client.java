package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("invoices")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	private String fristName;
	private String lastName;
	
    @OneToMany(mappedBy="client")
    @JsonBackReference
    private Set<Invoice> invoices= new HashSet<>();
    
	public Client() {
	}
	public Client(String fristName, String lastName) {
		super();
		this.fristName = fristName;
		this.lastName = lastName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
	
	
}
