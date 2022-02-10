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
import com.wecode.medsoft.entities.ProductFactory;
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
			List<Product> products=(List<Product>)productRepository.findAllByOrderByProductFactoryAsc();
			for (Product product : products) {
				prd=new ProductPojo();
				prd=buildNewPrdPojo(prd,product);
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
				prd=buildNewPrdPojo(prd,product.get());
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
			}
			ProductFactory pf=new ProductFactory();
			pf.setFtId(newProduct.getDrogueriaId());
			newPrd.setProductFactory(pf);
			newPrd.setPrdCode(newProduct.getPrdCode());
			newPrd.setPrdCost(newProduct.getCost());
			newPrd.setPrdDescription(newProduct.getDescription());
			newPrd.setPrdInventory(newProduct.getInventory());
			newPrd.setPrdName(newProduct.getName());
			newPrd.setPrdSellingPrice(newProduct.getSellingPrice());
			newPrd.setPrdActive(newProduct.isValid());
			newPrd.setPrdPromotionPrice(newProduct.getPromotionPrice());
			newPrd.setPrdExpiration(newProduct.getExpiDate());
			newPrd.setPrdLot(newProduct.getPrdLot());
			newPrd=this.productRepository.save(newPrd);
			prd=new ProductPojo();
			prd=buildNewPrdPojo(prd,newPrd);
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
			log.error("Error Savinc product: {}",e.getMessage());
			return new ResponseEntity<>(prd,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private ProductPojo buildNewPrdPojo(ProductPojo prd,Product newPrd) {
		prd.setId(newPrd.getPrdId());
		prd.setName(newPrd.getPrdName());
		prd.setPrdCode(newPrd.getPrdCode());
		prd.setDescription(newPrd.getPrdDescription());
		prd.setCost(newPrd.getPrdCost());
		prd.setSellingPrice(newPrd.getPrdSellingPrice());
		prd.setInventory(newPrd.getPrdInventory());
		prd.setValid(newPrd.getPrdActive());
		prd.setPromotionPrice(newPrd.getPrdPromotionPrice());
		prd.setDrogueriaId(newPrd.getProductFactory().getFtId());
		prd.setDrogueriaName(newPrd.getProductFactory().getFtName());
		prd.setPromotionPrice(newPrd.getPrdPromotionPrice());
		prd.setPrdLot(newPrd.getPrdLot());
		prd.setExpiDate(newPrd.getPrdExpiration());
		return prd;
	}

	public ResponseEntity<Boolean> validateProductName(String name, Integer idProducto) {
		try {
			List<Product> products=this.productRepositoryCustom.findPrdByName(name,idProducto);
			if(products!=null && products.size()>0) {
				return new ResponseEntity<>(true,HttpStatus.OK);
			}
			return new ResponseEntity<>(false,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error validateProductName",e.getMessage());
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Boolean> validateProductByCode(String code,Integer idProducto) {
		try {
			List<Product> products=this.productRepositoryCustom.findPrdByCode(code,idProducto);
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
