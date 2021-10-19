package com.wecode.medsoft.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.incomes.RequestIncome;
import com.wecode.medsoft.process.IncomeProcess;

@RestController
@RequestMapping("/income")
public class IngresosController {

	private IncomeProcess incomeProcess;
	
	@Autowired
	public IngresosController(IncomeProcess incomeProcess) {
		this.incomeProcess=incomeProcess;
	}
	
    @PostMapping("/save")
    public ResponseEntity<String> testMethod(@RequestBody RequestIncome income){
    	incomeProcess.saveIncome(income);
    	return new ResponseEntity<>("Funciona",HttpStatus.OK);
    }

}