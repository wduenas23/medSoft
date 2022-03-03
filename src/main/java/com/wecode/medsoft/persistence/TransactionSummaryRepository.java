package com.wecode.medsoft.persistence;

import java.util.Date;
import java.util.List;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount;
import com.wecode.medsoft.entities.Transaction;

public interface TransactionSummaryRepository {
	
	public Double getTotalDailyIncome();
	public Double getTotalMonthlyIncome();
	public List<Transaction> getDailyTransactions(Integer type);
	public List<Transaction> getDailyTransactionsDateRange(Date start, Date end,Integer type);
	public Double getTotalIncomeByRange(Date start, Date end, Integer type);
	public Double getTotalComissionByRange(Date start, Date end, Integer type);
	public List<MedicalServiceCount> getServiceCount(Date start, Date end);
	public int deleteTransaction(Integer id);
}
