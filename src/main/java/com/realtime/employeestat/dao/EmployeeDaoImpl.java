package com.lwl.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lwl.ems.domain.Dept;
import com.lwl.ems.domain.Employee;
import com.lwl.ems.dto.SalStat;
import com.lwl.ems.util.DbConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String DEPT_SAL_SAT = "select count(*) as count,max(sal) as max,min(sal) as min,avg(sal) as avg  from emp where deptno = ?";

	private DbConnectionUtil dbUtil = DbConnectionUtil.dbutil;

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
		}finally {
			dbUtil.close(rs, pst, con);
		}
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
