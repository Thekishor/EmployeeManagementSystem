package com.emp.Controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.Model.Employee;
import com.emp.ServiceImpl.EmployeeServiceImpl;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	// display all the employees
	@GetMapping("/show")
	public String viewHomePage(Model model) {
		model.addAttribute("employees", employeeServiceImpl.getAllEmployees());
		return "index";
	}

	// object send into thymeleaf
	@GetMapping("/form")
	public String showNewEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "form";
	}

	// update request to thymeleaf
	@GetMapping("/form/{id}")
	public String showEmployeeForm(@PathVariable Long id, Model model) {
		Employee employee = this.employeeServiceImpl.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "form";
	}

	// save and update
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "form";
		}

		if (employee.getId() != 0) {
			// Update existing employee
			Employee existingEmployee = this.employeeServiceImpl.getEmployeeById(employee.getId());
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
			existingEmployee.setEmail(employee.getEmail());
			this.employeeServiceImpl.saveEmployee(existingEmployee);
		} else {
			// Save new employee
			this.employeeServiceImpl.saveEmployee(employee);
		}
		return "redirect:/show";
	}

	// delete request
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		this.employeeServiceImpl.deleteEmploye(id);
		return "redirect:/show";
	}

}
