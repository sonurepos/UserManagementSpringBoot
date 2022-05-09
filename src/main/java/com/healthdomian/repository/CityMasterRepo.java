package com.healthdomian.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthdomian.entity.CityMasterEntity;

@Repository
public interface CityMasterRepo extends JpaRepository<CityMasterEntity, Serializable> {

	public List<CityMasterEntity> findByCityId(Integer stateId);
}
