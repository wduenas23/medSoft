package com.wecode.medsoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceRequest;
import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.process.MedicalServiceProcess;

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
    
    @GetMapping("/byId")
    @CrossOrigin
    public ResponseEntity<MedicalServiceResponse> getMedicalServiceById(@RequestParam Integer id){
        return new ResponseEntity<>(medicalServiceProcess.getMedicalServiceById(id),HttpStatus.OK);
    }
    
    @GetMapping("/promotions")
    @CrossOrigin
    public ResponseEntity<List<MedicalServiceResponse>> getAllMedicalServicesPromos(){
        return new ResponseEntity<>(medicalServiceProcess.getAllMedicalServicesPromo(),HttpStatus.OK);
    }
    
    @PostMapping("/edit")
    @CrossOrigin
    public ResponseEntity<MedicalServiceResponse> editMedicalService(@RequestBody MedicalServiceRequest medRequest){
        return new ResponseEntity<>(medicalServiceProcess.editMedicalService(medRequest),HttpStatus.OK);
    }


}
