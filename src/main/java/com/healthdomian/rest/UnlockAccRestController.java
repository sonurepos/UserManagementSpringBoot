	package com.healthdomian.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthdomian.bindings.UnlockAccForm;
import com.healthdomian.service.UserMgmtService;

@RestController
@CrossOrigin
public class UnlockAccRestController {

	@Autowired
	private UserMgmtService usrMgmntService;
	
	
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockUserAcnt(@RequestBody UnlockAccForm unlckAccForm){
		
		String unlockAccount = usrMgmntService.unlockAccount(unlckAccForm);
		return new ResponseEntity<>(unlockAccount , HttpStatus.OK);
	}
	
}
