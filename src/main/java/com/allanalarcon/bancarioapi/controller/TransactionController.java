package com.allanalarcon.bancarioapi.controller;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allanalarcon.bancarioapi.service.TransactionService;
import com.allanalarcon.bancarioapi.service.dto.ReportDto;
import com.allanalarcon.bancarioapi.service.dto.TransactionDto;
import com.allanalarcon.bancarioapi.utils.ReportPdf;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/accounts/{accountId}/transactions")
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionDto create(@Valid @RequestBody TransactionDto transactionDto, @PathVariable Long accountId){
		return transactionService.save(accountId, transactionDto);
	}

	@GetMapping("/transactions")
	@ResponseStatus(HttpStatus.OK)
	public List<TransactionDto> list(@RequestParam(required = false) String name){
		return name == null ? transactionService.findAll() : transactionService.findByAccountClientNameContainingIgnoreCase(name);
	}

	@GetMapping("/transactions/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TransactionDto get(@PathVariable Long id){
		return transactionService.findById(id);
	}

	@GetMapping("/clients/{clientId}/report")
	@ResponseStatus(HttpStatus.OK)
	public List<ReportDto> report(@PathVariable Long clientId, @RequestParam String dateTransactionStart, @RequestParam String dateTransactionEnd) throws ParseException{
		return transactionService.findAllByAccountClientIdAndDateBetween(clientId, new SimpleDateFormat("yyyy-MM-dd").parse(dateTransactionStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateTransactionEnd));
	}

	@GetMapping("/clients/{clientId}/report/pdf")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<InputStreamResource> reportPDF(@PathVariable Long clientId, @RequestParam String dateTransactionStart, @RequestParam String dateTransactionEnd) throws ParseException{
		List<ReportDto> transactions = transactionService.findAllByAccountClientIdAndDateBetween(clientId, new SimpleDateFormat("yyyy-MM-dd").parse(dateTransactionStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateTransactionEnd));

		ByteArrayInputStream pdf = ReportPdf.transactionsReport(transactions);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=transactions.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
	}
}
