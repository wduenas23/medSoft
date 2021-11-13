package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.product.ProductCategoryPojo;
import com.wecode.medsoft.entities.ProductCategory;
import com.wecode.medsoft.persistence.ProductCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductCategoryProcess {
	
	private ProductCategoryRepository categoryRepository;
	
	@Autowired
	public ProductCategoryProcess(ProductCategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}
	
	public ResponseEntity<List<ProductCategoryPojo>> getAllProductCategory(){
		List<ProductCategoryPojo> categoryPojos=new ArrayList<>();
		ProductCategoryPojo categoryPojo=null;
		try {
			List<ProductCategory> categories=(List<ProductCategory>)this.categoryRepository.findAll();
			for (ProductCategory productCategory : categories) {
				categoryPojo=new ProductCategoryPojo();
				categoryPojo.setId(productCategory.getPcId());
				categoryPojo.setName(productCategory.getPcName());
				categoryPojos.add(categoryPojo);
			}
			return new ResponseEntity<>(categoryPojos,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error getting all product categories",e.getMessage());
			return new ResponseEntity<>(categoryPojos,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
