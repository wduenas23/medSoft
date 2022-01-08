package com.wecode.medsoft.contracts.patient;

import lombok.Data;

@Data
public class PatientInfo {

	private String name;
	private String lastName;
	private String phone;
	private String address;
	private String identification;
	private String birthday;
	private int id;
}
