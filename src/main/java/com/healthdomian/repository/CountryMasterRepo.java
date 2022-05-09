package com.healthdomian.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthdomian.entity.CountryMasterEntity;

@Repository
public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity, Serializable> {
	
	

}
