package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Authorization;


public interface AuthorizationRepository extends CrudRepository<Authorization,Integer>{

}
