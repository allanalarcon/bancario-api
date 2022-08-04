package com.allanalarcon.bancarioapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allanalarcon.bancarioapi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public boolean existsByNumber(String number);
	public List<Account> findByNumberContaining(String number);
}
