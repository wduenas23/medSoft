package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.servicecategory.ServicesCategoryResponse;
import com.wecode.medsoft.entities.ServicesCategory;
import com.wecode.medsoft.persistence.ServiceCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceCategoryProcess {

	private ServiceCategoryRepository categoryRepository;
	
	public ServiceCategoryProcess(ServiceCategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}
	
	public List<ServicesCategoryResponse> getAllServiceCategories(){
		List<ServicesCategoryResponse> categoryResponses= new ArrayList<>(); 
		ServicesCategoryResponse categoryResponse=null;
		try {
			List<ServicesCategory> servCatList= (List<ServicesCategory>)categoryRepository.findAll();
			for (ServicesCategory servicesCategory : servCatList) {
				categoryResponse=new ServicesCategoryResponse();
				categoryResponse.setId(servicesCategory.getScId());
				categoryResponse.setName(servicesCategory.getScName());
				categoryResponses.add(categoryResponse);
			}
			return categoryResponses;
		} catch (Exception e) {
			 log.info("Error in ", e.getLocalizedMessage() + " " + e.getMessage());
	         throw e;
		}
	}
	
}
