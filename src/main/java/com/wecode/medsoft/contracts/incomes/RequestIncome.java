package com.wecode.medsoft.contracts.incomes;

import java.util.Date;
import java.util.List;

import com.wecode.medsoft.contracts.formofpayments.FormOfPaymentResponse;
import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.contracts.patient.PatientInfo;

import lombok.Data;

@Data
public class RequestIncome {
 
	private List<MedicalServiceResponse> services;
    private FormOfPaymentResponse formOfPayment;
    private List<Totals> totals;
    private Date txDate;
    private Double discount;
    private Integer id;
    private List<PaymentDetails> paymentDetails;
    private PatientInfo patient;
}