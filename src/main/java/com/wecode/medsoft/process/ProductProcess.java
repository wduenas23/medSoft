package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.product.ProductPojo;
import com.wecode.medsoft.entities.Product;
import com.wecode.medsoft.persistence.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductProcess {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductProcess(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	public ResponseEntity<List<ProductPojo>> getAllProducts(){
		List<ProductPojo> productsPojo=new ArrayList<>();
		ProductPojo prd=null;
		try {
			List<Product> products=(List<Product>)productRepository.findAll();
			for (Product product : products) {
				prd=new ProductPojo();
				prd.setId(product.getPrdId());
				prd.setName(product.getPrdName());
				prd.setPrdCode(product.getPrdCode());
				prd.setDescription(product.getPrdDescription());
				prd.setCategoryId(product.getProductCategory().getPcId());
				prd.setCategoryName(product.getProductCategory().getPcName());
				prd.setCost(product.getPrdCost());
				prd.setSellingPrice(product.getPrdSellingPrice());
				prd.setInventory(product.getPrdInventory());
				prd.setValid(product.isPrdValid());
				productsPojo.add(prd);
			}
			return new ResponseEntity<>(productsPojo,HttpStatus.OK);		
		} catch (Exception e) {
			log.error("Error getting all products",e.getMessage());
			return new ResponseEntity<>(productsPojo,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<ProductPojo> getProductById(Integer id){
		ProductPojo prd=null;
		try {
			Optional<Product> product=this.productRepository.findById(id);
			if(product.isPresent()) {
				prd=new ProductPojo();
				prd.setId(product.get().getPrdId());
				prd.setName(product.get().getPrdName());
				prd.setPrdCode(product.get().getPrdCode());
				prd.setDescription(product.get().getPrdDescription());
				prd.setCategoryId(product.get().getProductCategory().getPcId());
				prd.setCategoryName(product.get().getProductCategory().getPcName());
				prd.setCost(product.get().getPrdCost());
				prd.setSellingPrice(product.get().getPrdSellingPrice());
				prd.setInventory(product.get().getPrdInventory());
				prd.setValid(product.get().isPrdValid());
				return new ResponseEntity<>(prd,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(prd,HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("Error getting product by Id",e.getMessage());
			return new ResponseEntity<>(prd,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
