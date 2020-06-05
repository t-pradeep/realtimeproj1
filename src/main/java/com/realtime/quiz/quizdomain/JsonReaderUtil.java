package com.realtime.quiz.quizdomain;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	private JsonReaderUtil() {

	}

	private static final String JSON_FILE = "/quiz.json";

	public static Quiz loadDataFromJSONFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		Quiz quiz = null;
		try {
			quiz = objectMapper.readValue(JsonReaderUtil.class.getResourceAsStream(JSON_FILE), Quiz.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return quiz;
	}

	public static Quiz loadDataFromYAMLFile() {
		throw new UnsupportedOperationException("");
	}

}
