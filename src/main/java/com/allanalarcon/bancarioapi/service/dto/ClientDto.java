package com.allanalarcon.bancarioapi.service.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class ClientDto {

	private Long id;

	@NotBlank(message = "DNI is mandatory")
	private String dni;

	@NotBlank(message = "Name is mandatory")
	private String name;

	private String gender;
	private int age;
	private String address;
	private String phone;
	private boolean isActive;

	@NotBlank(message = "Password is mandatory")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
