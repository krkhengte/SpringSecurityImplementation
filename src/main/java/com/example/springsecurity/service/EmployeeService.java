package com.example.springsecurity.service;

import com.example.springsecurity.dto.EmployeeRequest;
import com.example.springsecurity.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    public EmployeeResponse saveEmployee(EmployeeRequest employee);
    public EmployeeResponse getEmployeeById(Long id);
    public List<EmployeeResponse> getAllEmployee();
    public EmployeeResponse updateEmployee(EmployeeRequest employee);
    public String deleteEmployeeById(Long id);
}
