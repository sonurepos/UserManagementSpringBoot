package com.healthdomian.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USER_ACCOUNT")
@Data
public class UserDtlsEntity {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="FIRST_NAME")
	private String fname;
	
	@Column(name="LAST_NAME")
	private String lname;
	
	@Column(name="EMAIL" , unique=true)
	private String email;
	
	@Column(name="USER_PWD")
	private String password;
	
	@Column(name="ACC_STATUS")
	private String accStatus;
	
	@Column(name="PHONE_NO")
	private Long phoneNo;
	
	@Column(name="BIRTH_DATE")
	private LocalDate dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	

}
