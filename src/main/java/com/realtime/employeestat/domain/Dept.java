package com.lwl.ems.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@deptno")
public class Dept implements Serializable {

 private Integer deptno;
 private String dname;
 private String location;
 @Setter(value = AccessLevel.NONE)
 private List<Employee> empList = new ArrayList<Employee>();
 
 public Employee addEmployee(Employee emp) {
  this.empList.add(emp);
  return emp;
 }
 
}