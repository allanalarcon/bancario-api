package com.allanalarcon.bancarioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allanalarcon.bancarioapi.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	public boolean existsByDni(String dni);
}
