package com.example.springsecurity.service.impl;

import com.example.springsecurity.dto.EmployeeRequest;
import com.example.springsecurity.exception.ResourceNotFoundException;
import com.example.springsecurity.model.Department;
import com.example.springsecurity.model.Employee;
import com.example.springsecurity.model.EmployeeHistory;
import com.example.springsecurity.repo.DepartmentRepo;
import com.example.springsecurity.repo.EmployeeHistoryRepo;
import com.example.springsecurity.repo.EmployeeRepo;
import com.example.springsecurity.response.EmployeeResponse;
import com.example.springsecurity.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceIml implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeHistoryRepo employeeHistoryRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    private String activeStatus="Active";

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        // Convert EmployeeRequest to Employee
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        // Create and set EmployeeHistory
        EmployeeHistory employeeHistory = new EmployeeHistory();
        employeeHistory.setUpdatedDateAndTime(LocalDateTime.now());
        employeeHistory.setUpdatedDetails("New Employee Added....");
        // Set history for employee
        employee.setHistory(List.of(employeeHistory));
        // Create Department and save it first
        Department department = new Department();
        department.setDepartmentName(employeeRequest.getDepartmentName());
        // Save the department to persist it before linking to the employee
        Department savedDepartment = departmentRepo.save(department);
        // Set the department for the employee
        employee.setDepartment(savedDepartment);
        employee.setIsDeleted(activeStatus);
        // Update history relationship for the saved employee
        employeeHistory.setEmployee(employee);
        // Save the employee
        Employee savedEmployee = employeeRepo.save(employee);
        // Create and populate EmployeeResponse
        EmployeeResponse employeeResponse = modelMapper.map(savedEmployee, EmployeeResponse.class);
        employeeResponse.setIsDeleted(activeStatus);

        return employeeResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is Not found with emp id :" ,id));
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is Not found with emp id :", id));
        employee.setIsDeleted("In-Active");
        EmployeeHistory employeeHistory=new EmployeeHistory();
        employeeHistory.setEmployee(employee);
        employeeHistory.setUpdatedDateAndTime(LocalDateTime.now());
        employeeHistory.setUpdatedDetails("Employee Deleted..");
        employeeHistoryRepo.save(employeeHistory);
        return "Employee Deleted..";
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> all = employeeRepo.findAll();
        List<EmployeeResponse> employeeResponses=new ArrayList<>();
        for(Employee e:all){
            EmployeeResponse employeeResponse = modelMapper.map(e, EmployeeResponse.class);
            employeeResponses.add(employeeResponse);
        }
        System.out.println(employeeResponses.size());
        return employeeResponses;
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) {
        Employee employee1 = employeeRepo.findById(employeeRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Employee is not with this Emp_Id:", employeeRequest.getId()));
        if(employee1!=null){
            Employee employee = modelMapper.map(employeeRequest, Employee.class);
            EmployeeHistory employeeHistory=new EmployeeHistory();
            employeeHistory.setEmployee(employee);
            employeeHistory.setUpdatedDateAndTime(LocalDateTime.now());
            employeeHistory.setUpdatedDetails("Updated Existing Employee....");
            employee.setHistory(List.of(employeeHistory));
            employee.setIsDeleted(activeStatus);
            Employee save = employeeRepo.save(employee);
            EmployeeResponse employeeResponse1 = modelMapper.map(employeeRequest, EmployeeResponse.class);
            employeeResponse1.setIsDeleted(activeStatus);
            return employeeResponse1;
        }else{
            return new EmployeeResponse();
        }
    }
}
