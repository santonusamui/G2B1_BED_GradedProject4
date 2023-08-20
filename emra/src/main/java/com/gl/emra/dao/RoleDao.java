package com.gl.emra.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gl.emra.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

	@Query("SELECT r.name FROM Role r WHERE r.name= :name")
	String existByName(String name);

}
