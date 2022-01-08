package com.wecode.medsoft.contracts.medicalservices;

import lombok.Data;

@Data
public class MedicalServiceResponse {

    private int id;
    private String description;
    private Double cost;
    private int category;
    private String categoryName;
    private boolean valid;
    private int trxdId;
}
