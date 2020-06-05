package com.realtime.coursestat;
import java.util.List;

import com.realtime.coursestat.domain.PlacedStatus;
import com.realtime.coursestat.domain.Student;
import com.realtime.coursestat.dto.PlacementCount;
import com.realtime.coursestat.service.StudentStatService;
import com.realtime.coursestat.service.StudentStatServiceImpl;
import com.realtime.coursestat.util.FileReaderUtil;

public class Manager {

	public static void main(String[] args) {
		List<Student> students = FileReaderUtil.getStudentDetailsFromFile("coursedata.csv");

		StudentStatService service = new StudentStatServiceImpl();

		long count = service.getCount((student) -> student.getPlacedStatus().equals(PlacedStatus.Y));
		System.out.println(count);
		
		List<Student> st=service.getStudentBy(student -> student.getDegree().equals("BE"));
		System.out.println("BE\n"+st);
		
		List<Student> st1=service.getStudentBy(student -> student.getDegree().equals("MCA"));
		System.out.println("MCA\n"+st1);
		
		List<Student> st2=service.getStudentBy(student -> student.getDegree().equals("BSc"));
		System.out.println("BSC\n"+st2);
		
		List<Student> st3=service.search("JOHNSON");
		System.out.println("\nSearch result: "+st3);
		
		PlacementCount placedOrNotPlaced = service.getPlacedAndNotPlacedCount();
		System.out.println("Placed count: "+placedOrNotPlaced.getPlaced());
		System.out.println("Not Placed count: "+placedOrNotPlaced.getNotPlaced());

	}

}
