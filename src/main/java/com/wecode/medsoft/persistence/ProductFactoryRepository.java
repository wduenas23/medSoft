package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.ProductFactory;


public interface ProductFactoryRepository extends CrudRepository<ProductFactory,Integer>{
    
	
}
