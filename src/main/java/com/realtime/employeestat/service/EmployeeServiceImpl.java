package com.realtime.employeestat.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.realtime.employeestat.dao.EmployeeDao;
import com.realtime.employeestat.dao.EmployeeDaoImpl;
import com.realtime.employeestat.domain.Dept;
import com.realtime.employeestat.domain.Employee;
import com.realtime.employeestat.dto.SalStat;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = new EmployeeDaoImpl();
	}

	@Override
	public List<Employee> getEmployeeByDept(int deptno) {
		assertTrue(deptno > 0);
		return employeeDao.getEmployeeByDept(deptno);

	}

	@Override
	public List<Employee> maxSalaryEmployees() {
		return employeeDao.maxSalaryEmployees();
	}

	@Override
	public Dept getDeptWhichHasMaxNumberOfEmployee() {
		return employeeDao.getDeptWhichHasMaxNumberOfEmployee();
	}

	@Override
	public List<Employee> getEmployeesWithManagers() {
		return employeeDao.getEmployeesWithManagers();
	}

	@Override
	public SalStat getSalStatOfOrganization() {
		return employeeDao.getSalStatOfOrganization();
	}

	@Override
	public SalStat getSalStatOfDept(int deptno) {
		assertTrue(deptno > 0);
		SalStat salStat = employeeDao.getSalStatOfDept(deptno);
		return salStat;
	}

	@Override
	public List<String> allManagerNames() {
		return employeeDao.allManagerNames();
	}

	@Override
	public List<Employee> whoJoinDayIs(String dayName) {
		assertTrue(dayName.length() > 0);
		return employeeDao.whoJoinDayIs(dayName);

	}

	@Override
	public int addEmployees(List<Employee> list) {
		return employeeDao.addEmployees(list);
	}

	@Override
	public int addDepartements(List<Dept> list) {
		return employeeDao.addDepartements(list);
	}

}
