package com.wecode.medsoft.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount;
import com.wecode.medsoft.contracts.product.ProductCount;
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
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1 and t.txDeleteFlag is null ";
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
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=1  and t.txDeleteFlag is null";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.startOfMonth() );
			q.setParameter("date2", DateUtil.endOfMonth());
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
		
	}



	@Override
	public List<Transaction> getDailyTransactions(Integer type) {
		String sql=null;
		try {
			sql=" SELECT t from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=:type and t.txDeleteFlag is null order by t.txDate desc ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(new Date()));
			q.setParameter("date2", DateUtil.atEndOfDay(new Date()));
			q.setParameter("type", type);
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public List<Transaction> getDailyTransactionsDateRange(Date start, Date end, Integer type) {
		String sql=null;
		try {
			sql=" SELECT t from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=:type  and t.txDeleteFlag is null order by t.txDate desc ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start));
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			q.setParameter("type", type);
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public Double getTotalIncomeByRange(Date start, Date end,Integer type) {
		String sql=null;
		try {
			sql=" SELECT sum(t.txTransTotal) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=:type and t.txDeleteFlag is null";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start) );
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			q.setParameter("type", type);
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Double getTotalComissionByRange(Date start, Date end,Integer type) {
		String sql=null;
		try {
			sql=" SELECT sum(t.txSaleComission) from Transaction t where t.txDate >= :date1 and t.txDate <= :date2 and t.transactionCategory.tcId=:type and t.txDeleteFlag is null";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start) );
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			q.setParameter("type", type);
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
					+ " t.txDate <= :date2 and t.transactionCategory.tcId=1  and t.txDeleteFlag is null group by s.svName ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("date1", DateUtil.atStartOfDay(start));
			q.setParameter("date2", DateUtil.atEndOfDay(end));
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	public List<ProductCount> getProductCountByRange(Date start,Date end){
		try {
			String sql="SELECT new com.wecode.medsoft.contracts.product.ProductCount(p.prdName, count(p.prdName))"
					+ "					 from TransactionDetailSale tds JOIN tds.transaction t JOIN tds.product p "
					+ "					 where t.txDate >= :start and "
					+ "					t.txDate <= :end  and t.txDeleteFlag is null group by p.prdName ";
			
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("start", DateUtil.atStartOfDay(start));
			q.setParameter("end", DateUtil.atEndOfDay(end));
			return q.getResultList();	
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public int deleteTransaction(Integer id) {
		String sql=null;
		try {
			sql=" Update Transaction t set t.txDeleteFlag=1, t.txDeleteDate=:tDate where t.txId=:txId ";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("tDate", DateUtil.atStartOfDay(new Date()));
			q.setParameter("txId", id);
			return q.executeUpdate();	
		} catch (Exception e) {
			throw e;
		}
		
	}
	

	

}
