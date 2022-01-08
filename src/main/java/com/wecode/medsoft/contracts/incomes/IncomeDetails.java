package com.wecode.medsoft.contracts.incomes;

import java.util.List;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;

import lombok.Data;

@Data
public class IncomeDetails {

	private String name;
    private String lastName;
    private String phone;
    private Double subTotalClient;
    private Double txTotal;
    private String paymentType;
    private Integer txId;
    private List<MedicalServiceResponse> services;
}
