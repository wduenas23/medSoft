package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Transaction;
import com.wecode.medsoft.entities.TransactionDetailSale;

public interface TransactionDetailSaleRepository extends CrudRepository<TransactionDetailSale, Integer>{

	List<TransactionDetailSale> findByTransaction(Transaction transaction);
}
