package com.allanalarcon.bancarioapi.data;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.allanalarcon.bancarioapi.entity.Account;
import com.allanalarcon.bancarioapi.entity.Client;
import com.allanalarcon.bancarioapi.entity.Transaction;
import com.allanalarcon.bancarioapi.repository.AccountRepository;
import com.allanalarcon.bancarioapi.repository.ClientRepository;
import com.allanalarcon.bancarioapi.repository.TransactionRepository;

@Component
public class InitialData implements CommandLineRunner {

	private final ClientRepository clientRepository;
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	@Autowired
	public InitialData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.clientRepository = clientRepository;
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client();
		client.setDni("0999999998");
		client.setName("Allan");
		client.setGender("M");
		client.setAge(26);
		client.setAddress("Gye");
		client.setPhone("0999999999");
		client.setPassword("12345");
		client.setActive(true);
		clientRepository.save(client);

		Client client2 = new Client();
		client2.setDni("0999999999");
		client2.setName("Samuel");
		client2.setGender("M");
		client2.setAge(26);
		client2.setAddress("Gye");
		client2.setPhone("0999999999");
		client2.setPassword("12345");
		client2.setActive(true);
		clientRepository.save(client2);

		Account account = new Account();
		account.setClient(client);
		account.setNumber("12345");
		account.setType("Ahorro");
		account.setAmountInitial(100);
		account.setActive(true);
		accountRepository.save(account);

		Account account2 = new Account();
		account2.setClient(client2);
		account2.setNumber("12346");
		account2.setType("Corriente");
		account2.setAmountInitial(150);
		account2.setActive(true);
		accountRepository.save(account2);

		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setDate(new Date(new Date().getTime() - 86400000));
		transaction.setType("Debit");
		transaction.setAmount(20);
		transaction.setBalance(80);
		transactionRepository.save(transaction);

		Transaction transaction2 = new Transaction();
		transaction2.setAccount(account2);
		transaction2.setDate(new Date(new Date().getTime() - (86400000*2)));
		transaction2.setType("Credit");
		transaction2.setAmount(20);
		transaction2.setBalance(170);
		transactionRepository.save(transaction2);
	}
}
