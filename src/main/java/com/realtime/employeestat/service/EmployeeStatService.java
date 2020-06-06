package com.realtime.employeestat.service;

import java.util.List;

import com.realtime.employeestat.domain.Dept;
import com.realtime.employeestat.domain.Employee;
import com.realtime.employeestat.dto.SalStat;

public interface EmployeeStatService {
	List<Employee> getEmployeeByDept(int deptno);

	List<Employee> maxSalaryEmployees();

	Dept getDeptWhichHasMaxNumberOfEmployee();

	List<Employee> getEmployeesWithManagers();

	SalStat getSalStatOfOrganization();

	SalStat getSalStatOfDept(int deptno);

	List<String> allManagerNames();

	List<Employee> whoJoinDayIs(String dayName);

}
