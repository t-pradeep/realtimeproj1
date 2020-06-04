package com.realtime.proj1.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.realtime.proj1.domain.CCStatus;
import com.realtime.proj1.domain.PlacedStatus;
import com.realtime.proj1.domain.Student;



public final class FileReaderUtil {

	private FileReaderUtil() {
		
	}
	public static List<Student> getStudentDetailsFromFile(String fileName) {
		List<Student> students = new ArrayList<Student>();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fileName));
			students = allLines.stream().skip(1).map(line -> convertIntoStudent(line)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return students;
	}

	private static Student convertIntoStudent(String line) {
		int c = 0;
		String[] arr = line.split(",");
		String name = arr[c++];
		String batch = arr[c++];
		CCStatus ccStatus = CCStatus.valueOf(arr[c++]);
		PlacedStatus placedStatus = PlacedStatus.valueOf(arr[c++]);
		String degree = arr[c++];
		float score = Float.parseFloat(arr[c++]);
		

		Student student = Student.builder().name(name).placedStatus(placedStatus).ccStatus(ccStatus).batch(batch)
		
				.degree(degree).score(score).build();
		
	return student;
	}
}
