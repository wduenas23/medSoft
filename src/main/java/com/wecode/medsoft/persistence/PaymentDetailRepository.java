package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.PaymentDetail;
import com.wecode.medsoft.entities.Transaction;

public interface PaymentDetailRepository extends CrudRepository<PaymentDetail, Integer>{
	
	List<PaymentDetail> findByTransaction(Transaction transaction);
	
}
