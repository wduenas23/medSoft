package com.wecode.medsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.patient.PatientInfo;
import com.wecode.medsoft.process.PatientProcess;


@RestController
@RequestMapping("/patient")
public class PatientController {

	private PatientProcess patientProcess;
	
	@Autowired
	public PatientController(PatientProcess patientProcess) {
		this.patientProcess=patientProcess;
	}
	
	@GetMapping("/byId")
    @CrossOrigin
	public ResponseEntity<PatientInfo> getPatientById(@RequestParam String id) {
		return patientProcess.getPatientById(id);
	}
	
	@GetMapping("/byPhoneNumber")
    @CrossOrigin
	public ResponseEntity<PatientInfo> getPatientByPhoneNumber(@RequestParam String phoneNumber) {
		return patientProcess.getPatientByPhoneNumber(phoneNumber);
	}
	
}
