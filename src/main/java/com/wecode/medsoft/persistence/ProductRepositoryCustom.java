package com.wecode.medsoft.persistence;

import java.util.List;

import com.wecode.medsoft.entities.Product;

public interface ProductRepositoryCustom {
	List<Product> findPrdByName(String prdName);
	List<Product> findPrdByCode(String prdCode);
}
