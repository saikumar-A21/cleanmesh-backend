package com.cleanmesh.cleanmesh_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleanmesh.cleanmesh_backend.dto.JobRequest;
import com.cleanmesh.cleanmesh_backend.dto.JobResponse;
import com.cleanmesh.cleanmesh_backend.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
	
	private final JobService jobService;

	public JobController(JobService jobService) {
		
		this.jobService = jobService;
	}
	
	@PostMapping
	public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobRequest request){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(jobService.createJob(request));
	}
	
	@GetMapping
	public ResponseEntity<List<JobResponse>> getAllJobs(){
		return ResponseEntity.ok(jobService.getAllJobs());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobResponse> getJobById(@PathVariable Long id){
		
		return ResponseEntity.ok(jobService.getJobById(id));
	}
	
	@PutMapping("/{jobId}/assign/{workerId}")
	public ResponseEntity<JobResponse> assignWorker(@PathVariable Long jobId,
			@PathVariable Long workerId){
		return ResponseEntity.ok(jobService.assignWorker(jobId, workerId));
	}
	
	@PutMapping("{jobId}/start")
	public ResponseEntity<JobResponse> startJob(@PathVariable Long jobId){
		
		return ResponseEntity.ok(jobService.startJob(jobId));
	}
 	
	@PutMapping("{jobId}/complete")
	public ResponseEntity<JobResponse> completeJob(@PathVariable Long jobId){
		
		return ResponseEntity.ok(jobService.completeJob(jobId));
	}

}
