package com.cleanmesh.cleanmesh_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanmesh.cleanmesh_backend.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long>{
	
	
}
