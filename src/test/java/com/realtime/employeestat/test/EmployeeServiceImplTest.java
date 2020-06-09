package com.realtime.employeestat.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.realtime.employeestat.domain.Dept;
import com.realtime.employeestat.domain.Employee;
import com.realtime.employeestat.dto.SalStat;
import com.realtime.employeestat.service.EmployeeService;
import com.realtime.employeestat.service.EmployeeServiceImpl;
import com.realtime.employeestat.util.DbConnectionUtil;

class EmployeeServiceImplTest {

	private SeedDataUtil seedDataUtil = new SeedDataUtil();

	private EmployeeService empService;

	@BeforeEach
	void clarData() {
		Connection con = null;
		Statement st = null;
		try {
			con = DbConnectionUtil.dbutil.getConnection();
			st = con.createStatement();
			st.execute("delete from test.employee");
			st.execute("delete from test.dept");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnectionUtil.dbutil.close(st, con);
		}
	}

	@Test
	public void deptSalStatTest() {

		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		SalStat salSat = empService.getSalStatOfDept(10);
		System.out.println(salSat.getCount());
		System.out.println(salSat.getMax());
		assertEquals(5000, salSat.getMax());
		assertEquals(3, salSat.getCount());

	}

	@Test
	public void getEmployeeByDeptTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		List<Employee> actualEmployees = empService.getEmployeeByDept(10);
		List<Employee> expectedEmployees = new ArrayList<Employee>();
		expectedEmployees.add(new Employee(7782, "CLARK", "MANAGER", 7839, LocalDate.parse("1993-05-14"), 2450, 0, 10));
		expectedEmployees.add(new Employee(7839, "KING", "PRESIDENT", 0, LocalDate.parse("1990-06-09"), 5000, 0, 10));
		expectedEmployees.add(new Employee(7934, "MILLER", "CLERK", 7782, LocalDate.parse("2000-01-21"), 1300, 0, 10));

		assertArrayEquals(expectedEmployees.toArray(), actualEmployees.toArray());
	}

	@Test
	public void maxSalaryEmployeesTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		List<Employee> actualEmployees = empService.maxSalaryEmployees();
		List<Employee> expectedEmployees = new ArrayList<Employee>();
		expectedEmployees.add(new Employee(7839, "KING", "PRESIDENT", 0, LocalDate.parse("1990-06-09"), 5000, 0, 10));

		assertArrayEquals(expectedEmployees.toArray(), actualEmployees.toArray());
	}

	@Test
	public void getEmployeesWithManagersTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		List<Employee> actualEmployees = empService.getEmployeesWithManagers();
		List<Employee> expectedEmployees = new ArrayList<Employee>();
		expectedEmployees.add(new Employee(7902, "FORD", "ANALYST", 7566, LocalDate.parse("1997-12-05"), 3000, 0, 20));
		expectedEmployees.add(new Employee(7698, "BLAKE", "MANAGER", 7839, LocalDate.parse("1992-06-11"), 2850, 0, 30));
		expectedEmployees.add(new Employee(7839, "KING", "PRESIDENT", 0, LocalDate.parse("1990-06-09"), 5000, 0, 10));
		expectedEmployees.add(new Employee(7566, "JONES", "MANAGER", 7839, LocalDate.parse("1995-10-31"), 2975, 0, 20));
		expectedEmployees.add(new Employee(7788, "SCOTT", "ANALYST", 7566, LocalDate.parse("1996-03-05"), 3000, 0, 20));
		expectedEmployees.add(new Employee(7782, "CLARK", "MANAGER", 7839, LocalDate.parse("1993-05-14"), 2450, 0, 10));

		assertArrayEquals(expectedEmployees.toArray(), actualEmployees.toArray());
	}

	@Test
	public void getSalStatOfOrganizationTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		SalStat salSat = empService.getSalStatOfOrganization();
		System.out.println(salSat.getCount());
		System.out.println(salSat.getMax());
		assertEquals(5000, salSat.getMax());
		assertEquals(14, salSat.getCount());
		assertEquals(800, salSat.getMin());

	}

	@Test
	public void allManagerNamesTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		List<String> actualEmployees = empService.allManagerNames();
		List<String> expectedEmployees = Arrays.asList("FORD", "BLAKE", "KING", "JONES", "SCOTT", "CLARK");

		assertEquals(expectedEmployees, actualEmployees);
	}

	@Test
	public void getDeptWhichHasMaxNumberOfEmployeeTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		Dept dept = empService.getDeptWhichHasMaxNumberOfEmployee();
		assertEquals(30, dept.getDeptno());
		assertEquals("Sales", dept.getDname());
		assertEquals("Chicago", dept.getLocation());
	}

	@Test
	public void whoJoinDayIsTest() {
		empService = new EmployeeServiceImpl();
		List<Dept> deptlist = seedDataUtil.getDeptDetails();
		List<Employee> list = seedDataUtil.getEmployees();
		empService.addDepartements(deptlist);
		empService.addEmployees(list);
		List<Employee> actualEmployees = empService.whoJoinDayIs("Thursday");
		List<Employee> expectedEmployees = new ArrayList<Employee>();
		expectedEmployees.add(new Employee(7698, "BLAKE", "MANAGER", 7839, LocalDate.parse("1992-06-11"), 2850, 0, 30));

		assertArrayEquals(expectedEmployees.toArray(), actualEmployees.toArray());
	}

}
