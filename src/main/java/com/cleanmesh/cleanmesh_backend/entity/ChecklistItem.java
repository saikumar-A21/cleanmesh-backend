package com.cleanmesh.cleanmesh_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="checklist_items")
@Getter
@Setter
@NoArgsConstructor
public class ChecklistItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="job_id", nullable =false)
	private Job job;
	
	private String taskName;
	
	private boolean completed=false;
	
	private LocalDateTime createdAt;
	
	public ChecklistItem(Job job, String taskName) {
        this.job = job;
        this.taskName = taskName;
        this.completed = false;
    }
}
