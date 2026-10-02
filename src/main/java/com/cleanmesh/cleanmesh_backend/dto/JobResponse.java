package com.cleanmesh.cleanmesh_backend.dto;

import java.time.LocalDateTime;

import com.cleanmesh.cleanmesh_backend.entity.Booking;
import com.cleanmesh.cleanmesh_backend.entity.Job;
import com.cleanmesh.cleanmesh_backend.entity.JobStatus;

public record JobResponse(Long id , Long bookingId, Long CustomerId, 
		String customerName, String cleaningServiceName, Long workerId,
		String workerName, JobStatus status, LocalDateTime createdAt) {
	
	
	static public JobResponse fromEntity(Job job) {
		Booking booking = job.getBooking();
		
		return new JobResponse(job.getId(),
				booking.getId(),
				booking.getCustomer().getId(),
				booking.getCustomer().getName(),
				booking.getCleaningService().getName(),
				job.getWorker()!=null?job.getWorker().getId():null,
				job.getWorker()!=null?job.getWorker().getName():null,
				job.getStatus(),
				job.getCreatedAt()
				);
		
	}

}
