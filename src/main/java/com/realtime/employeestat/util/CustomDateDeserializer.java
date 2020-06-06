package com.lwl.ems.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomDateDeserializer extends StdDeserializer<LocalDate> {
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
	}
}
