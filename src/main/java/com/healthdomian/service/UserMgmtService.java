package com.healthdomian.service;

import java.util.Map;

import com.healthdomian.bindings.LoginForm;
import com.healthdomian.bindings.UnlockAccForm;
import com.healthdomian.bindings.UserForm;

public interface UserMgmtService {
	
//	login screen related methods

	public String loginCheck(LoginForm loginForm);
	
//	save user
	public String saveUser(UserForm userForm);

//	Registration screen related methods
	
	public String emailCheck(String emailId);

	public Map<Integer, String> loadCountries();
	
	public Map<Integer , String> loadStates(Integer countryId);
	
	public Map<Integer , String>  loadCitries(Integer stateId);
	
//	unlock screen related methods
	public String unlockAccount(UnlockAccForm unlockAccForm);
	
//	forgot pwd scrren related methods
	
	public String forgotPwd(String email);
}
