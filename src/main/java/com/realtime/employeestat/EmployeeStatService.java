package com.realtime.employeestat;

import java.util.List;

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
