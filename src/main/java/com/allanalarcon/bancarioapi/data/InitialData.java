package com.allanalarcon.bancarioapi.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.allanalarcon.bancarioapi.entity.Account;
import com.allanalarcon.bancarioapi.entity.Client;
import com.allanalarcon.bancarioapi.repository.AccountRepository;
import com.allanalarcon.bancarioapi.repository.ClientRepository;

@Component
public class InitialData implements CommandLineRunner {

	private final ClientRepository clientRepository;
	private final AccountRepository accountRepository;

	@Autowired
	public InitialData(ClientRepository clientRepository, AccountRepository accountRepository) {
		this.clientRepository = clientRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client();
		client.setDni("0930699541");
		client.setName("Allan");
		client.setGender("M");
		client.setAge(26);
		client.setAddress("Gye");
		client.setPhone("0999999999");
		client.setPassword("12345");
		client.setActive(true);
		clientRepository.save(client);

		Account account = new Account();
		account.setClient(client);
		account.setNumber(12345);
		account.setType("Ahorro");
		account.setAmountInitial(100);
		account.setActive(true);
		accountRepository.save(account);
	}
}
