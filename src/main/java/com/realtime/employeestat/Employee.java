package com.realtime.employeestat;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@empno")
public class Employee {

	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private LocalDate hiredate;
	private int sal;
	private int comm;
	private int deptno;
}
