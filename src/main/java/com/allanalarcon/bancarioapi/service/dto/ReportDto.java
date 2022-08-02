package com.allanalarcon.bancarioapi.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReportDto {

	private Date date;
	private String client;
	private int accountNumber;
	private String accountType;
	private double amountInitial;
	private boolean isActive;
	private String type;
	private double amount;
	private double balance;
}
