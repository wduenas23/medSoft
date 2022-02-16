package com.wecode.medsoft.controllers;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.incomes.RequestIncome;
import com.wecode.medsoft.contracts.incomes.ResponseIncome;
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
    @CrossOrigin
    public ResponseEntity<ResponseIncome> save(@RequestBody RequestIncome income){
    	return new ResponseEntity<>(incomeProcess.saveIncome(income),HttpStatus.OK);
    }
    
    @PostMapping("/saveSale")
    @CrossOrigin
    public ResponseEntity<ResponseIncome> saveSale(@RequestBody RequestIncome income){
    	return new ResponseEntity<>(incomeProcess.saveIncomeSales(income),HttpStatus.OK);
    }
    
    @GetMapping("/dailyIncomes")
    @CrossOrigin
    public ResponseEntity<List<ResponseIncome>> getDailyIncomes(){
    	return incomeProcess.getDailyIncomes();
    }
    
    @GetMapping("/dailyIncomesByRange")
    @CrossOrigin
    public ResponseEntity<List<ResponseIncome>> getDailyIncomesByRange(@RequestParam Date start,@RequestParam Date end){
    	return incomeProcess.getDailyIncomesDateRange(start,end);
    }
    
    @GetMapping("/byId")
    @CrossOrigin
    public ResponseEntity<ResponseIncome> getIncomeById(@RequestParam Integer id){
    	return incomeProcess.getTxById(id);
    }
    
    
    

}