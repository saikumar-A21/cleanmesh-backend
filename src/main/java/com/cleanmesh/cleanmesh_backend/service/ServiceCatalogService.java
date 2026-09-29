package com.cleanmesh.cleanmesh_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cleanmesh.cleanmesh_backend.dto.CleaningServiceRequest;
import com.cleanmesh.cleanmesh_backend.dto.CleaningServiceResponse;
import com.cleanmesh.cleanmesh_backend.entity.CleaningService;
import com.cleanmesh.cleanmesh_backend.exception.ResourceNotFoundException;
import com.cleanmesh.cleanmesh_backend.repo.CleaningServiceRepository;

@Service
public class ServiceCatalogService {

    private final CleaningServiceRepository cleaningServiceRepository;

    public ServiceCatalogService(
            CleaningServiceRepository cleaningServiceRepository) {

        this.cleaningServiceRepository = cleaningServiceRepository;
    }

    public CleaningServiceResponse createService(
            CleaningServiceRequest request) {

        CleaningService cleaningService = new CleaningService(
                request.getName(),
                request.getDescription(),
                request.getBasePrice(),
                request.getActive()
        );

        CleaningService savedService =
                cleaningServiceRepository.save(cleaningService);

        return CleaningServiceResponse.fromEntity(savedService);
    }

    public List<CleaningServiceResponse> getAllServices() {

        return cleaningServiceRepository.findAll()
                .stream()
                .map(CleaningServiceResponse::fromEntity)
                .toList();
    }

    public CleaningServiceResponse getServiceById(Long id) {

        CleaningService service =
                cleaningServiceRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cleaning service not found with id: " + id
                                ));

        return CleaningServiceResponse.fromEntity(service);
    }
}