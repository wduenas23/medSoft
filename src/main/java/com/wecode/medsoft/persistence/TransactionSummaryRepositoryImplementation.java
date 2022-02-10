package com.wecode.medsoft.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount;
import com.wecode.medsoft.entities.Transaction;
import com.wecode.medsoft.util.DateUtil;

@Service
public class TransactionSummaryRepositoryImplementation implements TransactionSummaryRepository{

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
	
	@Override
	public Double getTotalDailyIncome() {
		String sql=null;
		try {
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1";
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
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.startOfMonth() );
			q.setParameter("date2", DateUtil.endOfMonth());
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
		
	}



	@Override
	public List<Transaction> getDailyTransactions() {
		String sql=null;
		try {
			sql=" SELECT t from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1 order by t.txDate desc ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(new Date()));
			q.setParameter("date2", DateUtil.atEndOfDay(new Date()));
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public List<Transaction> getDailyTransactionsDateRange(Date start, Date end) {
		String sql=null;
		try {
			sql=" SELECT t from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1 order by t.txDate desc ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start));
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public Double getTotalIncomeByRange(Date start, Date end) {
		String sql=null;
		try {
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start) );
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public List<MedicalServiceCount> getServiceCount(Date start, Date end) {
		String sql=null;
		try {
			sql=" SELECT new com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount(s.svName, count(s.svName))"
					+ " from TransactionDetail td JOIN td.transaction t JOIN td.service s "
					+ " where t.txDate >= :date1 and "
					+ " t.txDate <= :date2 and t.transactionCategory.tcId=1 group by s.svName ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start));
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}
	
	

	

}
