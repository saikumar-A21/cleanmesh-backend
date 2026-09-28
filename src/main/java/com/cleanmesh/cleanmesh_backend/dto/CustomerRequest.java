package com.cleanmesh.cleanmesh_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRequest {
	
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	
	private String phone;

}
