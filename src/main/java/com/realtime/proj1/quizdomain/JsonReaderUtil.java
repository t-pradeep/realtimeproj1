package com.realtime.proj1.quizdomain;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	
	
	 public static Quiz readJSON() throws JsonParseException, JsonMappingException, IOException{
	      ObjectMapper mapper = new ObjectMapper();
	      Quiz Quiz = mapper.readValue(new File("quiz.json"), Quiz.class);
	      return Quiz;
	   }

}
