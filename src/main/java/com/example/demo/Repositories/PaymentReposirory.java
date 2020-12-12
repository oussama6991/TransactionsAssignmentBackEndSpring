package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Payment;
@Repository
public interface PaymentReposirory  extends TransactionRepository  {

}
