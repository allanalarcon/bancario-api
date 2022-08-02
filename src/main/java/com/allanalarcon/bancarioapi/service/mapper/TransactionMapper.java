package com.allanalarcon.bancarioapi.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allanalarcon.bancarioapi.entity.Transaction;
import com.allanalarcon.bancarioapi.service.dto.ReportDto;
import com.allanalarcon.bancarioapi.service.dto.TransactionDto;

@Component
public class TransactionMapper {

	@Autowired
    private ModelMapper modelMapper;

	public TransactionDto toDto(Transaction transaction) {
		TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
	    return transactionDto;
	}

	public Transaction toEntity(TransactionDto transactionDto) {
		Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
	    return transaction;
	}

	public ReportDto toReportDto(Transaction transaction) {
		ReportDto reportDto = modelMapper.map(transaction, ReportDto.class);
	    return reportDto;
	}
}
