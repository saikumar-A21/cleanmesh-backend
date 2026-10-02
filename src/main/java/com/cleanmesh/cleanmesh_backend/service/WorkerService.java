package com.cleanmesh.cleanmesh_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cleanmesh.cleanmesh_backend.dto.WorkerRequest;
import com.cleanmesh.cleanmesh_backend.dto.WorkerResponse;
import com.cleanmesh.cleanmesh_backend.entity.Worker;
import com.cleanmesh.cleanmesh_backend.exception.ResourceNotFoundException;
import com.cleanmesh.cleanmesh_backend.repo.WorkerRepository;

@Service
public class WorkerService {
	
	private final WorkerRepository workerRepository;

	public WorkerService(WorkerRepository workerRepository) {
		
		this.workerRepository = workerRepository;
	}
	
	public WorkerResponse createWorker(WorkerRequest request) {
		Worker worker=new Worker(request.getName(),
				request.getEmail(),
				request.getPhone());
		Worker savedWorker=workerRepository.save(worker);
		return WorkerResponse.fromEntity(savedWorker);
	}
	
	public List<WorkerResponse> getAllWorkers() {
		return workerRepository.findAll().stream()
				.map(WorkerResponse::fromEntity)
				.toList();
	}
	
	public WorkerResponse getWorkerById(Long id) {
		Worker worker= workerRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Worker not found by Id :"+id));
		return WorkerResponse.fromEntity(worker);
	}
	
	

}
