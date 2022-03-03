package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.parameter.ParameterPojo;
import com.wecode.medsoft.entities.Parameter;
import com.wecode.medsoft.persistence.ParameterRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParameterProcess {

    private ParameterRepository parameterRepo;

    @Autowired
    public ParameterProcess(ParameterRepository parameterRepo) {
        this.parameterRepo=parameterRepo;
    }
    
    public ResponseEntity<List<ParameterPojo>> getAll(){
        List<ParameterPojo> paramResponseList=new ArrayList<>();
        ParameterPojo paramResponse=null;
        try {
            log.info("Calling find all");
            List<Parameter> paymentTypes=(List<Parameter>) parameterRepo.findAll();    
            for (Parameter param : paymentTypes) {
            	paramResponse=new ParameterPojo();
            	paramResponse.setPmtId(param.getPmtId());
            	paramResponse.setPmContext(param.getPmtContext());
            	paramResponse.setPmtValue(param.getPmtValue());
                paramResponseList.add(paramResponse);
            }
            return new ResponseEntity<>(paramResponseList,HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error in {}", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
        }
        
    }
    
    @Transactional
    public  ResponseEntity<ParameterPojo> saveEdit(ParameterPojo paramPojo) {
    	try {
    		Parameter param= new Parameter();
    		param.setPmtId(paramPojo.getPmtId());
    		param.setPmtContext(paramPojo.getPmContext());
    		param.setPmtValue(paramPojo.getPmtValue());
    		parameterRepo.save(param);
    		return new ResponseEntity<>(paramPojo,HttpStatus.OK);
		} catch (Exception e) {
			log.info("Error in {}", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
		}
    }
    
    public ResponseEntity<ParameterPojo> getById(String id) {
    	ParameterPojo paramPojo=null;
    	try {
    		Optional<Parameter> param=parameterRepo.findById(id);
    		if(param.isPresent()) {
    			paramPojo=new ParameterPojo();
    			paramPojo.setPmContext(param.get().getPmtContext());
    			paramPojo.setPmtId(param.get().getPmtId());
    			paramPojo.setPmtValue(param.get().getPmtValue());
    			return new ResponseEntity<>(paramPojo,HttpStatus.OK);
    		}
    		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
		} catch (Exception e) {
			log.info("Error in {}", e.getLocalizedMessage() + " " + e.getMessage());
            throw e;
		}
    }
}
