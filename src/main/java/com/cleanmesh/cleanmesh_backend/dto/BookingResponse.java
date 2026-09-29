package com.cleanmesh.cleanmesh_backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.cleanmesh.cleanmesh_backend.entity.Booking;
import com.cleanmesh.cleanmesh_backend.entity.BookingStatus;

public record BookingResponse(
        Long id,
        Long customerId,
        String customerName,
        Long cleaningServiceId,
        String cleaningServiceName,
        LocalDate bookingDate,
        LocalTime bookingTime,
        String address,
        BookingStatus status,
        LocalDateTime createdAt) {

    public static BookingResponse fromEntity(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getCustomer().getId(),
                booking.getCustomer().getName(),
                booking.getCleaningService().getId(),
                booking.getCleaningService().getName(),
                booking.getBookingDate(),
                booking.getBookingTime(),
                booking.getAddress(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}