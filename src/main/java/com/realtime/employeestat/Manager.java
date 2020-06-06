package com.realtime.employeestat;

import com.realtime.employeestat.service.EmployeeStatServiceImpl;

public class Manager {
	
	public static void main(String[] args) {
		EmployeeStatServiceImpl employeeStatServiceImpl= new EmployeeStatServiceImpl();
		
		System.out.println(employeeStatServiceImpl.allManagerNames());
		System.out.println(employeeStatServiceImpl.getDeptWhichHasMaxNumberOfEmployee());
		System.out.println(employeeStatServiceImpl.getEmployeeByDept(30));
		System.out.println(employeeStatServiceImpl.getEmployeesWithManagers());
		System.out.println(employeeStatServiceImpl.getSalStatOfDept(30));
		System.out.println(employeeStatServiceImpl.getSalStatOfOrganization());


	
	}
	
}
