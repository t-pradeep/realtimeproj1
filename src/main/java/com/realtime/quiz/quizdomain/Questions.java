package com.realtime.quiz.quizdomain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Questions {
	
	private String question;
	private List<String> options;
	private String answer;

}
