package com.allanalarcon.bancarioapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

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
public class Person extends Base {

	@NotBlank(message = "DNI is mandatory")
	@Column(unique=true)
	private String dni;

	@NotBlank(message = "Name is mandatory")
	private String name;

	private String gender;
	private int age;
	private String address;
	private String phone;
}
