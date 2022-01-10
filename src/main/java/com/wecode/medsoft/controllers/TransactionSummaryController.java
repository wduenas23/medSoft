package com.wecode.medsoft.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.summary.SummaryTransaction;
import com.wecode.medsoft.process.TransactionSummaryProcess;

@RestController
@RequestMapping("/summary")
public class TransactionSummaryController {

	private TransactionSummaryProcess process;
	
	@Autowired
	public TransactionSummaryController(TransactionSummaryProcess process) {
		this.process=process;
	}
	
	@GetMapping("/daily-monthly")
    @CrossOrigin
    public ResponseEntity<SummaryTransaction> getDailyAndMontlySummary(){
        return process.getSummaryTransaction();
    }
	
	@GetMapping("/range")
    @CrossOrigin
    public ResponseEntity<SummaryTransaction> getRangeSummary(@RequestParam Date start,@RequestParam Date end){
        return process.getSummaryTransactionByRange(start,end);
    }
	
}
