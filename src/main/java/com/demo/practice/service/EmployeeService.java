package com.demo.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.practice.entity.Employee;
import com.demo.practice.exceptions.EmployeeAlreadyExistsException;
import com.demo.practice.exceptions.EmployeeNotFoundException;
import com.demo.practice.exceptions.InvalidEmployeeDataException;
import com.demo.practice.repo.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty() || employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new InvalidEmployeeDataException("Invalid employee data provided.");
        }
        
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployee.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee with email " + employee.getEmail() + " already exists.");
        }
        
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployee(id); // Will throw EmployeeNotFoundException if not found
        
        if (employee.getName() == null || employee.getName().isEmpty() || employee.getEmail() == null || employee.getEmail().isEmpty()) {
            throw new InvalidEmployeeDataException("Invalid employee data provided.");
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        
        return employeeRepository.save(existingEmployee);
    }
}


