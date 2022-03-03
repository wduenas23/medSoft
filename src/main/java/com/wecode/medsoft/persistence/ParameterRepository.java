package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Parameter;


public interface ParameterRepository extends CrudRepository<Parameter,String>{

}
