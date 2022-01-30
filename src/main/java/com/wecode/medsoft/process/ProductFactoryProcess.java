package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wecode.medsoft.contracts.productfactory.ProductFactoryInfo;
import com.wecode.medsoft.entities.ProductFactory;
import com.wecode.medsoft.persistence.ProductFactoryRepository;

import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Slf4j
@Transactional
public class ProductFactoryProcess {

    
    private ProductFactoryRepository productFactoryRepository;
    
    @Autowired
    public ProductFactoryProcess(ProductFactoryRepository productFactoryRepository) {
        this.productFactoryRepository=productFactoryRepository;
    }
    
    public ResponseEntity<List<ProductFactoryInfo>> getAllProductFactory() {
    	List<ProductFactoryInfo> productFactoryInfos=new ArrayList<>();
    	ProductFactoryInfo pfi=null;
    	try {
			List<ProductFactory> pfs=(List<ProductFactory>) this.productFactoryRepository.findAll();
			for (ProductFactory pf : pfs) {
				pfi=buildProductFactoryInfo(pf);
				productFactoryInfos.add(pfi);
			}
			return new ResponseEntity<>(productFactoryInfos,HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in {}", e.getLocalizedMessage() + " " + e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    public ResponseEntity<ProductFactoryInfo> getProductFactoryById(Integer id){
    	ProductFactoryInfo pfi=null;
    	try {
			Optional<ProductFactory> pf=this.productFactoryRepository.findById(id);
			if(pf.isPresent()) {
				pfi=buildProductFactoryInfo(pf.get());
				return new ResponseEntity<>(pfi,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.info("Error in {}", e.getLocalizedMessage() + " " + e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    public ResponseEntity<ProductFactoryInfo> editProductFactory(ProductFactoryInfo productFactory){
    	ProductFactory newPf=null;
    	ProductFactoryInfo pfi=null;
    	try {
    		Optional<ProductFactory> pf=this.productFactoryRepository.findById(productFactory.getId());
    		
    		if(pf.isPresent()) {
    			newPf=pf.get();
			}else {
				newPf=new ProductFactory();
			}
    		newPf.setFtName(productFactory.getName());
    		newPf.setFtDescription(productFactory.getDescription());
    		newPf=this.productFactoryRepository.save(newPf);
    		pfi=buildProductFactoryInfo(newPf);
    		return new ResponseEntity<>(pfi,HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in {}", e.getLocalizedMessage() + " " + e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    }

	private ProductFactoryInfo buildProductFactoryInfo(ProductFactory pf) {
		ProductFactoryInfo pfi=new ProductFactoryInfo();
		pfi.setId(pf.getFtId());
		pfi.setName(pf.getFtName());
		pfi.setDescription(pf.getFtDescription());
		return pfi;
	}

    
    
}
