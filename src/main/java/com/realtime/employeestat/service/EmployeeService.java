package com.lwl.ems.service;

import java.util.List;

import com.lwl.ems.domain.Dept;
import com.lwl.ems.domain.Employee;
import com.lwl.ems.dto.SalStat;

public interface EmployeeService {

	List<Employee> getEmployeeByDept(int deptno);

	List<Employee> maxSalaryEmployees();

	Dept getDeptWhichHasMaxNumberOfEmployee();

	List<Employee> getEmployeesWithManagers();

	SalStat getSalStatOfOrganization();

	SalStat getSalStatOfDept(int deptno);

	List<String> allManagerNames();

	List<Employee> whoJoinDayIs(String dayName);

	int addEmployees(List<Employee> list);

	int addDepartements(List<Dept> list);

}
