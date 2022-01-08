package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.PatientContact;


public interface PatientContactRepository extends CrudRepository<PatientContact,Integer>{
    
}
