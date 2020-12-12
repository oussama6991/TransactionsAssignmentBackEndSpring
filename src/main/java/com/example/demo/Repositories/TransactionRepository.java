package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.entities.Transaction;

@NoRepositoryBean
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
