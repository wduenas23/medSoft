package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Transaction;
import com.wecode.medsoft.entities.TransactionDetail;

public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Integer>{

	List<TransactionDetail> findByTransaction(Transaction transaction);
}
