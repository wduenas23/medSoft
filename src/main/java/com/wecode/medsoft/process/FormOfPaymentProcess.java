package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;

import com.wecode.medsoft.contracts.formOfPayments.FormOfPaymentResponse;
import com.wecode.medsoft.entities.PaymentType;
import com.wecode.medsoft.persistence.FormOfPaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FormOfPaymentProcess {

    private FormOfPaymentRepository formOfPaymentRepo;

    @Autowired
    public FormOfPaymentProcess(FormOfPaymentRepository formOfPaymentRepo) {
        this.formOfPaymentRepo=formOfPaymentRepo;
    }
    
    public List<FormOfPaymentResponse> getAll(){
        List<FormOfPaymentResponse> formOfPaymentResponses=new ArrayList<>();
        FormOfPaymentResponse formOfPaymentResponse=null;
        try {
            log.info("Calling find all");
            List<PaymentType> paymentTypes=(List<PaymentType>) formOfPaymentRepo.findAll();    
            for (PaymentType paymentType : paymentTypes) {
                formOfPaymentResponse=new FormOfPaymentResponse();
                formOfPaymentResponse.setId(paymentType.getPtId());
                formOfPaymentResponse.setDescription(paymentType.getPtName());     
                formOfPaymentResponses.add(formOfPaymentResponse);
            }
            return formOfPaymentResponses;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
        
    }
}
