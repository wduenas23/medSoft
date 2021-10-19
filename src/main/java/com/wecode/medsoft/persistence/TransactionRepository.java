package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

}
