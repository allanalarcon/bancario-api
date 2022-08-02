package com.allanalarcon.bancarioapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.allanalarcon.bancarioapi.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public Transaction findTopByAccountIdOrderByIdDesc(Long accountId);
	public List<Transaction> findAllByAccountClientIdAndDateBetween(Long clientId, @Param("dateTransactionStart") Date dateTransactionStart, @Param("dateTransactionEnd") Date dateTransactionEnd);
}
