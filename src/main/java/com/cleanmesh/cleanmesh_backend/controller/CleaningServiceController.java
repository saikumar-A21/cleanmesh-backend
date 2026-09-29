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

import com.cleanmesh.cleanmesh_backend.dto.CleaningServiceRequest;
import com.cleanmesh.cleanmesh_backend.dto.CleaningServiceResponse;
import com.cleanmesh.cleanmesh_backend.service.ServiceCatalogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class CleaningServiceController {

    private final ServiceCatalogService serviceCatalogService;

    public CleaningServiceController(
            ServiceCatalogService serviceCatalogService) {

        this.serviceCatalogService = serviceCatalogService;
    }

    @PostMapping
    public ResponseEntity<CleaningServiceResponse> createService(
            @Valid @RequestBody CleaningServiceRequest request) {

        CleaningServiceResponse savedService =
                serviceCatalogService.createService(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedService);
    }

    @GetMapping
    public ResponseEntity<List<CleaningServiceResponse>> getAllServices() {

        return ResponseEntity.ok(
                serviceCatalogService.getAllServices()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CleaningServiceResponse> getServiceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                serviceCatalogService.getServiceById(id)
        );
    }
}