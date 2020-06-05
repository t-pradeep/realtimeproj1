package com.realtime.coursestat.service;

import java.util.List;
import java.util.function.Predicate;

import com.realtime.coursestat.domain.Student;
import com.realtime.coursestat.dto.PlacementCount;

public interface StudentStatService {

	public List<Student> getStudentBy(Predicate<Student> student);
	public long getCount(Predicate<Student> student);
	public List<Student> search(String str);
	public PlacementCount getPlacedAndNotPlacedCount();
}
