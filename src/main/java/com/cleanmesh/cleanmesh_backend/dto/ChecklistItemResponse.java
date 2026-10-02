package com.cleanmesh.cleanmesh_backend.dto;

import java.time.LocalDateTime;

import com.cleanmesh.cleanmesh_backend.entity.ChecklistItem;

public record ChecklistItemResponse(Long id,Long jobId,
		String taskName, boolean Completed, LocalDateTime createdAt ) {
	
	public static ChecklistItemResponse fromEntity(ChecklistItem item) {
		return new ChecklistItemResponse(item.getId(),
				item.getJob().getId(),
				item.getTaskName(),
				item.isCompleted(),
				item.getCreatedAt());
	}

}
