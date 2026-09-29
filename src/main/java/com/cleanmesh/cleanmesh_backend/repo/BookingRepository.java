package com.cleanmesh.cleanmesh_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanmesh.cleanmesh_backend.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{
	
	

}
