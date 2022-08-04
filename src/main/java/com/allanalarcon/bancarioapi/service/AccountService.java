package com.allanalarcon.bancarioapi.service;

import java.util.List;

import com.allanalarcon.bancarioapi.service.dto.AccountDto;

public interface AccountService {

	public AccountDto save(Long clientId, AccountDto accountDto);
	public List<AccountDto> findAll();
	public AccountDto findById(Long id);
	public AccountDto update(Long id, AccountDto accountDto);
	public void deleteById(Long id);
	public List<AccountDto> findByNumberContaining(String number);
}
