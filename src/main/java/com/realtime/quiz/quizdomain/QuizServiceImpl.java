package com.realtime.quiz.quizdomain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class QuizServiceImpl implements QuizService {
	private Quiz quiz;
	private List<String> enteredAnswers;
	private int correctCount;

	public QuizServiceImpl() throws JsonParseException {
		enteredAnswers=new ArrayList<String>();
		correctCount=0;
		quiz = JsonReaderUtil.loadDataFromJSONFile();
	}

	@Override
	public void startService() {
		for (Questions questions : quiz.getQuestions()) {
			System.out.println("Question: ");
			System.out.println(questions.getQuestion());
			System.out.println("Options: ");
			for (String options : questions.getOptions()) {
				System.out.print(options+"\t");
			}
			System.out.print("\nEnter Answer: ");
			Scanner scan = new Scanner(System.in);
			String txt=scan.next();
			enteredAnswers.add(txt);
			if(txt.trim().toLowerCase().equalsIgnoreCase(questions.getAnswer().trim().toLowerCase()))
				correctCount++;
			System.out.println("Correct Answer: "+questions.getAnswer());
			System.out.println("----------------------");
		}
		System.out.println("Summary of Quiz");
		System.out.println("___________________________________");
		System.out.println("Number of answers correct: "+correctCount+" of "+quiz.getQuestions().size());
		System.out.println("Percentage: "+((double)correctCount/quiz.getQuestions().size())*100.0);

	}

}
