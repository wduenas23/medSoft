package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Role;
import com.wecode.medsoft.entities.RoleAuthorization;


public interface RoleAuthorizationRepository extends CrudRepository<RoleAuthorization,Integer>{
	
	List<RoleAuthorization> findByRole(Role role);

}
