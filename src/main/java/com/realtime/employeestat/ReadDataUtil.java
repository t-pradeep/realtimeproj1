package com.realtime.employeestat;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataUtil {

	private ReadDataUtil() {

	}

	public static List<Dept> loadDeptDataFromJSONFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Dept> deptlist = null;
		try {
			deptlist = Arrays
					.asList(objectMapper.readValue(ReadDataUtil.class.getResourceAsStream("/dept.json"), Dept[].class));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptlist;
	}

	public static List<Employee> loadEmpDataFromJSONFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Employee> emplist = null;
		try {
			emplist = Arrays.asList(
					objectMapper.readValue(ReadDataUtil.class.getResourceAsStream("/employee.json"), Employee[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emplist;
	}

}
