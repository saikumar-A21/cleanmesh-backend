package com.cleanmesh.cleanmesh_backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="custome_id",nullable=false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="claning_service_id",nullable=false)
	private CleaningService cleaningService;
	
	@Column(nullable=false)
	private LocalDate bookingDate;
	
	@Column(nullable=false)
	private LocalTime bookingTime;
	
	@Column(nullable=false)
	private String address;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private BookingStatus status;
	
	@Column(nullable=false,updatable=false)
	private LocalDateTime createdAt;
	
	
	@PrePersist
	protected void onCreate() {
		createdAt=LocalDateTime.now();
		if(status==null) {
			status=BookingStatus.REQUESTED;
		}
	}

	
}
