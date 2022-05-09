package com.healthdomian.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthdomian.entity.UserDtlsEntity;

@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Serializable> {

	public UserDtlsEntity findByEmailAndPassword(String email, String password);

	public UserDtlsEntity findByEmail(String email);
}
