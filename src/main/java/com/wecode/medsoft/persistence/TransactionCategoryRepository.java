package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.TransactionCategory;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Integer>{

		List<TransactionCategory> findByTcName(String tcName);
}
