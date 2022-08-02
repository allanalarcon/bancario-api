package com.allanalarcon.bancarioapi.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allanalarcon.bancarioapi.entity.Client;
import com.allanalarcon.bancarioapi.service.dto.ClientDto;

@Component
public class ClientMapper {

	@Autowired
    private ModelMapper modelMapper;

	public ClientDto toDto(Client client) {
		ClientDto clientDto = modelMapper.map(client, ClientDto.class);
	    return clientDto;
	}

	public Client toEntity(ClientDto clientDto) {
		Client client = modelMapper.map(clientDto, Client.class);
	    return client;
	}
}
