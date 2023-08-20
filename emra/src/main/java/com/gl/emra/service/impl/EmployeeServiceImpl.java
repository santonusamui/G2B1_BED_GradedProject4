package com.gl.emra.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.emra.dao.EmployeeDao;
import com.gl.emra.exception.ResourceNotFoundException;
import com.gl.emra.model.Employee;
import com.gl.emra.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;

	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> listAllEmployees() {

		return employeeDao.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return employeeDao.save(employee);
	}

	@Override

	public Employee getEmployeeById(long employeeId) {

		return employeeDao.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
	}

	@Override
	public Employee updateEmployeeById(long id, Employee employee) {

		// Checking the Employee is present or not in DB

		Employee existingEmployee = employeeDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		// Updating the Existing Employee Object

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		// Save the changes to the repository

		employeeDao.save(existingEmployee);

		return existingEmployee;
	}

	@Override
	public void deleteEmployeeById(long id) {

		// Checking the Employee is present or not in DB

		employeeDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		employeeDao.deleteById(id);

	}

	@Override
	public List<Employee> searchEmployees(String first_Name) {

		// Checking the Employee is present or not in DB

		List<Employee> employees = employeeDao.searchByName(first_Name);
		if (employees.isEmpty()) {
			throw new ResourceNotFoundException("Employee ", "First-Name", first_Name);
		} else {

			return employees;
		}
	}

}
