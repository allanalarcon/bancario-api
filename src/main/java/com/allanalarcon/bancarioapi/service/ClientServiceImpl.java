package com.allanalarcon.bancarioapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanalarcon.bancarioapi.entity.Client;
import com.allanalarcon.bancarioapi.exception.BadRequestException;
import com.allanalarcon.bancarioapi.exception.ResourceNotFoundException;
import com.allanalarcon.bancarioapi.repository.ClientRepository;
import com.allanalarcon.bancarioapi.service.dto.ClientDto;
import com.allanalarcon.bancarioapi.service.mapper.ClientMapper;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@Override
	public ClientDto save(ClientDto clientDto) {
		if (clientRepository.existsByDni(clientDto.getDni())) {
			throw(new BadRequestException("Client already exists"));
		}
		Client client = clientRepository.save(clientMapper.toEntity(clientDto));
		return clientMapper.toDto(client);
	}

	@Override
	public List<ClientDto> findAll() {
		List<Client> clients = clientRepository.findAll();
		return clients.stream()
				.map(client -> clientMapper.toDto(client))
				.collect(Collectors.toList());
	}

	@Override
	public ClientDto findById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found: " + id));
		return clientMapper.toDto(client);
	}

	@Override
	public ClientDto update(Long id, ClientDto clientDto) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found: " + id));

		if (clientDto.getDni() != null) {
			throw(new BadRequestException("Cannot change DNI"));
		}
		if (clientDto.getPassword() != null) {
			throw(new BadRequestException("Cannot change Password"));
		}
		if (clientDto.getName() != null) {
			client.setName(clientDto.getName());
		}
		if (clientDto.getGender() != null) {
			client.setGender(clientDto.getGender());
		}
		if (clientDto.getAge() != 0) {
			client.setAge(clientDto.getAge());
		}
		if (clientDto.getAddress() != null) {
			client.setAddress(clientDto.getAddress());
		}
		if (clientDto.getPhone() != null) {
			client.setPhone(clientDto.getPhone());
		}
		if (clientDto.isActive() != client.isActive()) {
			client.setActive(clientDto.isActive());
		}

		clientRepository.save(client);
		return clientMapper.toDto(client);
	}

	@Override
	public void deleteById(Long id) {
		clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found" + id));
		clientRepository.deleteById(id);
	}

}
