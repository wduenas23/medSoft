package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceRequest;
import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.entities.Service;
import com.wecode.medsoft.entities.ServicesCategory;
import com.wecode.medsoft.persistence.MedicalServicesRepository;
import com.wecode.medsoft.persistence.ServiceCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Slf4j
@Transactional
public class MedicalServiceProcess {

    private MedicalServicesRepository medicalServicesRepository;
    private ServiceCategoryRepository categoryRepository;
    private final static String PROMOCATEGORYNAME="PROMOCIONES";

    @Autowired
    public MedicalServiceProcess(MedicalServicesRepository medicalServicesRepository,ServiceCategoryRepository categoryRepository) {
        this.medicalServicesRepository=medicalServicesRepository;
        this.categoryRepository=categoryRepository;
    }
    
    public List<MedicalServiceResponse> getAllMedicalServices(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
            List<Service> services=(List<Service>) medicalServicesRepository.findAll();
            for (Service service : services) {
            	if(!service.getServicesCategory().getScName().equals("PROMOCIONES")) {
            		medicalService=new MedicalServiceResponse();
                    medicalService.setId(service.getSvId());
                    medicalService.setDescription(service.getSvName());
                    medicalService.setCost(service.getSvCost());
                    medicalService.setCategory(service.getServicesCategory().getScId());
                    medicalServices.add(medicalService);
            	}
            }
            return medicalServices;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
    }
    
    public List<MedicalServiceResponse> getAllMedicalServicesPromo(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
        	ServicesCategory sv=categoryRepository.findByScName(PROMOCATEGORYNAME);
            List<Service> services=(List<Service>) medicalServicesRepository.findByServicesCategory(sv);
            for (Service service : services) {
                medicalService=new MedicalServiceResponse();
                medicalService.setId(service.getSvId());
                medicalService.setDescription(service.getSvName());
                medicalService.setCost(service.getSvCost());
                medicalService.setCategory(service.getServicesCategory().getScId());
                medicalServices.add(medicalService);
            }
            return medicalServices;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
    }
    
    public MedicalServiceResponse editMedicalService(MedicalServiceRequest request) {
    	MedicalServiceResponse medicalServiceResponse=null;
    	try {
    		Service medicalService=medicalServicesRepository.findById(request.getId()).get();
    		medicalService.setSvCost(request.getCost());
    		medicalService.setSvName(request.getDescription());
    		medicalService=medicalServicesRepository.save(medicalService);
    		medicalServiceResponse=new MedicalServiceResponse();
    		medicalServiceResponse.setCategory(medicalService.getServicesCategory().getScId());
    		medicalServiceResponse.setCost(medicalService.getSvCost());
    		medicalServiceResponse.setDescription(medicalService.getSvName());
    		medicalServiceResponse.setId(medicalService.getSvId());
    		return medicalServiceResponse;
		} catch (Exception e) {
			log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
		}
    }
}
