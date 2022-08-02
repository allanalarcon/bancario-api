package com.allanalarcon.bancarioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allanalarcon.bancarioapi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public boolean existsByNumber(int number);
}
