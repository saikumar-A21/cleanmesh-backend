package com.cleanmesh.cleanmesh_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cleanmesh.cleanmesh_backend.dto.CustomerRequest;
import com.cleanmesh.cleanmesh_backend.dto.CustomerResponse;
import com.cleanmesh.cleanmesh_backend.entity.Customer;
import com.cleanmesh.cleanmesh_backend.exception.ResourceNotFoundException;
import com.cleanmesh.cleanmesh_backend.repo.CustomerRepository;


@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerResponse createCustomer(CustomerRequest request) {

		Customer customer = new Customer(request.getName(), request.getEmail(), request.getPhone());

		Customer savedCustomer = customerRepository.save(customer);

		return toResponse(savedCustomer);
	}

	public List<CustomerResponse> getAllCustomers() {

		return customerRepository.findAll().stream().map(this::toResponse).toList();
	}

	public CustomerResponse getCustomerById(Long id) {

		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));

		return toResponse(customer);
	}

	private CustomerResponse toResponse(Customer customer) {

		return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
	}
}
