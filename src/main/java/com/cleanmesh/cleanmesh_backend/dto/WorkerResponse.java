package com.cleanmesh.cleanmesh_backend.dto;

import com.cleanmesh.cleanmesh_backend.entity.Worker;
import com.cleanmesh.cleanmesh_backend.entity.WorkerStatus;

public record WorkerResponse(Long Id, String name, String email,String phone, WorkerStatus status) {
	
	public static WorkerResponse fromEntity(Worker worker) {
		return new WorkerResponse(
				worker.getId(),
				worker.getName(),
				worker.getEmail(),
				worker.getPhone(),
				worker.getStatus()
				);
	}

}
