package com.allanalarcon.bancarioapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Transaction extends Base {

	@ManyToOne(optional = false)
	@NotNull(message = "Account is mandatory")
	private Account account;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(nullable=false)
	@NotBlank(message = "Type is mandatory")
	private String type;

	@Range(min = 1, message="Amount greater than zero")
	private double amount;
	private double balance;
}
