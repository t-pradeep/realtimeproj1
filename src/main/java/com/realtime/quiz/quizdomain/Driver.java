package com.realtime.quiz.quizdomain;

import com.fasterxml.jackson.core.JsonParseException;

public class Driver {

	public static void main(String[] args) {

		QuizServiceImpl impl = null;
		try {
			impl = new QuizServiceImpl();
		} catch (JsonParseException e) {
			e.printStackTrace();
		}
		impl.startService();
	}

}
