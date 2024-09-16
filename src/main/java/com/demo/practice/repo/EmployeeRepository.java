package com.demo.practice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.practice.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
