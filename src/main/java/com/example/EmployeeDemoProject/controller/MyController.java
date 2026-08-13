package com.example.EmployeeDemoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeDemoProject.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class MyController {
	
	@Autowired
    private EmployeeService employeeService;
	
	@GetMapping("/maxsalary/api")
	 public List<Object[]> getMaxSalaryByDept() {
        return employeeService.getMaxSalaryByDept();
    }

}
