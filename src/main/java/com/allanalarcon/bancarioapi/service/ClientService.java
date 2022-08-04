package com.allanalarcon.bancarioapi.service;

import java.util.List;

import com.allanalarcon.bancarioapi.service.dto.ClientDto;

public interface ClientService {

	public ClientDto save(ClientDto clientDto);
	public List<ClientDto> findAll();
	public ClientDto findById(Long id);
	public ClientDto update(Long id, ClientDto clientDto);
	public void deleteById(Long id);
	public List<ClientDto> findByNameContaining(String name);
}
