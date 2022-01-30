package com.wecode.medsoft.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecode.medsoft.contracts.authorization.AuthorizationDetail;
import com.wecode.medsoft.contracts.authorization.UserDetail;
import com.wecode.medsoft.process.AuthorizationProcess;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private AuthorizationProcess authorizationProcess;
	
	public LoginController(AuthorizationProcess authorizationProcess) {
		this.authorizationProcess=authorizationProcess;
	}
	
	@GetMapping("/byRole")
    @CrossOrigin
	public ResponseEntity<List<AuthorizationDetail>> getAuthorizationByRole(@RequestParam Integer roleId) {
		return this.authorizationProcess.getAuthorizationByRole(roleId);
	}
	
	@PostMapping("/login")
    @CrossOrigin
	public ResponseEntity<UserDetail> getAuthorizationByRole(@RequestBody UserDetail user) {
		return authorizationProcess.login(user);
	}
	
	
	
}
