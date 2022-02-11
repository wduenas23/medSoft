package com.wecode.medsoft.contracts.incomes;

import java.util.Date;
import java.util.List;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.contracts.patient.PatientInfo;

import lombok.Data;

@Data
public class ResponseIncome {
    
    private String message;
    private String code;
    private Double txSubTotal;
    private Double subTotalClient;
    private Double txTotal;
    private String paymentType;
    private Integer paymentId;
    private Integer txId;
    private Double discount;
    private Double discountTotal;
    private Double comission;
    private List<MedicalServiceResponse> services;
    private List<PaymentDetails> paymentDetails;
    private PatientInfo patient;
    private Date date;
    
}
