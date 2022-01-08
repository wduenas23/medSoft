package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.ContactType;


public interface ContactTypeRepository extends CrudRepository<ContactType,Integer>{
    
	List<ContactType> findByCtName(String ctName);
}
