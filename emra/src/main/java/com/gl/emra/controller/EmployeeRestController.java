package com.gl.emra.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.emra.dao.EmployeeDao;
import com.gl.emra.model.Employee;
import com.gl.emra.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

	private EmployeeDao empDao;

	private EmployeeService employeeService;

	public EmployeeRestController(EmployeeDao empDao, EmployeeService employeeService) {
		super();
		this.empDao = empDao;
		this.employeeService = employeeService;
	}

	// Get All list of Employees

	@GetMapping("/list")
	public List<Employee> getAllEmployees() {

		return employeeService.listAllEmployees();
	}

	// Create an Employee

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

		return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
	}

	// Find an employee by id

	@GetMapping("/get/{Id}")
	public Employee getEmployeeById(@PathVariable("Id") long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	// Update an Employee by Id

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee updateEmployee(@PathVariable("id") long Id, @RequestBody Employee employee) {

		return employeeService.updateEmployeeById(Id, employee);

	}

	// Delete an Employee by Id
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long Id) {

		employeeService.deleteEmployeeById(Id);

		return new ResponseEntity<String>("Deleted the employee, having id - " + Id, HttpStatus.OK);
	}

	// Search Employees having FirstName

	@GetMapping("/search/{query}")
	public List<Employee> searchEmployees(@PathVariable("query") String first_Name) {

		return employeeService.searchEmployees(first_Name.trim());
	}

	// sort Employees Ascending or Descending Depending upon
	// the request Parameter

	@GetMapping("/sort{order}")
	public List<Employee> getSortedEmployees(@RequestParam String order) {
		order = order.trim();
		Sort sort = Sort.by("firstName");
		if (order.equals("desc")) {
			sort = sort.descending();
		}
		return empDao.findAll(sort);
	}
}
