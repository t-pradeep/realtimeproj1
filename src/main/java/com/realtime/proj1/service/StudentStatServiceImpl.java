package com.realtime.proj1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.realtime.proj1.domain.PlacedStatus;
import com.realtime.proj1.domain.Student;
import com.realtime.proj1.dto.PlacementCount;
import com.realtime.proj1.util.FileReaderUtil;

public class StudentStatServiceImpl implements StudentStatService {

	private static final String FILE_NAME = "coursedata.csv";
	private static List<Student> studentList;

	static {
		studentList = FileReaderUtil.getStudentDetailsFromFile(FILE_NAME);
	}

	public List<Student> getStudentBy(Predicate<Student> predicate) {

		return studentList.stream().filter(predicate).collect(Collectors.toList());

	}

	public long getCount(Predicate<Student> predicate) {
		return studentList.stream().filter(predicate).count();
	}

	public List<Student> search(String str) {
		if(str!=null) {
		return studentList.stream().filter(s -> s.getName().toLowerCase().contains(str.toLowerCase())).collect(Collectors.toList());
		}
		return new ArrayList<Student>();
	}

	public PlacementCount getPlacedAndNotPlacedCount() {
		PlacementCount placementCount = new PlacementCount();
		long notPlaced=studentList.stream().filter(s -> s.getPlacedStatus().equals(PlacedStatus.N)).count();
		placementCount.setNotPlaced(notPlaced);
		placementCount.setPlaced(studentList.size()-notPlaced);

		return placementCount;
	}

}
