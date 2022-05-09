package com.healthdomian.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthdomian.bindings.LoginForm;
import com.healthdomian.bindings.UnlockAccForm;
import com.healthdomian.bindings.UserForm;
import com.healthdomian.entity.CityMasterEntity;
import com.healthdomian.entity.CountryMasterEntity;
import com.healthdomian.entity.StateMasterEntity;
import com.healthdomian.entity.UserDtlsEntity;
import com.healthdomian.repository.CityMasterRepo;
import com.healthdomian.repository.CountryMasterRepo;
import com.healthdomian.repository.StateMasterRepo;
import com.healthdomian.repository.UserDtlsRepo;
import com.healthdomian.util.EmailUtils;

@Service
public class UserMgmtServiceImpl  implements UserMgmtService{
	
	@Autowired
	private UserDtlsRepo userRepo;
	
	@Autowired
	private CountryMasterRepo countryMasterRepo;
	
	@Autowired
	private StateMasterRepo stateMasterRepo;
	
	@Autowired
	private CityMasterRepo cityMasterRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String loginCheck(LoginForm loginForm) {
		
		 UserDtlsEntity userAcntDtls = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		
		 if(userAcntDtls == null) {
			 return "INVALID CREDENTIALS";
		 }else if(userAcntDtls.getAccStatus().equals("LOCKED")) {
			 return "YOUR ACCOUNT LOCKED";
		 }else {
			 return "SUCCESS";
		 }
		
	}

	@Override
	public String saveUser(UserForm userForm) {
		
		//generating random password and setting into userForm
		userForm.setPassword(generateRandomPassword(6));
		
		UserDtlsEntity userEntity=new UserDtlsEntity();
		
		
		//copy userForm to entiry class
		BeanUtils.copyProperties(userForm, userEntity);
		userEntity.setAccStatus("LOCKED");
		
		//saving the user entity details
		userRepo.save(userEntity);
		
//		TODO : send email to unlock  with temp password
		
		String subject="User Registration - SonuKumarJha";
		String emailBody=readUnlockAccntEmailBody(userForm);
		emailUtils.sendEmail(userForm.getEmail(), subject, emailBody);
		
		return "SUCCESS";
	}

	@Override
	public String emailCheck(String emailId) {
		
		UserDtlsEntity userAccDtls = userRepo.findByEmail(emailId);
		
		if(userAccDtls == null) {
			return "UNIQUE";
			
		}else {
			return "DUPLICATE";
		}
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity>  listOfCountry = countryMasterRepo.findAll();
		
		//converting list to map
		Map<Integer , String > countryMap=new HashMap<>();
		listOfCountry.forEach(country -> {
			countryMap.put(country.getContryId() , country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		List<StateMasterEntity> listOfStateByCountryId = stateMasterRepo.findByCountryId(countryId);
		
		Map<Integer , String> stateMap=new HashMap<>();
		
		//converting list to map
		listOfStateByCountryId.forEach(state -> {
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCitries(Integer stateId) {
		
		List<CityMasterEntity> listOfCityByStateId = cityMasterRepo.findByCityId(stateId);
		
		Map<Integer , String> cityMap= new HashMap<>();
		
		//convert list to map
		
		listOfCityByStateId.forEach(city ->{
			cityMap.put(city.getCityId(), city.getCityName());
		});
		return cityMap;
	}

	@Override
	public String unlockAccount(UnlockAccForm unlockAccForm) {
		
		UserDtlsEntity userEntity=userRepo.findByEmailAndPassword(unlockAccForm.getEmail(), unlockAccForm.getTempPwd());
		
		if(userEntity == null) {
			return "INVALID TEMPORARY PASSWORD";
		}
		
		userEntity.setPassword(unlockAccForm.getNewPwd());
		userEntity.setAccStatus("UNLOCKED");
		
		//save new password
		userRepo.save(userEntity);
		return "ACCOUNT UNLOCKED SUCCESSFULLY!";
	}

	@Override
	public String forgotPwd(String email) {
		
		UserDtlsEntity userDtls = userRepo.findByEmail(email);
		
		if(userDtls == null) {
			return "INVALID EMAIL";
		}
		
		//calling sned mail 
		String subject="USER REGISTRATION - SonuKumarJha pvt. ltd.";
		String body=readRecoverPasswordEmailBody(userDtls);
		emailUtils.sendEmail(email, subject, body);
		
		return "PASSWORD SEND TO REGISTERED EMAIL SUCCESSFULLY";
	}

	// generate a random AlphaNumeric String using for temp password
	// using Math.random() method
 // function to generate a random string of length n
		private static String generateRandomPassword(int n)
		{

			// chose a Character random from this String
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
										+ "0123456789"
										+ "abcdefghijklmnopqrstuvxyz";

			// create StringBuffer size of AlphaNumericString
			StringBuilder sb = new StringBuilder(n);

			for (int i = 0; i < n; i++) {

				// generate a random number between
				// 0 to AlphaNumericString variable length
				int index
					= (int)(AlphaNumericString.length()
							* Math.random());

				// add Character one by one in end of sb
				sb.append(AlphaNumericString
							.charAt(index));
			}

			return sb.toString();
		}

		//read from files
		
		private String readUnlockAccntEmailBody(UserForm userForm) {
			String body="";
			StringBuffer sb=new StringBuffer();
			
			
			Path path = Paths.get("UNLOCK_ACC_EMAIL_BODY_TEMPLTAE.txt");
			
			//java 8 
			

			try(Stream<String>  stream=Files.lines(path)){
				
				stream.forEach(line -> {
					sb.append(line);
				});
				
				body=sb.toString();
				body=body.replace("{FNAME}", userForm.getFname());
				body=body.replace("{LNAME" , userForm.getLname());
				body=body.replace("{TEMP-PWD" , userForm.getPassword());
				body=body.replace("{EMAIL" , userForm.getEmail());
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return body;
		}
	
		private String readRecoverPasswordEmailBody(UserDtlsEntity usrDtls) {
			String body="";
			StringBuffer sb=new StringBuffer();
			
			
			Path path = Paths.get("RECOVER_PASSWORD_BODY_TEMPLATE.txt");
			
			//java 8 steam and forEach loop
			

			try(Stream<String>  stream=Files.lines(path)){
				
				stream.forEach(line -> {
					sb.append(line);
				});
				
				body=sb.toString();
				body=body.replace("{FNAME}", usrDtls.getFname());
				body=body.replace("{LNAME" , usrDtls.getLname());
				body=body.replace("{PWD" , usrDtls.getPassword());
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return body;
		}
	


}
