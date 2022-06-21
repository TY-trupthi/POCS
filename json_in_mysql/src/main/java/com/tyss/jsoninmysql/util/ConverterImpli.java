package com.tyss.jsoninmysql.util;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterImpli implements AttributeConverter<Object, String> {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Object attribute) {
		if (attribute != null) {
			try {
				return OBJECT_MAPPER.writeValueAsString(attribute);
			} catch (JsonProcessingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Object convertToEntityAttribute(String dbData) {
		if (dbData != null) {
			try {
				return OBJECT_MAPPER.readValue(dbData, Object.class);
			} catch (JsonProcessingException e) {
				return null;
			}
		} else {
			return null;
		}
	}
}
