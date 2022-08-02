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
@Builder(builderMethodName = "clientBuilder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Client extends Person {

	@NotBlank(message = "Password is mandatory")
	private String password;

	@Column(columnDefinition = "boolean default true")
	private boolean isActive;
}
