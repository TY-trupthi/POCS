package com.tyss.jsoninmysql.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tyss.jsoninmysql.util.ConverterImpli;

import lombok.Data;

@Data
@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String name;

	@Convert(converter = ConverterImpli.class)
	private Object otherDetails;

}
