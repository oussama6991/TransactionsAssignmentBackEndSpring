package com.example.demo.Repositories;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Transaction;

@NoRepositoryBean
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
