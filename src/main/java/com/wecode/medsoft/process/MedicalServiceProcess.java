package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceRequest;
import com.wecode.medsoft.contracts.medicalservices.MedicalServiceResponse;
import com.wecode.medsoft.entities.Service;
import com.wecode.medsoft.entities.ServicesCategory;
import com.wecode.medsoft.persistence.MedicalServicesRepository;
import com.wecode.medsoft.persistence.MedicalServicesRepositoryCustom;
import com.wecode.medsoft.persistence.ServiceCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Slf4j
@Transactional
public class MedicalServiceProcess {

    private MedicalServicesRepository medicalServicesRepository;
    private MedicalServicesRepositoryCustom customImplementation;
    private ServiceCategoryRepository categoryRepository;
    
    @Autowired
    public MedicalServiceProcess(MedicalServicesRepository medicalServicesRepository,ServiceCategoryRepository categoryRepository,MedicalServicesRepositoryCustom customImplementation) {
        this.medicalServicesRepository=medicalServicesRepository;
        this.categoryRepository=categoryRepository;
        this.customImplementation=customImplementation;
    }
    
    public List<MedicalServiceResponse> getAllMedicalServices(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
            List<Service> services=(List<Service>) medicalServicesRepository.findAllByOrderByServicesCategoryAsc();
            for (Service service : services) {
        		medicalService=new MedicalServiceResponse();
                medicalService.setId(service.getSvId());
                medicalService.setDescription(service.getSvName());
                medicalService.setCost(service.getSvCost());
                medicalService.setCategory(service.getServicesCategory().getScId());
                medicalService.setCategoryName(service.getServicesCategory().getScName());
                medicalService.setValid(service.getSvValid());
                medicalServices.add(medicalService);
            }
            return medicalServices;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
    }
    
    public List<MedicalServiceResponse> getAllActiveMedicalServices(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
            List<Service> services=(List<Service>) customImplementation.findActiveServices();
            for (Service service : services) {
        		medicalService=new MedicalServiceResponse();
                medicalService.setId(service.getSvId());
                medicalService.setDescription(service.getSvName());
                medicalService.setCost(service.getSvCost());
                medicalService.setCategory(service.getServicesCategory().getScId());
                medicalService.setCategoryName(service.getServicesCategory().getScName());
                medicalService.setValid(service.getSvValid());
                medicalServices.add(medicalService);
            }
            return medicalServices;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
    }
    
    public MedicalServiceResponse getMedicalServiceById(Integer id){
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
            Service service=(Service) medicalServicesRepository.findById(id).get();
        	if(service!=null) {
        		medicalService=new MedicalServiceResponse();
                medicalService.setId(service.getSvId());
                medicalService.setDescription(service.getSvName());
                medicalService.setCost(service.getSvCost());
                medicalService.setCategory(service.getServicesCategory().getScId());
                medicalService.setCategoryName(service.getServicesCategory().getScName());
                medicalService.setValid(service.getSvValid());
        	}

            return medicalService;
        } catch (Exception e) {
            log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
    }
    
    public List<MedicalServiceResponse> getAllMedicalServicesPromo(){
        List<MedicalServiceResponse> medicalServices=new ArrayList<>();
        MedicalServiceResponse medicalService=new MedicalServiceResponse();

        try {
            List<Service> services=(List<Service>) customImplementation.findActivePromotions();
            for (Service service : services) {
                medicalService=new MedicalServiceResponse();
                medicalService.setId(service.getSvId());
                medicalService.setDescription(service.getSvName());
                medicalService.setCost(service.getSvCost());
                medicalService.setCategory(service.getServicesCategory().getScId());
                medicalService.setValid(service.getSvValid());
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
    	Service newMedicalService=null;
    	ServicesCategory sc=null;
    	try {
    		Optional<Service> medicalService=medicalServicesRepository.findById(request.getId());
    		if(medicalService.isPresent()) {
    			newMedicalService=medicalService.get();
    			sc=this.categoryRepository.findById(request.getCategory()).get();
    			newMedicalService.setSvCost(request.getCost());
    			newMedicalService.setSvName(request.getDescription());
    			newMedicalService.setServicesCategory(sc);
    			newMedicalService.setSvValid(request.isValid());
    		}else {
    			newMedicalService=new Service();
    			newMedicalService.setSvCost(request.getCost());
    			newMedicalService.setSvName(request.getDescription());
    			newMedicalService.setSvDescription(request.getDescription());
    			newMedicalService.setSvValid(request.isValid());
    			sc=this.categoryRepository.findById(request.getCategory()).get();
    			newMedicalService.setServicesCategory(sc);
    		}
    		newMedicalService=medicalServicesRepository.save(newMedicalService);
    		medicalServiceResponse=new MedicalServiceResponse();
    		medicalServiceResponse.setCategory(newMedicalService.getServicesCategory().getScId());
    		medicalServiceResponse.setCost(newMedicalService.getSvCost());
    		medicalServiceResponse.setDescription(newMedicalService.getSvName());
    		medicalServiceResponse.setId(newMedicalService.getSvId());
    		medicalServiceResponse.setValid(newMedicalService.getSvValid());
    		return medicalServiceResponse;
		} catch (Exception e) {
			log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
		}
    }
}
