package com.springboot.backend.service.impl;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepository;
import com.springboot.backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

    //http://localhost:8080/api/employees  (post)
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	
	}

    //http://localhost:8080/api/employees (get)
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
		
	}

	@Override
	public Employee getEmployeeById(long id) {
//		// TODO Auto-generated method stub
//		java.util.Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "ID", id);
//		}
	
		// we can also use lamda expression.
		return employeeRepository.findById(id).orElseThrow(() -> 
		              new ResourceNotFoundException("Employee", "id", id));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//we need to check weather employee with given id is existing in db or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing employee to DB
		employeeRepository.save(existingEmployee);
		
		
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check weather employee exist in DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
		                            new ResourceNotFoundException("Employee","Id",id));
		
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
		
		
	}

	
	
	
	
	

}
