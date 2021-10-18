package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import com.wecode.medsoft.contracts.medicalServices.MedicalServiceResponse;
import com.wecode.medsoft.entities.Service;
import com.wecode.medsoft.persistence.MedicalServicesRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Slf4j
public class MedicalServiceProcess {

    private MedicalServicesRepository medicalServicesRepository;

    @Autowired
    public MedicalServiceProcess(MedicalServicesRepository medicalServicesRepository) {
        this.medicalServicesRepository=medicalServicesRepository;
    }
    
    public List<MedicalServiceResponse> getAllMedicalServices(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
            List<Service> services=(List<Service>) medicalServicesRepository.findAll();
            for (Service service : services) {
                medicalService=new MedicalServiceResponse();
                medicalService.setId(service.getSvId());
                medicalService.setDescription(service.getSvName());
                medicalService.setCost(service.getSvCost());
                medicalServices.add(medicalService);
            }
            return medicalServices;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
    }
}
