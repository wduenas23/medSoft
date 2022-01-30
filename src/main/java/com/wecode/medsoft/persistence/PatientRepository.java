package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Patient;


public interface PatientRepository extends CrudRepository<Patient,Integer>{
    List<Patient> findByPtIdentification(String ptIdentification);
    List<Patient> findByPtName(String ptName);
    List<Patient> findByPtLastName(String ptLastName);
}
