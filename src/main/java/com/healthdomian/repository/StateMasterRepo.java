package com.healthdomian.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthdomian.entity.StateMasterEntity;
import com.healthdomian.entity.UserDtlsEntity;

@Repository
public interface StateMasterRepo extends JpaRepository<StateMasterEntity, Serializable> {

	public List<StateMasterEntity> findByCountryId(Integer countryId);
}
