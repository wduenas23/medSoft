package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
