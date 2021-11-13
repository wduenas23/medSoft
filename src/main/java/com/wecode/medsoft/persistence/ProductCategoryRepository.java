package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer>{

}
