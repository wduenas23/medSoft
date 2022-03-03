package com.wecode.medsoft.persistence;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount;
import com.wecode.medsoft.entities.Service;
import com.wecode.medsoft.entities.ServicesCategory;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MedicalServicesRepository extends CrudRepository<Service,Integer> {
    
	List<Service> findByServicesCategory(ServicesCategory servicesCategory);
	List<Service> findAllByOrderByServicesCategoryAsc();
	
	@Query("SELECT new com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount(s.svName, count(s.svName))"
			+ "					 from TransactionDetail td JOIN td.transaction t JOIN td.service s "
			+ "					 where t.txDate >= ?1 and "
			+ "					t.txDate <= ?2 and t.txDeleteFlag is null group by s.svName ")
	public List<MedicalServiceCount> getServiceCountByRange(Date start,Date end);
	
	
	
}
