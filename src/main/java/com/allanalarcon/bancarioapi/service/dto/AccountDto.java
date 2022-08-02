package com.allanalarcon.bancarioapi.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class AccountDto {

	private Long id;
	private ClientDto client;

	@NotNull(message= "Number account is mandatory")
	@Range(min = 1, message="Number account is mandatory")
	private int number;

	@NotBlank(message = "Type is mandatory")
	private String type;

	private double amountInitial;
	private boolean isActive;
}
