package com.wecode.medsoft.controllers;

import java.util.List;

import com.wecode.medsoft.contracts.formOfPayments.FormOfPaymentResponse;
import com.wecode.medsoft.process.FormOfPaymentProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forms-of-payment")
public class FormOfPaymentController {
    
    private FormOfPaymentProcess formOfPaymentProcess;

    @Autowired
    public FormOfPaymentController(FormOfPaymentProcess formOfPaymentProcess) {
        this.formOfPaymentProcess=formOfPaymentProcess;
    }

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<FormOfPaymentResponse>> getAll(){
        return new ResponseEntity<>(formOfPaymentProcess.getAll(),HttpStatus.OK);
    }
}
