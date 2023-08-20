package com.gl.emra.service;

import java.util.List;

import com.gl.emra.model.Employee;

public interface EmployeeService {

	List<Employee> listAllEmployees();

	Employee addEmployee(Employee employee);

	Employee getEmployeeById(long employeeId);

	Employee updateEmployeeById(long id, Employee employee);

	void deleteEmployeeById(long id);

	List<Employee> searchEmployees(String first_Name);

}
