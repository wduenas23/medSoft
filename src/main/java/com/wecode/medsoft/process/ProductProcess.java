package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.wecode.medsoft.contracts.product.ProductPojo;
import com.wecode.medsoft.entities.Product;
import com.wecode.medsoft.entities.ProductCategory;
import com.wecode.medsoft.persistence.ProductRepository;
import com.wecode.medsoft.persistence.ProductRepositoryCustomImplementation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductProcess {

	private ProductRepository productRepository;
	private ProductRepositoryCustomImplementation productRepositoryCustom;
	
	@Autowired
	public ProductProcess(ProductRepository productRepository,ProductRepositoryCustomImplementation productRepositoryCustom) {
		this.productRepository=productRepository;
		this.productRepositoryCustom=productRepositoryCustom;
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
	
	public ResponseEntity<ProductPojo> editProduct(ProductPojo newProduct){
		ProductPojo prd=null;
		Product newPrd=null;
		try {
			Optional<Product> product=this.productRepository.findById(newProduct.getId());
			if(product.isPresent()) {
				newPrd=product.get();
			}else {
				newPrd=new Product();
				ProductCategory cat=new ProductCategory();
				cat.setPcId(newProduct.getCategoryId());
				newPrd.setProductCategory(cat);
			}
			newPrd.setPrdCode(newProduct.getPrdCode());
			newPrd.setPrdCost(newProduct.getCost());
			newPrd.setPrdDescription(newProduct.getDescription());
			newPrd.setPrdInventory(newProduct.getInventory());
			newPrd.setPrdName(newProduct.getName());
			newPrd.setPrdSellingPrice(newProduct.getSellingPrice());
			newPrd.setPrdValid(newProduct.isValid());
			newPrd=this.productRepository.save(newPrd);
			prd=new ProductPojo();
			prd.setId(newPrd.getPrdId());
			prd.setName(newPrd.getPrdName());
			prd.setPrdCode(newPrd.getPrdCode());
			prd.setDescription(newPrd.getPrdDescription());
			prd.setCategoryId(newPrd.getProductCategory().getPcId());
			prd.setCategoryName(newPrd.getProductCategory().getPcName());
			prd.setCost(newPrd.getPrdCost());
			prd.setSellingPrice(newPrd.getPrdSellingPrice());
			prd.setInventory(newPrd.getPrdInventory());
			prd.setValid(newPrd.isPrdValid());
			return new ResponseEntity<>(prd,HttpStatus.OK);
			
		}catch (DataIntegrityViolationException e) {
			log.error("Duplicated Key",e.getMessage());
			return new ResponseEntity<>(prd,HttpStatus.CONFLICT);
		} catch (UnexpectedRollbackException e) {
			log.error("Duplicated Key",e.getMessage());
			return new ResponseEntity<>(prd,HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Error Savinc product",e.getMessage());
			return new ResponseEntity<>(prd,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Boolean> validateProductName(String name) {
		try {
			List<Product> products=this.productRepositoryCustom.findPrdByName(name);
			if(products!=null && products.size()>0) {
				return new ResponseEntity<>(true,HttpStatus.OK);
			}
			return new ResponseEntity<>(false,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error validateProductName",e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Boolean> validateProductByCode(String code) {
		try {
			List<Product> products=this.productRepositoryCustom.findPrdByCode(code);
			if(products!=null && products.size()>0) {
				return new ResponseEntity<>(true,HttpStatus.OK);
			}
			return new ResponseEntity<>(false,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error validateProductName",e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
