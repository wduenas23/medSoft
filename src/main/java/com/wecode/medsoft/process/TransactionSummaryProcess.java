package com.wecode.medsoft.process;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.summary.SummaryTransaction;
import com.wecode.medsoft.persistence.TransactionSummaryRepositoryImplementation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionSummaryProcess {

	private TransactionSummaryRepositoryImplementation implementation;
	
	@Autowired
	public TransactionSummaryProcess(TransactionSummaryRepositoryImplementation implementation) {
		this.implementation=implementation;
	}
	
	public ResponseEntity<SummaryTransaction> getSummaryTransaction() {
		SummaryTransaction summaryTransaction=null;
		try {
			summaryTransaction=new SummaryTransaction();
			summaryTransaction.setDailySummary(this.implementation.getTotalDailyIncome());
			summaryTransaction.setMonthlySummary(this.implementation.getTotalMonthlyIncome());
			return new ResponseEntity<>(summaryTransaction,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in: {}",e.getMessage());
			return new ResponseEntity<>(summaryTransaction,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<SummaryTransaction> getSummaryTransactionByRange(Date start, Date end) {
		SummaryTransaction summaryTransaction=null;
		try {
			summaryTransaction=new SummaryTransaction();
			summaryTransaction.setRangeSummary(this.implementation.getTotalIncomeByRange(start,end));
			return new ResponseEntity<>(summaryTransaction,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in: {}",e.getMessage());
			return new ResponseEntity<>(summaryTransaction,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
