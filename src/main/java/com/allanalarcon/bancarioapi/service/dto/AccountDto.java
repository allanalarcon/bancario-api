package com.allanalarcon.bancarioapi.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountDto {

	private Long id;
	private ClientDto client;

	@NotBlank(message= "Number account is mandatory")
	private String number;

	@NotBlank(message = "Type is mandatory")
	private String type;

	private double amountInitial;
	private boolean isActive;
}
