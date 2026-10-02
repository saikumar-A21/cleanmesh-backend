package com.cleanmesh.cleanmesh_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cleanmesh.cleanmesh_backend.dto.JobRequest;
import com.cleanmesh.cleanmesh_backend.dto.JobResponse;
import com.cleanmesh.cleanmesh_backend.entity.Booking;
import com.cleanmesh.cleanmesh_backend.entity.ChecklistItem;
import com.cleanmesh.cleanmesh_backend.entity.Job;
import com.cleanmesh.cleanmesh_backend.entity.JobStatus;
import com.cleanmesh.cleanmesh_backend.entity.Worker;
import com.cleanmesh.cleanmesh_backend.entity.WorkerStatus;
import com.cleanmesh.cleanmesh_backend.exception.ResourceNotFoundException;
import com.cleanmesh.cleanmesh_backend.repo.BookingRepository;
import com.cleanmesh.cleanmesh_backend.repo.ChecklistRepository;
import com.cleanmesh.cleanmesh_backend.repo.JobRepository;
import com.cleanmesh.cleanmesh_backend.repo.WorkerRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final BookingRepository bookingRepository;
    private final WorkerRepository workerRepository;
    private final ChecklistRepository checklistRepository;

    public JobService(
            JobRepository jobRepository,
            BookingRepository bookingRepository,WorkerRepository workerRepository,ChecklistRepository checklistRepository) {

        this.jobRepository = jobRepository;
        this.bookingRepository = bookingRepository;
        this.workerRepository=workerRepository;
        this.checklistRepository=checklistRepository;
    }

    @Transactional
    public JobResponse createJob(JobRequest request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with id: "
                                        + request.getBookingId()
                        ));

        Job job = new Job();

        job.setBooking(booking);
        job.setStatus(JobStatus.CREATED);

        Job savedJob = jobRepository.save(job);

        return JobResponse.fromEntity(savedJob);
    }

    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(JobResponse::fromEntity)
                .toList();
    }

    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + id
                        ));

        return JobResponse.fromEntity(job);
    }
    
    @Transactional
    public JobResponse assignWorker(Long jobId,Long workerId) {
    	Job job=jobRepository.findById(jobId)
    			 .orElseThrow(() ->
                 new ResourceNotFoundException(
                         "Job not found with id: " + jobId
                 ));
    	Worker worker=workerRepository.findById(workerId)
    			 .orElseThrow(() ->
                 new ResourceNotFoundException(
                         "Worker not found with id: " + workerId
                 ));
    	
    	if(job.getStatus()!=JobStatus.CREATED) {
    		throw new IllegalStateException(
                    "Worker can only be assigned to a CREATED job"
            );
    		
    	}
    	
    	if(worker.getStatus()!=WorkerStatus.AVAILABLE) {
    		throw new IllegalStateException(
                    "Worker is not available for Assignment"
            );
    	}
    	
    	job.setWorker(worker);
    	job.setStatus(JobStatus.ASSIGNED);
    	
    	worker.setStatus(WorkerStatus.BUSY);
    	
    		jobRepository.save(job);
    		workerRepository.save(worker);
    		
    		return JobResponse.fromEntity(job);
    }
    
    @Transactional
    public JobResponse startJob(Long jobId) {
    	
    	Job job= jobRepository.findById(jobId)
    			.orElseThrow(()-> new ResourceNotFoundException("Job not found with id "+jobId));
    	
    	if(job.getStatus()!=JobStatus.ASSIGNED) {
    		throw new IllegalStateException("Only Assigned Job can be started");
    	}
    	
    	if(job.getStatus()==null) {
    		throw new IllegalStateException("Job does not have an assigned worker");
    	}
    	
    	job.setStatus(JobStatus.IN_PROGRESS);
    	
    	Job savedjob=jobRepository.save(job);
    	
    	return JobResponse.fromEntity(savedjob);
    }
    
    @Transactional
    public JobResponse completeJob(Long jobId) {
    	Job job= jobRepository.findById(jobId)
    			.orElseThrow(()->new ResourceNotFoundException("Job not Found with id "+jobId));
    	
    	 if (job.getStatus() == JobStatus.COMPLETED) {
    	        return JobResponse.fromEntity(job);
    	    }
    	 
    	if(job.getStatus()!=JobStatus.IN_PROGRESS) {
    		 throw new IllegalStateException(
    	                "Only an IN_PROGRESS job can be completed"
    	        );
    	}
    	
    	if(job.getWorker()==null) {
    		 throw new IllegalStateException(
    	                "Job does not have an assigned worker"
    	        );
    	}
    	List<ChecklistItem> checklistItems= checklistRepository.findByJobId(jobId);
    	
    	if(checklistItems.isEmpty()) {
    		throw new IllegalStateException("Job cannot be completed withoutchecklist items");
    	}
    	
    	boolean allTasksCompleted=checklistItems
    			.stream()
    			.allMatch(ChecklistItem::isCompleted);
    	if(!allTasksCompleted) {
    		throw new IllegalStateException("Job cannot be completed because some checklist items are still pending");
    	}
    			
    	Worker worker=job.getWorker();
    	job.setStatus(JobStatus.COMPLETED);
    	worker.setStatus(WorkerStatus.AVAILABLE);
    	
    	jobRepository.save(job);
    	workerRepository.save(worker);
    	
    	
    	
    	
    	return JobResponse.fromEntity(job);
    }
    
}