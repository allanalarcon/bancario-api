package com.allanalarcon.bancarioapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allanalarcon.bancarioapi.service.ClientService;
import com.allanalarcon.bancarioapi.service.dto.ClientDto;

@RestController
@RequestMapping("api/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDto create(@Valid @RequestBody ClientDto clientDto){
		return clientService.save(clientDto);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClientDto> list(@RequestParam(required = false) String name){
		return name == null ? clientService.findAll() : clientService.findByNameContaining(name);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClientDto get(@PathVariable Long id){
		return clientService.findById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClientDto update(@RequestBody ClientDto clientDto, @PathVariable Long id){
		return clientService.update(id, clientDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id){
		clientService.deleteById(id);
	}
}
