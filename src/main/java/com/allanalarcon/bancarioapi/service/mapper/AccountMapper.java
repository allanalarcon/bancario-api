package com.allanalarcon.bancarioapi.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allanalarcon.bancarioapi.entity.Account;
import com.allanalarcon.bancarioapi.service.dto.AccountDto;

@Component
public class AccountMapper {

	@Autowired
    private ModelMapper modelMapper;

	public AccountDto toDto(Account account) {
		AccountDto accountDto = modelMapper.map(account, AccountDto.class);
	    return accountDto;
	}

	public Account toEntity(AccountDto accountDto) {
		Account account = modelMapper.map(accountDto, Account.class);
	    return account;
	}
}
