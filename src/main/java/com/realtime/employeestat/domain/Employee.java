package com.realtime.employeestat.domain;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private int empno;
	private String ename;
	private String job;
	private int mgr;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private LocalDate hiredate;
	private int sal;
	private int comm;
	private int deptno;
}

//class CustomDateDeserializer extends StdDeserializer<LocalDate> {
//
//	private static final long serialVersionUID = 1L;
//	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//
//	@Override
//	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
//			throws IOException, JsonProcessingException {
//
//		String date = p.getText();
//		LocalDate localDate = LocalDate.parse(date, formatter);
//
//		return localDate;
//	}
//	
//	   public CustomDateDeserializer() { 
//		   this._coerceNullToken(ctxt, isPrimitive)
//	   } 
//	   public CustomDateDeserializer(Class<LocalDate> t) { 
//	      super(t); 
//	   } 
// 
//
//}

class CustomDateDeserializer extends StdDeserializer<LocalDate> {
	   private static final long serialVersionUID = 1L;
	   private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	   public CustomDateDeserializer() { 
	      this(null); 
	   } 
	   public CustomDateDeserializer(Class<LocalDate> t) { 
	      super(t); 
	   } 
	   @Override 
	   public LocalDate deserialize(JsonParser parser, DeserializationContext context) 
	      throws IOException, JsonProcessingException { 
	      
	      String date = parser.getText(); 
	      return LocalDate.parse(date, formatter);    
	   } }
