package com.wecode.medsoft.persistence;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.ServicesCategory;

public interface ServiceCategoryRepository extends CrudRepository<ServicesCategory, Integer> {

	ServicesCategory findByScName(String scName);
}
