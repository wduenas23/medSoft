package com.wecode.medsoft.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.entities.Product;

@Service
public class ProductRepositoryCustomImplementation implements ProductRepositoryCustom{
	
	@PersistenceContext // or even @Autowired
    private EntityManager entityManager;
	
	@Override
	public List<Product> findPrdByName(String prdNameParam, Integer prdId){
		try {
			
			StringBuilder sql=new StringBuilder();
			sql.append(" Select p from Product p where p.prdName = :name ");
			if(prdId != null) {
				sql.append("and p.prdId <> :prdId");
			}
					
			Query q=this.entityManager.createQuery(sql.toString(),Product.class);
			q.setParameter("name",prdNameParam);
			if(prdId != null) {
				q.setParameter("prdId", prdId);
			}
			
			List<Product> products= q.getResultList();
			return products;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public List<Product> findPrdByCode(String prdCode, Integer prdId){
		try {
			
			StringBuilder sql=new StringBuilder();
			sql.append(" Select p from Product p where p.prdCode = :prdCode ");
			if(prdId != null) {
				sql.append("and p.prdId <> :prdId");
			}
			Query q=this.entityManager.createQuery(sql.toString(),Product.class);
			q.setParameter("prdCode",prdCode);
			if(prdId != null) {
			q.setParameter("prdId", prdId);	
			}
			List<Product> products= q.getResultList();
			return products;
		} catch (Exception e) {
			throw e;
		}
	}
}
