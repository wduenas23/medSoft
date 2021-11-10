package com.wecode.medsoft.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wecode.medsoft.entities.Service;

@org.springframework.stereotype.Service
public class MedicalServiceRepositoryImplCustom implements MedicalServicesRepositoryCustom {

	 @PersistenceContext // or even @Autowired
	    private EntityManager entityManager;
	
	@Override
	public List<Service> findActivePromotions() {
		String sql=null;
		try {
			sql=" SELECT s from Service s "
					+ "	where s.svValid= true and s.servicesCategory.scName='PROMOCIONES' ";
			Query q=this.entityManager.createQuery(sql);
			return (List<Service>) q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Service> findActiveServices() {
		String sql=null;
		try {
			sql=" SELECT s from Service s "
					+ "	where s.svValid= true ";
			Query q=this.entityManager.createQuery(sql);
			return (List<Service>) q.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

}
