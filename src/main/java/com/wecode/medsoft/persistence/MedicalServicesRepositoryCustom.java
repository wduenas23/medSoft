package com.wecode.medsoft.persistence;

import com.wecode.medsoft.entities.Service;
import com.wecode.medsoft.entities.ServicesCategory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MedicalServicesRepositoryCustom {
    
	List<Service> findActivePromotions();
	List<Service> findActiveServices();
	
	
}
