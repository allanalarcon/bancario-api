package com.allanalarcon.bancarioapi.service.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class TransactionDto {

	private Long id;
	private AccountDto account;
	private Date date;

	@NotBlank(message = "Type is mandatory")
	private String type;

	@Range(min = 1, message="Amount greater than zero")
	private double amount;
	private double balance;
}
