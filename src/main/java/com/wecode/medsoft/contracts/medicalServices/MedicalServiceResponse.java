package com.wecode.medsoft.contracts.medicalServices;

import lombok.Data;

@Data
public class MedicalServiceResponse {

    private int id;
    private String description;
    private Double cost;
}
