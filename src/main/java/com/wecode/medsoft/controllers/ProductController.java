package com.wecode.medsoft.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.product.ProductCategoryPojo;
import com.wecode.medsoft.contracts.product.ProductFactoryPojo;
import com.wecode.medsoft.contracts.product.ProductPojo;
import com.wecode.medsoft.process.ProductCategoryProcess;
import com.wecode.medsoft.process.ProductProcess;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductProcess process;
	private ProductCategoryProcess categoryProcess;
	
	public ProductController(ProductProcess process,ProductCategoryProcess categoryProcess) {
		this.process=process;
		this.categoryProcess=categoryProcess;
	}
	
	@GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<ProductPojo>> getAllProducts(){
		return process.getAllProducts();
	}
	
	@GetMapping("/byId")
    @CrossOrigin
    public ResponseEntity<ProductPojo> getProductById(@RequestParam Integer id){
		return process.getProductById(id);
	}
	
	@GetMapping("/category/all")
    @CrossOrigin
    public ResponseEntity<List<ProductCategoryPojo>> getAllProductCategories(){
		return categoryProcess.getAllProductCategory();
	}
	
	@GetMapping("/factory/all")
    @CrossOrigin
    public ResponseEntity<List<ProductFactoryPojo>> getAll(){
		return process.getAllProductFactory();
	}
	
	@GetMapping("/validateName")
    @CrossOrigin
    public ResponseEntity<Boolean> validateName(@RequestParam String productName){
		return process.validateProductName(productName);
	}
	
	@GetMapping("/validateCode")
    @CrossOrigin
    public ResponseEntity<Boolean> validateCode(@RequestParam String productCode){
		return process.validateProductByCode(productCode);
	}
	
	@PostMapping("/edit")
    @CrossOrigin
    public ResponseEntity<ProductPojo> editProduct(@RequestBody ProductPojo product){
		return process.editProduct(product);
	}
}
