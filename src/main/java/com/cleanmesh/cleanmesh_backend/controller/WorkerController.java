package com.cleanmesh.cleanmesh_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleanmesh.cleanmesh_backend.dto.WorkerRequest;
import com.cleanmesh.cleanmesh_backend.dto.WorkerResponse;
import com.cleanmesh.cleanmesh_backend.service.WorkerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {
	
	private final WorkerService workerService;

	public WorkerController(WorkerService workerService) {
		
		this.workerService = workerService;
	}
	
	@PostMapping
	public ResponseEntity<WorkerResponse> createWorker(@Valid @RequestBody WorkerRequest request){
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(workerService.createWorker(request));
		
	}
	
	@GetMapping
	public ResponseEntity<List<WorkerResponse>> getAllWorkers(){
		return ResponseEntity.ok(workerService.getAllWorkers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkerResponse> getWorkerById(@PathVariable Long id){
		return ResponseEntity.ok(workerService.getWorkerById(id));
	}
	
	
}
