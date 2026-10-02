package com.cleanmesh.cleanmesh_backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanmesh.cleanmesh_backend.entity.ChecklistItem;

public interface ChecklistRepository extends JpaRepository<ChecklistItem,Long> {
	
	List<ChecklistItem> findByJobId(Long jobId);
	
	

}
