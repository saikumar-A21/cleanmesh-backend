package com.cleanmesh.cleanmesh_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanmesh.cleanmesh_backend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
