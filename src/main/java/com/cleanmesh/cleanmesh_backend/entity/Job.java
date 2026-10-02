package com.cleanmesh.cleanmesh_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="worker_id")
	private Worker worker;
	
	
	@OneToOne
	@JoinColumn(name="booking_id", nullable=false, unique=true)
	private Booking booking;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private JobStatus status=JobStatus.COMPLETED;
	
	@Column(nullable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt=LocalDateTime.now();
		if(status==null) {
			status=JobStatus.COMPLETED;
		}
	}
	
	
	

}
