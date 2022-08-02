package com.allanalarcon.bancarioapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.allanalarcon.bancarioapi.service.dto.ReportDto;
import com.allanalarcon.bancarioapi.service.dto.TransactionDto;

public interface TransactionService {

	public TransactionDto save(Long accountId, TransactionDto transactionDto);
	public List<TransactionDto> findAll();
	public TransactionDto findById(Long id);
	public TransactionDto update(Long id, TransactionDto transactionDto);
	public void deleteById(Long id);
	public TransactionDto findLastByAccountId(Long accountId);
	public List<ReportDto> findAllByAccountClientIdAndDateBetween(Long clientId, @Param("dateTransactionStart") Date dateTransactionStart, @Param("dateTransactionEnd") Date dateTransactionEnd);
}
