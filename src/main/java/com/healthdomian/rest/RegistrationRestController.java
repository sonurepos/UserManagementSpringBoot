package com.healthdomian.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthdomian.bindings.UserForm;
import com.healthdomian.service.UserMgmtService;

@RestController
@CrossOrigin
public class RegistrationRestController {

	@Autowired
	private UserMgmtService usermgmtService;

	@GetMapping("/emailcheck/{email}")
	public ResponseEntity<String> emailCheck(@PathVariable("email") String emailId) {

		String status = usermgmtService.emailCheck(emailId);

		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@GetMapping("/countries")
	public ResponseEntity<Map<Integer, String>> getCountries() {

		Map<Integer, String> countriesMap = usermgmtService.loadCountries();

		return new ResponseEntity<>(countriesMap, HttpStatus.OK);
	}
	
	@GetMapping("/states/{countryId}")
	public ResponseEntity<Map<Integer , String>> getStates(@PathVariable("countryId") Integer countryId){
		Map<Integer,String> statesMap=usermgmtService.loadStates(countryId);
		return new ResponseEntity<>(statesMap , HttpStatus.OK);
	}
	
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<Map<Integer , String>> getCities(@PathVariable("stateId") Integer stateId){
		Map<Integer, String> citiesMap = usermgmtService.loadCitries(stateId);
		return new ResponseEntity<>(citiesMap, HttpStatus.OK);
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody UserForm userForm){
		String status = usermgmtService.saveUser(userForm);
		return new ResponseEntity<>(status , HttpStatus.CREATED);
	}

}
