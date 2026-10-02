package com.cleanmesh.cleanmesh_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class WorkerRequest {
	
	
	@NotBlank(message="Worker name is required")
	private String name;
	
	@NotBlank(message="Worker email is required")
	@Email(message="Inavlid email format")
	private String email;
	
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	

}
