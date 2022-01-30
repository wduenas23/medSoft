package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Role;


public interface RoleRepository extends CrudRepository<Role,Integer>{

}
