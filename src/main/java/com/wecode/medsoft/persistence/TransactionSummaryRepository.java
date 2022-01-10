package com.wecode.medsoft.persistence;

import java.util.Date;
import java.util.List;

import com.wecode.medsoft.entities.Transaction;

public interface TransactionSummaryRepository {
	
	public Double getTotalDailyIncome();
	public Double getTotalMonthlyIncome();
	public List<Transaction> getDailyTransactions();
	public List<Transaction> getDailyTransactionsDateRange(Date start, Date end);
	public Double getTotalIncomeByRange(Date start, Date end);

}
