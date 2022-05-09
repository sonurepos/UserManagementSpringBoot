package com.healthdomian.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {

	private String fname;
	private String lname;
	private String email;
	private String password;
	private String accStatus;
	private Long phoneNo;
	private LocalDate dob;
	private String gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	
		
	
}
