package com.wecode.medsoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.servicecategory.ServicesCategoryResponse;
import com.wecode.medsoft.process.ServiceCategoryProcess;

@RestController
@RequestMapping("/service-category")
public class ServiceCategoryController {

	private ServiceCategoryProcess categoryProcess;
	
	@Autowired
	public ServiceCategoryController(ServiceCategoryProcess categoryProcess) {
		this.categoryProcess=categoryProcess;
	}
	
	@GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<ServicesCategoryResponse>> getAll(){
		return new ResponseEntity<>(this.categoryProcess.getAllServiceCategories(),HttpStatus.OK);
	}
}
