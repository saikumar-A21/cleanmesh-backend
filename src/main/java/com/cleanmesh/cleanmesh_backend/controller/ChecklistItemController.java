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

import com.cleanmesh.cleanmesh_backend.dto.ChecklistItemRequest;
import com.cleanmesh.cleanmesh_backend.dto.ChecklistItemResponse;
import com.cleanmesh.cleanmesh_backend.service.ChecklistItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/checklist-items")
public class ChecklistItemController {
	
	private final ChecklistItemService checklistItemService;

	public ChecklistItemController(ChecklistItemService checklistItemService) {
		
		this.checklistItemService = checklistItemService;
	}
	
	@PostMapping
	public ResponseEntity<ChecklistItemResponse> createChecklistItem(@Valid @RequestBody ChecklistItemRequest request) {
		
		
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(checklistItemService.createChecklistItem(request));
		
	}
	
	@GetMapping("/job/{jobId}")
	public ResponseEntity<List<ChecklistItemResponse>> getChecklistById(@PathVariable Long jobId) {
		return ResponseEntity.ok(checklistItemService.getChecklistById(jobId));
	}
	
	
	@PutMapping("/{itemId}/complete")
	public ResponseEntity<ChecklistItemResponse> completeChecklistItem(@PathVariable Long itemId){
		return ResponseEntity.ok(checklistItemService.completeChecklistItem(itemId));
	}
	
	
}
