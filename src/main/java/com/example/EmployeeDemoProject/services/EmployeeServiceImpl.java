package com.example.EmployeeDemoProject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	 @PersistenceContext
	    private EntityManager entityManager;

	    @Override
	    public List<Object[]> getMaxSalaryByDept() {

	        String sql =
	                "SELECT e.emp_id, e.department, e.salary " + "FROM employee_table e " +
	                "INNER JOIN ( " + "    SELECT department, MAX(salary) AS max_salary " +
	                "    FROM employee_table " + "    GROUP BY department " +
	                ") dept_max " + "ON e.department = dept_max.department " + "AND e.salary = dept_max.max_salary";

	        Query query = entityManager.createNativeQuery(sql);
	        @SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();

	        if (result == null || result.isEmpty()) {
	            throw new EmployeeNotFoundException("No employee data found");
	        }

	        return result;
	    }
	
}
