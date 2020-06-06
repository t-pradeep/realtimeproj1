package com.realtime.employeestat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;
import com.realtime.employeestat.domain.Dept;
import com.realtime.employeestat.domain.Employee;

public class EmployeeDAO {

	private static final String EMP_INSERT_QUERY = "insert into test.employee(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(?,?,?,?,?,?,?,?)";
	private static final String DEPT_INSERT_QUERY = "insert into test.dept(deptno,dname,location) values(?,?,?)";
	private static final String EMP_SELECT_QUERY = "select * from test.employee";
	private static final String DEPT_SELECT_QUERY = "select * from test.dept";

	public static void addDept(List<Dept> deptlist) {
		Connection con = BaseDAO.getDb();
		try {
			for (Dept dept : deptlist) {
				PreparedStatement preparedStatement = con.prepareStatement(DEPT_INSERT_QUERY);
				preparedStatement.setInt(1, dept.getDeptno());
				preparedStatement.setString(2, dept.getDname());
				preparedStatement.setString(3, dept.getLocation());

				int result = preparedStatement.executeUpdate();
				if (result == 1) {
					System.out.println("Add dept with " + dept.getDeptno() + " sucessfully");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addEmp(List<Employee> emplist) {

		try (Connection con = BaseDAO.getDb()) {
			for (Employee emp : emplist) {
				PreparedStatement preparedStatement = con.prepareStatement(EMP_INSERT_QUERY);
				preparedStatement.setInt(1, emp.getEmpno());
				preparedStatement.setString(2, emp.getEname());
				preparedStatement.setString(3, emp.getJob());
				preparedStatement.setInt(4, emp.getMgr());
				preparedStatement.setDate(5, java.sql.Date.valueOf(emp.getHiredate()));
				preparedStatement.setInt(6, emp.getSal());
				preparedStatement.setInt(7, emp.getComm());
				preparedStatement.setInt(8, emp.getDeptno());

				int result = preparedStatement.executeUpdate();
				if (result == 1) {
					System.out.println("Added employee table with name " + emp.getEname() + " sucessfully");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Employee> empFindAll() {
		List<Employee> emplist = new ArrayList<Employee>();
		try (Connection con = BaseDAO.getDb()) {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(EMP_SELECT_QUERY);
			while (resultSet.next()) {
				Employee emp = Employee.builder().comm(resultSet.getInt("comm")).deptno(resultSet.getInt("deptno"))
						.empno(resultSet.getInt("empno")).ename(resultSet.getString("ename"))
						.job(resultSet.getString("job")).mgr(resultSet.getInt("mgr"))
						.hiredate(resultSet.getDate("hiredate").toLocalDate()).sal(resultSet.getInt("sal")).build();
				emplist.add(emp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emplist;

	}

	public static List<Dept> deptFindAll() {
		List<Dept> deptlist = null;
		try (Connection con = BaseDAO.getDb()) {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(DEPT_SELECT_QUERY);
			deptlist = new ArrayList<Dept>();
			while (resultSet.next()) {
				Dept dept = Dept.builder().deptno(resultSet.getInt("deptno")).dname(resultSet.getString("dname"))
						.location(resultSet.getString("location")).build();
				deptlist.add(dept);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptlist;

	}

}
