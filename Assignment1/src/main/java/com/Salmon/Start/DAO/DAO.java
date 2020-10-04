package com.Salmon.Start.DAO;
import java.util.List;

import com.Salmon.Start.DTO.EmployeeDTO;
import com.Salmon.Start.Model.Employee;

public interface DAO {
	
	List<Employee> getEmployees();
	
	String insert(Employee emp);
	
	String update(EmployeeDTO empdto);
	
	String delete(String id);
}
