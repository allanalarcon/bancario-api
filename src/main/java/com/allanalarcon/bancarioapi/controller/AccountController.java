package com.allanalarcon.bancarioapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allanalarcon.bancarioapi.service.AccountService;
import com.allanalarcon.bancarioapi.service.dto.AccountDto;

@RestController
@RequestMapping("api")
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/clients/{clientId}/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDto create(@Valid @RequestBody AccountDto accountDto, @PathVariable Long clientId){
		return accountService.save(clientId, accountDto);
	}

	@GetMapping("/accounts")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountDto> list(){
		return accountService.findAll();
	}

	@GetMapping("/accounts/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto get(@PathVariable Long id){
		return accountService.findById(id);
	}

	@PutMapping("/accounts/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDto update(@RequestBody AccountDto accountDto, @PathVariable Long id){
		return accountService.update(id, accountDto);
	}

	@DeleteMapping("/accounts/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		accountService.deleteById(id);
	}
}
