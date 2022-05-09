package com.healthdomian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="STATES_MASTER")
@Data
public class StateMasterEntity {

	@Id
	@GeneratedValue
	@Column(name="STATE_ID")
	public Integer stateId;
	
	@Column(name="COUNTRY_ID")
	public Integer countryId;
	
	@Column(name="STATE_NAME")
	public String stateName;
}
