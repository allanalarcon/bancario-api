package com.allanalarcon.bancarioapi;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.allanalarcon.bancarioapi.entity.Transaction;
import com.allanalarcon.bancarioapi.service.dto.ReportDto;

@SpringBootApplication
public class BancarioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancarioApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Transaction, ReportDto> propertyMapper = modelMapper.createTypeMap(Transaction.class, ReportDto.class);
		propertyMapper.addMappings(
			      mapper -> {
			    	  mapper.map(src -> src.getAccount().getClient().getName(), ReportDto::setClient);
			    	  mapper.map(src -> src.getAccount().getNumber(), ReportDto::setAccountNumber);
			    	  mapper.map(src -> src.getAccount().getType(), ReportDto::setAccountType);
			    	  mapper.map(src -> src.getAccount().getAmountInitial(), ReportDto::setAmountInitial);
			    	  mapper.map(src -> src.getAccount().isActive(), ReportDto::setActive);
			      }
			    );
	    return modelMapper;
	}
}
