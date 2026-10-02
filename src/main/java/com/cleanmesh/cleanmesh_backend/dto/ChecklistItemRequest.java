package com.cleanmesh.cleanmesh_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChecklistItemRequest {
	
	
	@NotNull(message="Job id is required")
	private Long jobId;
	
	@NotBlank(message="Task name is required")
	private String taskName;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	

}
