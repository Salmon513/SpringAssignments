package com.Salmon.Start.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Salmon.Start.DTO.EmployeeDTO;
import com.Salmon.Start.Implementation.Perform;
import com.Salmon.Start.Model.*;


@RestController
public class MyController {
	@Autowired
	Perform use;	
			
	@PutMapping(value="/save")
	public String insert(@RequestBody Employee emp) {
		
		 return use.insert(emp);		
	}
	
	@PutMapping(value="/update")
	public String update(@RequestBody EmployeeDTO empdto) {
		 
		return use.update(empdto);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public String delete(@PathVariable String id) {
		
		return use.delete(id);
	}
	
	@GetMapping(value="/getdata")
	public List<Map<String,Object>> getData(){
		return use.getData();
	}
	
	@GetMapping(value="/getdepts")
	public List<Department> getDepts(){
		return use.getDepartments();
	}
	
	@GetMapping(value="/getemps")
	public List<Employee> getEmps(){
		return use.getEmployees();
	}
	@GetMapping(value="/Test")
	public String test() {
		return "Test";
	}
//	@GetMapping("/idtest")
//	public int Testtt() {
//		return use.generateId();
//	}
}
