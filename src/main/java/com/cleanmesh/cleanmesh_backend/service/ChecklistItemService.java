package com.cleanmesh.cleanmesh_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cleanmesh.cleanmesh_backend.dto.ChecklistItemRequest;
import com.cleanmesh.cleanmesh_backend.dto.ChecklistItemResponse;
import com.cleanmesh.cleanmesh_backend.entity.ChecklistItem;
import com.cleanmesh.cleanmesh_backend.entity.Job;
import com.cleanmesh.cleanmesh_backend.exception.ResourceNotFoundException;
import com.cleanmesh.cleanmesh_backend.repo.ChecklistRepository;
import com.cleanmesh.cleanmesh_backend.repo.JobRepository;

import jakarta.transaction.Transactional;

@Service
public class ChecklistItemService {
	
	private final ChecklistRepository checklistRepository;
	private final JobRepository jobRepository;
	public ChecklistItemService(ChecklistRepository checklistRepository, JobRepository jobRepository) {
		
		this.checklistRepository = checklistRepository;
		this.jobRepository = jobRepository;
	}
	
	@Transactional
	public ChecklistItemResponse createChecklistItem(ChecklistItemRequest request) {
		
		Job job= jobRepository.findById(request.getJobId())
				.orElseThrow(()-> new ResourceNotFoundException("Job is not Found with id "+request.getJobId()));
		
		ChecklistItem item=new ChecklistItem(job,request.getTaskName());
		
		ChecklistItem saveditem=checklistRepository.save(item);
		
		return ChecklistItemResponse.fromEntity(saveditem);
		
	}
	
	public List<ChecklistItemResponse> getChecklistById(Long jobId){
		
		if (!jobRepository.existsById(jobId)) {
            throw new ResourceNotFoundException(
                    "Job not found with id: " + jobId
            );
        }
		
		return checklistRepository.findByJobId(jobId)
				.stream()
				.map(ChecklistItemResponse::fromEntity)
				.toList();
		
	}
	
	@Transactional
	public ChecklistItemResponse completeChecklistItem(Long itemId) {
		ChecklistItem item=checklistRepository.findById(itemId)
				.orElseThrow(()-> new ResourceNotFoundException("Checklist item not found with id "+itemId));
		
		if(item.isCompleted()) {
			throw new IllegalStateException("ChecklistItem is aready complted");
		}
		
		item.setCompleted(true);
		item.setCreatedAt(LocalDateTime.now());
		
		ChecklistItem saveditem= checklistRepository.save(item);
		return ChecklistItemResponse.fromEntity(saveditem);
				
	}
	
	

}
