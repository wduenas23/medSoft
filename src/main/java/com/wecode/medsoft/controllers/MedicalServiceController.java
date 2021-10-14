package com.wecode.medsoft.controllers;

import java.util.List;

import com.wecode.medsoft.contracts.medicalServices.MedicalServiceResponse;
import com.wecode.medsoft.process.MedicalServiceProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-services")
public class MedicalServiceController {
    
    private MedicalServiceProcess medicalServiceProcess;

    @Autowired
    public MedicalServiceController(MedicalServiceProcess medicalServiceProcess) {
        this.medicalServiceProcess=medicalServiceProcess;
    }

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<MedicalServiceResponse>> getAllMedicalServices(){
        return new ResponseEntity<>(medicalServiceProcess.getAllMedicalServices(),HttpStatus.OK);
    }

}
