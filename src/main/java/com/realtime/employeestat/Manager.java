package com.realtime.employeestat;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

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
