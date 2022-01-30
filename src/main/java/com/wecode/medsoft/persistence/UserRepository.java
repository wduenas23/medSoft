package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.User;


public interface UserRepository extends CrudRepository<User,Integer>{

}
