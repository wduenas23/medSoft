package com.wecode.medsoft.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.entities.Product;

@Service
public class ProductRepositoryCustomImplementation implements ProductRepositoryCustom{
	
	@PersistenceContext // or even @Autowired
    private EntityManager entityManager;
	
	@Override
	public List<Product> findPrdByName(String prdNameParam){
		try {
			TypedQuery<Product> q=this.entityManager
					.createQuery(" Select p from Product p where p.prdName = :name ",Product.class)
					.setParameter("name",prdNameParam);
			List<Product> products= q.getResultList();
			return products;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public List<Product> findPrdByCode(String prdCode){
		try {
			TypedQuery<Product> q=this.entityManager
					.createQuery(" Select p from Product p where p.prdCode = :prdCode ",Product.class)
					.setParameter("prdCode",prdCode);
			List<Product> products= q.getResultList();
			return products;
		} catch (Exception e) {
			throw e;
		}
	}
}
