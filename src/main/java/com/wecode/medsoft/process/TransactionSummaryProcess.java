package com.wecode.medsoft.process;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.medicalservices.MedicalServiceCount;
import com.wecode.medsoft.contracts.product.ProductCount;
import com.wecode.medsoft.contracts.summary.SummaryTransaction;
import com.wecode.medsoft.persistence.MedicalServicesRepository;
import com.wecode.medsoft.persistence.TransactionSummaryRepositoryImplementation;
import com.wecode.medsoft.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionSummaryProcess {

	private TransactionSummaryRepositoryImplementation implementation;
	private MedicalServicesRepository medicalServiceRepository;
	
	@Autowired
	public TransactionSummaryProcess(TransactionSummaryRepositoryImplementation implementation,MedicalServicesRepository medicalServiceRepository) {
		this.implementation=implementation;
		this.medicalServiceRepository=medicalServiceRepository;
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
	
	public ResponseEntity<SummaryTransaction> getSummaryTransactionByRange(Date start, Date end, Integer type) {
		SummaryTransaction summaryTransaction=null;
		try {
			summaryTransaction=new SummaryTransaction();
			summaryTransaction.setRangeSummary(this.implementation.getTotalIncomeByRange(start,end,type));
			return new ResponseEntity<>(summaryTransaction,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in: {}",e.getMessage());
			return new ResponseEntity<>(summaryTransaction,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<SummaryTransaction> getSummaryComissionTransactionByRange(Date start, Date end, Integer type) {
		SummaryTransaction summaryTransaction=null;
		try {
			summaryTransaction=new SummaryTransaction();
			summaryTransaction.setRangeComission(this.implementation.getTotalComissionByRange(start,end,type));
			return new ResponseEntity<>(summaryTransaction,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in: {}",e.getMessage());
			return new ResponseEntity<>(summaryTransaction,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<MedicalServiceCount>> getServiceCountByRange(Date start, Date end) {
		try {
			return new ResponseEntity<>(this.medicalServiceRepository.getServiceCountByRange(DateUtil.atStartOfDay(start), DateUtil.atEndOfDay(end)),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in: {}",e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<ProductCount>> getProductCountByRange(Date start, Date end) {
		try {
			return new ResponseEntity<>(this.implementation.getProductCountByRange(DateUtil.atStartOfDay(start), DateUtil.atEndOfDay(end)),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in: {}",e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
