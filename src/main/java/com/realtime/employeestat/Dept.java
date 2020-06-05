package com.realtime.employeestat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@deptno")
public class Dept implements Serializable {

	private Integer deptno;
	private String dname;
	private String location;
	@Setter(value = AccessLevel.NONE)
	private List<Employee> empList = new ArrayList<Employee>();
	
	public Employee addEmployee(Employee emp) {
		this.empList.add(emp);
		return emp;
	}
	
}