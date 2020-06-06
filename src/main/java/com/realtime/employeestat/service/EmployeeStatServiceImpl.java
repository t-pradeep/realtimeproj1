package com.realtime.employeestat.service;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.realtime.employeestat.dao.EmployeeDAO;
import com.realtime.employeestat.domain.Dept;
import com.realtime.employeestat.domain.Employee;
import com.realtime.employeestat.dto.SalStat;
import com.realtime.employeestat.util.ReadDataUtil;

public class EmployeeStatServiceImpl implements EmployeeStatService {

	public EmployeeStatServiceImpl() {

		List<Dept> deptlist = ReadDataUtil.loadDeptDataFromJSONFile();
		List<Employee> emplist = ReadDataUtil.loadEmpDataFromJSONFile();
		for (Dept d : deptlist) {
			for (Employee e : emplist) {
				if (e.getDeptno() == d.getDeptno()) {
					d.addEmployee(e);
				}
			}

		}
		if (EmployeeDAO.deptFindAll().stream().count()== 0)
			EmployeeDAO.addDept(deptlist);
		if (EmployeeDAO.empFindAll() .stream().count()== 0)
			EmployeeDAO.addEmp(emplist);
	}

	@Override
	public List<Employee> getEmployeeByDept(int deptno) {
		return EmployeeDAO.empFindAll().stream().filter(d -> d.getDeptno() == deptno).collect(Collectors.toList());
	}

	@Override
	public List<Employee> maxSalaryEmployees() {
		List<Employee> maxempls = new ArrayList<Employee>();
		int max_sal = EmployeeDAO.empFindAll().stream().mapToInt(e -> e.getSal()).max().getAsInt();
		List<Employee> list = EmployeeDAO.empFindAll().parallelStream().filter(e -> e.getSal() == max_sal)
				.collect(Collectors.toList());
		System.out.println("Max salary :" + max_sal + " and count of mas salary employees is :" + list.size());
		return list;
	}

	@Override
	public Dept getDeptWhichHasMaxNumberOfEmployee() {
		int max=0;
		int count=0;
		Dept rtnDept=null;
		for(Dept d:EmployeeDAO.deptFindAll()) {
			for(Employee e:EmployeeDAO.empFindAll()) {
				count=0;
				if(d.getDeptno()==e.getDeptno()) {
					count++;
				}
			}
			if(count>max) {
				rtnDept=d;
			}
		}
		return rtnDept;
		
//		int max_count = EmployeeDAO.empFindAll().stream().mapToInt(d -> d.getDeptno().count()).max().getAsInt();
//		Dept dept = EmployeeDAO.deptFindAll().stream().filter(d -> d.getEmpList().size() == max_count).findFirst()
//				.get();
//
//		return dept;
	}

	@Override
	public List<Employee> getEmployeesWithManagers() {

		return EmployeeDAO.empFindAll().stream().filter(e -> check(e.getEmpno())).collect(Collectors.toList());
	}

	private Boolean check(int empno) {
		for (Employee e : EmployeeDAO.empFindAll()) {
			if (empno == e.getMgr())
				return true;
		}
		return false;
	}

	@Override
	public SalStat getSalStatOfOrganization() {

		DoubleSummaryStatistics stat = EmployeeDAO.empFindAll().stream().mapToDouble(e -> e.getSal())
				.summaryStatistics();
		SalStat salSta = mapper(stat);
		// Log information
		return salSta;
	}

	@Override
	public SalStat getSalStatOfDept(int deptno) {
		DoubleSummaryStatistics stat = EmployeeDAO.empFindAll().stream().filter(d -> d.getDeptno() == deptno)
				.mapToDouble(e -> e.getSal()).summaryStatistics();
		SalStat salSta = mapper(stat);

		return salSta;
	}

	@Override
	public List<String> allManagerNames() {
		Set<Integer> set = EmployeeDAO.empFindAll().stream().map(e -> e.getMgr()).collect(Collectors.toSet());
		List<String> names = EmployeeDAO.empFindAll().stream().filter(e -> set.contains(e.getEmpno()))
				.map(e -> e.getEname()).collect(Collectors.toList());
		// log message
		return names;
	}

	@Override
	public List<Employee> whoJoinDayIs(String dayName) {
		// Work
		return null;
	}

	private SalStat mapper(DoubleSummaryStatistics stat) {
		return SalStat.builder().min(stat.getMin()).max(stat.getMax()).avg(stat.getAverage()).count(stat.getCount())
				.build();
	}

}
