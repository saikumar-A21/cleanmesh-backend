package com.cleanmesh.cleanmesh_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="workers")
@Getter
@Setter
@NoArgsConstructor
public class Worker {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private WorkerStatus status=WorkerStatus.AVAILABLE;
	
	 public Worker(String name, String email, String phone) {
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        this.status = WorkerStatus.AVAILABLE;
	    }

}
