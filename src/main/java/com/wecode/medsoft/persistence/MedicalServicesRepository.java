package com.wecode.medsoft.persistence;

import com.wecode.medsoft.entities.Service;
import com.wecode.medsoft.entities.ServicesCategory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MedicalServicesRepository extends CrudRepository<Service,Integer> {
    
	List<Service> findByServicesCategory(ServicesCategory servicesCategory);
	
}
