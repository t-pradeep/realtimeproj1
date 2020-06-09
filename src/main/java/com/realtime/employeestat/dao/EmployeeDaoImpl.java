package com.realtime.employeestat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.realtime.employeestat.domain.Dept;
import com.realtime.employeestat.domain.Employee;
import com.realtime.employeestat.dto.SalStat;
import com.realtime.employeestat.util.DbConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String EMP_INSERT = "insert into test.employee(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(?,?,?,?,?,?,?,?)";
	private static final String DEPT_INSERT = "insert into test.dept(deptno,dname,location) values(?,?,?)";
	private static final String DEPT_SAL_SAT = "select count(*) as count,max(sal) as max,min(sal) as min,avg(sal) as avg  from test.employee where deptno = ?";
	private static final String EMP_BY_DEPT = "select * from employee where deptno=?";
	private static final String EMP_MAXSAL = "select * from test.Employee where sal=(select max(sal) as max from test.employee)";
	private static final String EMPLOYEES_WITH_MANAGERS = "select * from test.employee where empno IN (select mgr from test.employee)";
	private static final String ALLDEPT_SAL_SAT = "select count(*) as count,max(sal) as max,min(sal) as min,avg(sal) as avg  from test.employee";
	private static final String MANAGER_NAMES = "select ename from test.employee where empno IN (select mgr from test.employee)";
	private static final String DEPT_HAS_MAXEMP = "SELECT * FROM test.dept where deptno= (select deptno from test.employee group by deptno order by count(*) desc limit 1)";
//	private static final String DEPT_HAS_MAXEMP = "select * from test.dept where deptno =(select deptno from test.employee group  by deptno having count(*) =(select max(e.count) from (select count(*) as count from test.employee group by deptno) e))";
	private static final String EMP_JOIN_DAY = "select * from test.employee where dayname(hiredate)=?";

	private DbConnectionUtil dbUtil = DbConnectionUtil.dbutil;

	@Override
	public List<Employee> getEmployeeByDept(int deptno) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(EMP_BY_DEPT);
			pst.setInt(1, deptno);
			rs = pst.executeQuery();
			while (rs.next()) {
				Employee emp = Employee.builder().comm(rs.getInt("comm")).deptno(rs.getInt("deptno"))
						.empno(rs.getInt("empno")).ename(rs.getString("ename")).job(rs.getString("job"))
						.mgr(rs.getInt("mgr")).hiredate(rs.getDate("hiredate").toLocalDate()).sal(rs.getInt("sal"))
						.build();
				empList.add(emp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return empList;
	}

	@Override
	public List<Employee> maxSalaryEmployees() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(EMP_MAXSAL);

			rs = pst.executeQuery();
			while (rs.next()) {
				Employee emp = Employee.builder().comm(rs.getInt("comm")).deptno(rs.getInt("deptno"))
						.empno(rs.getInt("empno")).ename(rs.getString("ename")).job(rs.getString("job"))
						.mgr(rs.getInt("mgr")).hiredate(rs.getDate("hiredate").toLocalDate()).sal(rs.getInt("sal"))
						.build();
				empList.add(emp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return empList;
	}

	@Override
	public Dept getDeptWhichHasMaxNumberOfEmployee() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Dept dept = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(DEPT_HAS_MAXEMP);
			rs = pst.executeQuery();
			if (rs.next()) {

				dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
						.location(rs.getString("location")).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return dept;
	}

	@Override
	public List<Employee> getEmployeesWithManagers() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(EMPLOYEES_WITH_MANAGERS);
			rs = pst.executeQuery();
			while (rs.next()) {
				Employee emp = Employee.builder().comm(rs.getInt("comm")).deptno(rs.getInt("deptno"))
						.empno(rs.getInt("empno")).ename(rs.getString("ename")).job(rs.getString("job"))
						.mgr(rs.getInt("mgr")).hiredate(rs.getDate("hiredate").toLocalDate()).sal(rs.getInt("sal"))
						.build();
				empList.add(emp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return empList;
	}

	@Override
	public SalStat getSalStatOfOrganization() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SalStat salStat = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(ALLDEPT_SAL_SAT);
			rs = pst.executeQuery();
			if (rs.next()) {
				long count = rs.getLong("count");
				float min = rs.getFloat("min");
				float max = rs.getFloat("max");
				float avg = rs.getFloat("avg");
				salStat = SalStat.builder().min(min).max(max).avg(avg).count(count).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return salStat;
	}

	@Override
	public SalStat getSalStatOfDept(int deptno) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SalStat salStat = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(DEPT_SAL_SAT);
			pst.setInt(1, deptno);
			rs = pst.executeQuery();
			if (rs.next()) {
				long count = rs.getLong("count");
				float min = rs.getFloat("min");
				float max = rs.getFloat("max");
				float avg = rs.getFloat("avg");
				salStat = SalStat.builder().min(min).max(max).avg(avg).count(count).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return salStat;
	}

	@Override
	public List<String> allManagerNames() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> managerNames = new ArrayList<String>();
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(MANAGER_NAMES);

			rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("ename");
				managerNames.add(name);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return managerNames;
	}

	@Override
	public List<Employee> whoJoinDayIs(String dayName) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(EMP_JOIN_DAY);
			pst.setString(1, dayName);
			rs = pst.executeQuery();
			while (rs.next()) {
				Employee emp = Employee.builder().comm(rs.getInt("comm")).deptno(rs.getInt("deptno"))
						.empno(rs.getInt("empno")).ename(rs.getString("ename")).job(rs.getString("job"))
						.mgr(rs.getInt("mgr")).hiredate(rs.getDate("hiredate").toLocalDate()).sal(rs.getInt("sal"))
						.build();
				empList.add(emp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return empList;
	}

	@Override
	public int addEmployees(List<Employee> emplist) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(EMP_INSERT);
			for (Employee emp : emplist) {

				pst.setInt(1, emp.getEmpno());
				pst.setString(2, emp.getEname());
				pst.setString(3, emp.getJob());
				pst.setInt(4, emp.getMgr());
				pst.setDate(5, java.sql.Date.valueOf(emp.getHiredate()));
				pst.setInt(6, emp.getSal());
				pst.setInt(7, emp.getComm());
				pst.setInt(8, emp.getDeptno());

				result = pst.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return result;

	}

	@Override
	public int addDepartements(List<Dept> deptlist) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(DEPT_INSERT);
			for (Dept dept : deptlist) {
				pst.setInt(1, dept.getDeptno());
				pst.setString(2, dept.getDname());
				pst.setString(3, dept.getLocation());

				result = pst.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pst, con);
		}
		return result;
	}

}
