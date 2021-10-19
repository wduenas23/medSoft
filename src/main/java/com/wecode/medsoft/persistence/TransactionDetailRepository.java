package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.TransactionDetail;

public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Integer>{

}
