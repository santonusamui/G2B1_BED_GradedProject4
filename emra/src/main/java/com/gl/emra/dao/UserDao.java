package com.gl.emra.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.emra.model.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

	@Query("SELECT ue.username FROM UserEntity ue WHERE ue.username= :name")
	String existByUsername(String name);

}
