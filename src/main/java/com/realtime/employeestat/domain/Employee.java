package com.realtime.employeestat.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.realtime.employeestat.util.CustomDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Employee {

	private int empno;
	private String ename;
	private String job;
	private int mgr;
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private LocalDate hiredate;
	private int sal;
	private int comm;
	private int deptno;
}

