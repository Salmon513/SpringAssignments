package com.Salmon.Start.Implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.Salmon.Start.DAO.DAO;
import com.Salmon.Start.DTO.EmployeeDTO;
import com.Salmon.Start.Model.*;

@Repository
public class Perform implements DAO{
	
	@Autowired
	private JdbcTemplate cursor;

	@Override
	public List<Employee> getEmployees() {
		String sql = "SELECT * FROM EMPLOYEE";
		List<Employee> emplist = cursor.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
		return emplist;
	}

	
	@Override
	public String insert(Employee emp) {
		int status;
		String deptname=emp.getDept().getName();
		if(!checkDept(deptname)) {
			String sql="INSERT INTO DEPARTMENT VALUES(dept_id.nextval,?)";
			//String seq="dept_id.nextval";
			cursor.update(sql,new Object[] {deptname});
		}
		String sql="INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";
		status=cursor.update(sql,new Object[] {generateId(),emp.getName(),emp.getMobile(),emp.getEmail(),getIdbyName(deptname)});
		if(status>0)
			return "Row Inserted";
		return "Row not INserted";
	}

	@Override
	public String update(EmployeeDTO empdto) {
		int status;
		String sql="UPDATE EMPLOYEE SET EMAIL=? WHERE ID=?";
		status=cursor.update(sql,new Object[] {empdto.getEmail(),empdto.getId()});
		if(status>0) return "Details Updated";
		return "Details Not Updated";
	}

	@Override
	public String delete(String id) {
		int status;
		String sql="DELETE FROM EMPLOYEE WHERE ID=?";
		status=cursor.update(sql,new Object[] {id});
		if(status>0) return "EMPLOYEE DELETED";
		return "EMPLOYEE NOT DELETED";
	}
	
	//Returns true if specified department name exists else returns false
	public boolean checkDept(String name) {
		String sql="SELECT NAME FROM DEPARTMENT";
		List<String> deptlist=cursor.queryForList(sql,String.class);
		return deptlist.contains(name);
	}
	
	public List<Department> getDepartments(){
		String sql = "SELECT * FROM DEPARTMENT";
		List<Department> deptlist = cursor.query(sql, new BeanPropertyRowMapper<Department>(Department.class));
		return deptlist; 
	}
	
	public Integer getIdbyName(String deptname) {
		String sql = "SELECT ID FROM DEPARTMENT WHERE NAME='"+deptname+"'";
		Integer deptid = cursor.queryForObject(sql, Integer.class);
		return deptid;
	}
	
	
	public List<Map<String,Object>> getData(){
		String sql = "SELECT emp.*,dept.NAME FROM EMPLOYEE emp JOIN DEPARTMENT dept ON emp.DEPTID=dept.ID";
		List<Map<String,Object>> list = cursor.queryForList(sql);
		return list;
	}
	
	public String generateId() {
		String empid="RYTH";
		String sql="SELECT emp_id.nextval FROM DUAL";
		Integer tempid=cursor.queryForObject(sql,Integer.class);
				
		if(tempid<10) {
			empid+="00";
		}else {
			empid+="0";
		}
		empid+=Integer.toString(tempid);
		return empid;
	}
	
		
}

