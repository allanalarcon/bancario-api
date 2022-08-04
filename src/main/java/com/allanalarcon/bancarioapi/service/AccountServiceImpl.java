package com.allanalarcon.bancarioapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanalarcon.bancarioapi.entity.Account;
import com.allanalarcon.bancarioapi.exception.BadRequestException;
import com.allanalarcon.bancarioapi.exception.ResourceNotFoundException;
import com.allanalarcon.bancarioapi.repository.AccountRepository;
import com.allanalarcon.bancarioapi.service.dto.AccountDto;
import com.allanalarcon.bancarioapi.service.dto.ClientDto;
import com.allanalarcon.bancarioapi.service.mapper.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final AccountMapper accountMapper;
	private final ClientService clientService;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, ClientService clientService) {
		this.accountRepository = accountRepository;
		this.accountMapper = accountMapper;
		this.clientService = clientService;
	}

	@Override
	public AccountDto save(Long clientId, AccountDto accountDto) {
		if (accountRepository.existsByNumber(accountDto.getNumber())) {
			throw(new BadRequestException("Account already exists"));
		}
		ClientDto clientDto = clientService.findById(clientId);
		accountDto.setClient(clientDto);
		Account account = accountRepository.save(accountMapper.toEntity(accountDto));
		return accountMapper.toDto(account);
	}

	@Override
	public List<AccountDto> findAll() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream()
				.map(account -> accountMapper.toDto(account))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDto findById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));
		return accountMapper.toDto(account);
	}

	@Override
	public AccountDto update(Long id, AccountDto accountDto) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));

		if (accountDto.getNumber() != null) {
			throw(new BadRequestException("Cannot change Number"));
		}
		if (accountDto.getClient() != null) {
			throw(new BadRequestException("Cannot change Client"));
		}
		if (accountDto.getAmountInitial() != 0) {
			throw(new BadRequestException("Cannot change initial Amount"));
		}
		if (accountDto.getType() != null) {
			throw(new BadRequestException("Cannot change Type"));
		}
		if (accountDto.isActive() != account.isActive()) {
			account.setActive(accountDto.isActive());
		}

		accountRepository.save(account);
		return accountMapper.toDto(account);
	}

	@Override
	public void deleteById(Long id) {
		accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));
		accountRepository.deleteById(id);
	}

	@Override
	public List<AccountDto> findByNumberContaining(String number) {
		List<Account> accounts = accountRepository.findByNumberContaining(number);
		return accounts.stream()
				.map(account -> accountMapper.toDto(account))
				.collect(Collectors.toList());
	}

}
