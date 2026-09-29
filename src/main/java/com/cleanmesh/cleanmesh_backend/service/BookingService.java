package com.cleanmesh.cleanmesh_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cleanmesh.cleanmesh_backend.dto.BookingRequest;
import com.cleanmesh.cleanmesh_backend.dto.BookingResponse;
import com.cleanmesh.cleanmesh_backend.entity.Booking;
import com.cleanmesh.cleanmesh_backend.entity.BookingStatus;
import com.cleanmesh.cleanmesh_backend.entity.CleaningService;
import com.cleanmesh.cleanmesh_backend.entity.Customer;
import com.cleanmesh.cleanmesh_backend.exception.ResourceNotFoundException;
import com.cleanmesh.cleanmesh_backend.repo.BookingRepository;
import com.cleanmesh.cleanmesh_backend.repo.CleaningServiceRepository;
import com.cleanmesh.cleanmesh_backend.repo.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
	
	private final BookingRepository bookingRepository;

	private final CustomerRepository customerRepository;
	
	private final CleaningServiceRepository cleaningServiceRepository;

	public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository,
			CleaningServiceRepository cleaningServiceRepository) {
		
		this.bookingRepository = bookingRepository;
		this.customerRepository = customerRepository;
		this.cleaningServiceRepository = cleaningServiceRepository;
		
		
	}
	
	@Transactional
    public BookingResponse createBooking(BookingRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id: " + request.getCustomerId()));

        CleaningService cleaningService =
                cleaningServiceRepository.findById(request.getCleaningServiceId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cleaning service not found with id: "
                                                + request.getCleaningServiceId()));

        if (!cleaningService.isActive()) {
            throw new IllegalStateException(
                    "Cleaning service is currently inactive");
        }

        Booking booking = new Booking();

        booking.setCustomer(customer);
        booking.setCleaningService(cleaningService);
        booking.setBookingDate(request.getBookingDate());
        booking.setBookingTime(request.getBookingTime());
        booking.setAddress(request.getAddress());
        booking.setStatus(BookingStatus.REQUESTED);

        Booking savedBooking = bookingRepository.save(booking);

        return BookingResponse.fromEntity(savedBooking);
    }
	
	public List<BookingResponse> getAllBookings(){
		return bookingRepository.findAll()
				.stream()
				.map(BookingResponse::fromEntity)
				.toList();
	}
	
	public BookingResponse getBookingById(Long id) {
		
		Booking booking=bookingRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Booking not found with id"+id));
		return BookingResponse.fromEntity(booking);
	}
	
}
