package com.cleanmesh.cleanmesh_backend.dto;

import java.math.BigDecimal;

import com.cleanmesh.cleanmesh_backend.entity.CleaningService;

public record CleaningServiceResponse(
        Long id,
        String name,
        String description,
        BigDecimal basePrice,
        boolean active) {

    public static CleaningServiceResponse fromEntity(
            CleaningService service) {

        return new CleaningServiceResponse(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getBasePrice(),
                service.isActive()
        );
    }
}