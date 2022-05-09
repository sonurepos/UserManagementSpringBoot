package com.healthdomian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CITIES_MASTER")
@Data
public class CityMasterEntity {

	@Id
	@GeneratedValue
	@Column(name="CITY_ID")
	public Integer cityId;
	
	@Column(name="CITY_NAME")
	public String cityName;
	
	@Column(name="STATE_ID")
	public Integer stateId;
}
