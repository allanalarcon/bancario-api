package com.allanalarcon.bancarioapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Account extends Base {

	@ManyToOne(optional = false)
	@NotNull(message = "Client is mandatory")
	private Client client;

	@Column(unique=true)
	@NotNull(message= "Number account is mandatory")
	@Range(min = 1, message="Number account is mandatory")
	private int number;

	@NotBlank(message = "Type is mandatory")
	private String type;

	@Column(columnDefinition = "double default 0")
	private double amountInitial;

	@Column(columnDefinition = "boolean default true")
	private boolean isActive;
}
