package com.lwl.ems.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.lwl.ems.dao.EmployeeDao;
import com.lwl.ems.dao.EmployeeDaoImpl;
import com.lwl.ems.domain.Dept;
import com.lwl.ems.domain.Employee;
import com.lwl.ems.dto.SalStat;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = new EmployeeDaoImpl();
	}

	@Override
	public List<Employee> getEmployeeByDept(int deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> maxSalaryEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dept getDeptWhichHasMaxNumberOfEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployeesWithManagers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalStat getSalStatOfOrganization() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalStat getSalStatOfDept(int deptno) {
		assertTrue(deptno > 0);
		SalStat salStat = employeeDao.getSalStatOfDept(deptno);
		return salStat;
	}

	@Override
	public List<String> allManagerNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> whoJoinDayIs(String dayName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addEmployees(List<Employee> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addDepartements(List<Dept> list) {
		// TODO Auto-generated method stub
		return 0;
	}

}
