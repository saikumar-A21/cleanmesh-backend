package com.cleanmesh.cleanmesh_backend.dto;

import jakarta.validation.constraints.NotNull;

public class JobRequest {
	
	@NotNull(message="Booking ID is required")
	private Long bookingId;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	
}
