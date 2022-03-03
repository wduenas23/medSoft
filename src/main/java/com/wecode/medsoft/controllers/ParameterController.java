package com.wecode.medsoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.parameter.ParameterPojo;
import com.wecode.medsoft.process.ParameterProcess;

@RestController
@RequestMapping("parameter")
public class ParameterController {
    
    private ParameterProcess parameterProcess;

    @Autowired
    public ParameterController(ParameterProcess parameterProcess) {
        this.parameterProcess=parameterProcess;
    }

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<List<ParameterPojo>> getAll(){
        return parameterProcess.getAll();
    }
    
    @GetMapping("/byId")
    @CrossOrigin
    public ResponseEntity<ParameterPojo> byId(@RequestParam String id){
        return parameterProcess.getById(id);
    }
    
    @PostMapping("/save")
    @CrossOrigin
    public ResponseEntity<ParameterPojo> byId(@RequestBody ParameterPojo param){
        return parameterProcess.saveEdit(param);
    }
}
