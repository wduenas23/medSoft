package com.wecode.medsoft.contracts.incomes;

import java.util.List;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.contracts.patient.PatientInfo;

import lombok.Data;

@Data
public class ResponseIncome {
    
    private String message;
    private String code;
    private String name;
    private String lastName;
    private String phone;
    private Double subTotalClient;
    private Double txTotal;
    private String paymentType;
    private Integer paymentId;
    private Integer txId;
    private Double discount;
    private List<MedicalServiceResponse> services;
    private List<PaymentDetails> paymentDetails;
    private PatientInfo patient;
    
}
