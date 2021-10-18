package com.wecode.medsoft.persistence;

import com.wecode.medsoft.entities.Service;

import org.springframework.data.repository.CrudRepository;

public interface MedicalServicesRepository extends CrudRepository<Service,Integer> {
    
}
