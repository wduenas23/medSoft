package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Patient;


public interface PatientRepository extends CrudRepository<Patient,Integer>{
    List<Patient> findByPtIdentification(String ptIdentification);
    List<Patient> findByPtName(String ptName);
    List<Patient> findByPtLastName(String ptLastName);
    @Query("select p from Patient p join p.patientContact pc where pc.pcPhoneNumber = :phoneNumber")
    List<Patient> findPatientByPhoneNumber(String phoneNumber);
}
