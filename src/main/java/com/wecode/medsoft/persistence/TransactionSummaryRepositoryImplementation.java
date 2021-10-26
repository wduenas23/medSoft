package com.wecode.medsoft.persistence;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.util.DateUtil;

@Service
public class TransactionSummaryRepositoryImplementation implements TransactionSummaryRepository{

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
	
	@Override
	public Double getTotalDailyIncome() {
		String sql=null;
		try {
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate between :date1 and :date2";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(new Date()));
			q.setParameter("date2", DateUtil.atEndOfDay(new Date()));
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	@Override
	public Double getTotalMonthlyIncome() {
		String sql=null;
		try {
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate between :date1 and :date2";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.startOfMonth() );
			q.setParameter("date2", DateUtil.endOfMonth());
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	

	

}
