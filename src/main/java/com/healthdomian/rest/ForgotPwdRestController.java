package com.healthdomian.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.healthdomian.service.UserMgmtService;

@RestController
@CrossOrigin
public class ForgotPwdRestController {

	@Autowired
	private UserMgmtService usrMgmntService;
	
	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable("email") String email){
		String forgotPwd = usrMgmntService.forgotPwd(email);
		return new ResponseEntity<>(forgotPwd, HttpStatus.OK);
	}
}
