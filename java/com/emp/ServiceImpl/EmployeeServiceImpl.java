package com.emp.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.Model.Employee;
import com.emp.Repository.EmployeeRepository;
import com.emp.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Employee employee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id ::" + id));
		return employee;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public void deleteEmploye(long id) {
		this.employeeRepository.deleteById(id);
	}
}
