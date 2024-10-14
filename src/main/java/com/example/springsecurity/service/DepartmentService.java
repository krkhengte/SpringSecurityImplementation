package com.example.springsecurity.service;

import com.example.springsecurity.response.EmployeeDeptResponse;

import java.util.List;

public interface DepartmentService {

    public List<EmployeeDeptResponse> getAllDepartment();

    public List<EmployeeDeptResponse> getAllEmployeeByDept(String departNmae);

}
