package com.gl.emra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.emra.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE e.firstName LIKE CONCAT('%',:first_Name,'%')")
	List<Employee> searchByName(String first_Name);

}
