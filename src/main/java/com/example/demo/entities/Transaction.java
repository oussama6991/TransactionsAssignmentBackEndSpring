package com.example.demo.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction   implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @JsonManagedReference

	private Long id;
	private Date dateCreated;
	private float Value;
	
	public Transaction() {
	}
	
	public Transaction(float value) {
		this.Value = value;
		this.dateCreated=new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public float getValue() {
		return Value;
	}

	public void setValue(float value) {
		Value = value;
	}
	
	
	
	
	
	
	
	
	

}
