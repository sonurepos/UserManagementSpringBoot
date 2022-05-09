package com.healthdomian.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthdomian.bindings.LoginForm;
import com.healthdomian.service.UserMgmtService;

@RestController
@CrossOrigin
public class LoginRestController {
	
	@Autowired
	private UserMgmtService userMgmntService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
		
		String status = userMgmntService.loginCheck(loginForm);
		
		return new ResponseEntity<String>(status , HttpStatus.OK);
	}

}
