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
import com.wecode.medsoft.contracts.product.ProductPojo;
import com.wecode.medsoft.contracts.productfactory.ProductFactoryInfo;
import com.wecode.medsoft.process.ProductCategoryProcess;
import com.wecode.medsoft.process.ProductFactoryProcess;
import com.wecode.medsoft.process.ProductProcess;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductProcess process;
	private ProductCategoryProcess categoryProcess;
	private ProductFactoryProcess productFactoryProcess;
	
	public ProductController(ProductProcess process,ProductCategoryProcess categoryProcess,ProductFactoryProcess productFactoryProcess) {
		this.process=process;
		this.categoryProcess=categoryProcess;
		this.productFactoryProcess=productFactoryProcess;
	}
	
	@GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<ProductPojo>> getAllProducts(){
		return process.getAllProducts();
	}
	
	@GetMapping("/allAvailable")
    @CrossOrigin
    public ResponseEntity<List<ProductPojo>> getAllAvailableProducts(){
		return process.getAllAvailableProducts();
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
    public ResponseEntity<List<ProductFactoryInfo>> getAllProductFactory(){
		return productFactoryProcess.getAllProductFactory();
	}
	
	@GetMapping("/factory/byId")
    @CrossOrigin
    public ResponseEntity<ProductFactoryInfo> getProductFactoryById(@RequestParam Integer id){
		return productFactoryProcess.getProductFactoryById(id);
	}
	
	@PostMapping("/factory/edit")
    @CrossOrigin
    public ResponseEntity<ProductFactoryInfo> editProductFactory(@RequestBody ProductFactoryInfo productFactory){
		return productFactoryProcess.editProductFactory(productFactory);
	}
	
	
	@GetMapping("/validateName")
    @CrossOrigin
    public ResponseEntity<Boolean> validateName(@RequestParam String productName,@RequestParam Integer productId){
		return process.validateProductName(productName,productId);
	}
	
	@GetMapping("/validateCode")
    @CrossOrigin
    public ResponseEntity<Boolean> validateCode(@RequestParam String productCode,@RequestParam Integer productId){
		return process.validateProductByCode(productCode,productId);
	}
	
	@PostMapping("/edit")
    @CrossOrigin
    public ResponseEntity<ProductPojo> editProduct(@RequestBody ProductPojo product){
		return process.editProduct(product);
	}
}
