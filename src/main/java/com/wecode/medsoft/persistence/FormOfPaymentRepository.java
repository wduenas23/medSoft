package com.wecode.medsoft.persistence;

import com.wecode.medsoft.entities.PaymentType;

import org.springframework.data.repository.CrudRepository;


public interface FormOfPaymentRepository extends CrudRepository<PaymentType,Integer>{
    
}
