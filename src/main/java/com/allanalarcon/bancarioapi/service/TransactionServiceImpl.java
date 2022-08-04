package com.allanalarcon.bancarioapi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanalarcon.bancarioapi.entity.Transaction;
import com.allanalarcon.bancarioapi.exception.BadRequestException;
import com.allanalarcon.bancarioapi.exception.ResourceNotFoundException;
import com.allanalarcon.bancarioapi.repository.TransactionRepository;
import com.allanalarcon.bancarioapi.service.dto.AccountDto;
import com.allanalarcon.bancarioapi.service.dto.ReportDto;
import com.allanalarcon.bancarioapi.service.dto.TransactionDto;
import com.allanalarcon.bancarioapi.service.mapper.TransactionMapper;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final TransactionMapper transactionMapper;
	private final AccountService accountService;

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountService accountService) {
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
		this.accountService = accountService;
	}

	@Override
	public TransactionDto save(Long accountId, TransactionDto transactionDto) {
		AccountDto accountDto = accountService.findById(accountId);
		transactionDto.setAccount(accountDto);
		transactionDto.setDate(new Date());
		double balance = findLastByAccountId(accountId) != null ? findLastByAccountId(accountId).getBalance() : accountDto.getAmountInitial();
		if (transactionDto.getType().equals("Debit")) {
			if (balance < transactionDto.getAmount()) {
				throw(new BadRequestException("Saldo no disponible"));
			}
			transactionDto.setBalance(balance - transactionDto.getAmount());
		}
		else if (transactionDto.getType().equals("Credit")) {
			transactionDto.setBalance(balance + transactionDto.getAmount());
		}
		else {
			throw(new BadRequestException("Incorrect type"));
		}
		Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDto));
		return transactionMapper.toDto(transaction);
	}

	@Override
	public List<TransactionDto> findAll() {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions.stream()
				.map(transaction -> transactionMapper.toDto(transaction))
				.collect(Collectors.toList());
	}

	@Override
	public TransactionDto findById(Long id) {
		Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id));
		return transactionMapper.toDto(transaction);
	}

	@Override
	public TransactionDto update(Long id, TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id));
		transactionRepository.deleteById(id);
	}

	@Override
	public TransactionDto findLastByAccountId(Long accountId) {
		Transaction transaction = transactionRepository.findTopByAccountIdOrderByIdDesc(accountId);
		return transaction == null ? null : transactionMapper.toDto(transaction);
	}

	@Override
	public List<ReportDto> findAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
			Date dateTransactionEnd) {
		List<Transaction> transactions = transactionRepository.findAllByAccountClientIdAndDateBetween(clientId, dateTransactionStart, dateTransactionEnd);
		return transactions.stream()
				.map(transaction -> transactionMapper.toReportDto(transaction))
				.collect(Collectors.toList());
	}

	@Override
	public List<TransactionDto> findByAccountClientNameContainingIgnoreCase(String name) {
		List<Transaction> transactions = transactionRepository.findByAccountClientNameContainingIgnoreCase(name);
		return transactions.stream()
				.map(transaction -> transactionMapper.toDto(transaction))
				.collect(Collectors.toList());
	}

}
