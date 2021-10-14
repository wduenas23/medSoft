package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;

import com.wecode.medsoft.contracts.formOfPayments.formOfPaymentResponse;

import org.springframework.stereotype.Service;

@Service
public class FormOfPaymentProcess {
    
    public List<formOfPaymentResponse> getAll(){
        List<formOfPaymentResponse> formOfPaymentResponses=new ArrayList<>();
        formOfPaymentResponse formOfPaymentResponse=new formOfPaymentResponse();
        formOfPaymentResponse.setId(1);
        formOfPaymentResponse.setDescription("Efectivo");
        formOfPaymentResponses.add(formOfPaymentResponse);
        formOfPaymentResponse=new formOfPaymentResponse();
        formOfPaymentResponse.setId(2);
        formOfPaymentResponse.setDescription("Tarjeta");
        formOfPaymentResponses.add(formOfPaymentResponse);
        formOfPaymentResponse=new formOfPaymentResponse();
        formOfPaymentResponse.setId(3);
        formOfPaymentResponse.setDescription("Transferencia");
        formOfPaymentResponses.add(formOfPaymentResponse);
        formOfPaymentResponse=new formOfPaymentResponse();
        formOfPaymentResponse.setId(4);
        formOfPaymentResponse.setDescription("BitCoins");
        formOfPaymentResponses.add(formOfPaymentResponse);

        return formOfPaymentResponses;
    }
}
