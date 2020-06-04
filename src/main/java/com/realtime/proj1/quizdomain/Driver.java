package com.realtime.proj1.quizdomain;

public class Driver {

	public static void main(String[] args) {

		QuizServiceImpl impl= new QuizServiceImpl();
		impl.startService();
	}

}
