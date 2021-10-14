package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import com.wecode.medsoft.contracts.medicalServices.MedicalServiceResponse;
import org.springframework.stereotype.Service;

@Service
public class MedicalServiceProcess {
    
    public List<MedicalServiceResponse> getAllMedicalServices(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();
        medicalService.setId(1);
        medicalService.setDescription("Consulta");
        medicalService.setCost( 35.0);
        medicalServices.add(medicalService);
        medicalService=new MedicalServiceResponse();
        medicalService.setId(2);
        medicalService.setDescription("Limpieza Facial");
        medicalService.setCost( 50.0);
        medicalServices.add(medicalService);
        medicalService=new MedicalServiceResponse();
        medicalService.setId(3);
        medicalService.setDescription("Rinomodelación con hilos");
        medicalService.setCost( 800.0);
        medicalServices.add(medicalService);
        return medicalServices;
    }
}
