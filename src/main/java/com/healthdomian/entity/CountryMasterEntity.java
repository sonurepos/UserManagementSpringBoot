package com.healthdomian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="COUNTRY_MASTER")
@Data
public class CountryMasterEntity {

	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID")
	private Integer contryId;
	
	@Column(name="COUNTR_CODE")
	private Integer countryCode;
	
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	
	
	
}
