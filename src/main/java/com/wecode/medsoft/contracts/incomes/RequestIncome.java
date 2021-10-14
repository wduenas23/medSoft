package com.wecode.medsoft.contracts.incomes;

import java.util.List;

import com.wecode.medsoft.contracts.medicalServices.MedicalServiceResponse;

import lombok.Data;

@Data
public class RequestIncome {
 
    private String nombres;
    private String apellidos;
    private String fechaServicio;
    private List<MedicalServiceResponse> servicios;
    private Summary totales;
}