package com.realtime.employeestat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeStatServiceImpl implements EmployeeStatService {
	private List<Dept> deptlist;
	private List<Employee> emplist;

	public EmployeeStatServiceImpl() {
		deptlist = ReadDataUtil.loadDeptDataFromJSONFile();
		emplist = ReadDataUtil.loadEmpDataFromJSONFile();
		for (Dept d : deptlist) {
			for (Employee e : emplist) {
				if (e.getDeptno() == d.getDeptno()) {
					d.addEmployee(e);
				}
			}

		}
	}

	@Override
	public List<Employee> getEmployeeByDept(int deptno) {
		return emplist.stream().filter(d -> d.getDeptno() == deptno).collect(Collectors.toList());
	}

	@Override
	public List<Employee> maxSalaryEmployees() {
		List<Employee> maxempls = new ArrayList<Employee>();
		int max_sal = this.emplist.stream().mapToInt(e -> e.getSal()).max().getAsInt();
		List<Employee> list = this.emplist.parallelStream().filter(e -> e.getSal() == max_sal)
				.collect(Collectors.toList());
		System.out.println("Max salary :" + max_sal + " and count of mas salary employees is :" + list.size());
		return list;
	}

	@Override
	public Dept getDeptWhichHasMaxNumberOfEmployee() {

		int max_count = this.deptlist.stream().mapToInt(d -> d.getEmpList().size()).max().getAsInt();
		Dept dept = this.deptlist.stream().filter(d -> d.getEmpList().size() == max_count).findFirst().get();

		return dept;
	}

	@Override
	public List<Employee> getEmployeesWithManagers() {

		return emplist.stream().filter(e -> check(e.getEmpno())).collect(Collectors.toList());
	}

	private Boolean check(int empno) {
		for (Employee e : emplist) {
			if (empno == e.getMgr())
				return true;
		}
		return false;
	}

	@Override
	public SalStat getSalStatOfOrganization() {

		DoubleSummaryStatistics stat = emplist.stream().mapToDouble(e -> e.getSal()).summaryStatistics();
		SalStat salSta = mapper(stat);
		//Log information
		return salSta;
	}

	@Override
	public SalStat getSalStatOfDept(int deptno) {
		DoubleSummaryStatistics stat = emplist.stream().filter(d -> d.getDeptno() == deptno)
				.mapToDouble(e -> e.getSal()).summaryStatistics();
		SalStat salSta = mapper(stat);

		return salSta;
	}

	@Override
	public List<String> allManagerNames() {
		Set<Integer> set = emplist.stream().map(e->e.getMgr()).collect(Collectors.toSet());
		List<String> names = emplist.stream().filter(e->set.contains(e.getEmpno())).map(e->e.getEname()).collect(Collectors.toList());
		//log message 
		return names;
	}

	@Override
	public List<Employee> whoJoinDayIs(String dayName) {
		//Work
		return null;
	}

	private SalStat mapper(DoubleSummaryStatistics stat) {
		return SalStat.builder().min(stat.getMin()).max(stat.getMax()).avg(stat.getAverage()).count(stat.getCount())
				.build();
	}

}
