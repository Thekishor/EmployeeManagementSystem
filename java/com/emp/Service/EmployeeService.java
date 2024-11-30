package com.emp.Service;

import java.util.List;

import com.emp.Model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(long id);
	public Employee saveEmployee(Employee employee);
	public void deleteEmploye(long id);
}
