package com.allanalarcon.bancarioapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.allanalarcon.bancarioapi.exception.ResourceNotFoundException;
import com.allanalarcon.bancarioapi.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@DisplayName("Client controller")
public class ClientControllerTest {

	@InjectMocks
	private ClientController clientController;

	@Mock
	private ClientService clientService;

	private MockMvc mockMvc;
	private final String relativePath = "/api/clients";

	@BeforeEach
	void setUp() throws JsonProcessingException {
		openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	}

	@Test
	@DisplayName("GET: " + relativePath + ": ✔ Status code 200")
	void getClients_statusOk() throws Exception {
		mockMvc.perform(
				get(relativePath)
				.characterEncoding("utf-8")
				)
		.andExpect(status().isOk());
	}

	@Test
	@DisplayName("GET: " + relativePath + ": ✔ Status code 404")
	void getClient_statusNotFound() throws Exception {
		when(clientService.findById(any())).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(
				get(relativePath + "/2")
				.characterEncoding("utf-8")
				)
		.andExpect(status().is(404));
	}
}
