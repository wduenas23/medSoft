package com.wecode.medsoft.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wecode.medsoft.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findAllByOrderByProductFactoryAsc();
	
	@Query("SELECT p from Product p where p.prdActive = 'yes' and p.prdInventory > 0 ")
	public List<Product> getAllAvailableProdcts();
}
