package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.Department;
import com.example.springsecurity.model.Employee;
import com.example.springsecurity.repo.DepartmentRepo;
import com.example.springsecurity.response.EmployeeDeptResponse;
import com.example.springsecurity.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDeptServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDeptResponse> getAllDepartment() {
        List<Department> all = departmentRepo.findAll();
        List<EmployeeDeptResponse> employeeDeptResponses=new ArrayList<>();

        List<Object> collect = all.stream().map((x) -> {
            List<Employee> employee = x.getEmployee();
            for (Employee employee1 : employee) {
                EmployeeDeptResponse employeeDeptResponse = modelMapper.map(employee1, EmployeeDeptResponse.class);
                employeeDeptResponse.setDepartmentName(x.getDepartmentName());
                employeeDeptResponse.setId(x.getId());
                employeeDeptResponses.add(employeeDeptResponse);
            }
            return null;
        }).collect(Collectors.toList());

        return employeeDeptResponses;
    }

    @Override
    public List<EmployeeDeptResponse> getAllEmployeeByDept(String departName) {
        List<Department> departByDeptName = departmentRepo.getDepartByDeptName(departName);
        List<EmployeeDeptResponse> employeeDeptResponses=new ArrayList<>();
        List<Object> collect = departByDeptName.stream().map((x) -> {
            List<Employee> employee = x.getEmployee();
            for (Employee employee1 : employee) {
                EmployeeDeptResponse employeeDeptResponse = modelMapper.map(employee1, EmployeeDeptResponse.class);
                employeeDeptResponse.setDepartmentName(x.getDepartmentName());
                employeeDeptResponse.setId(x.getId());
                employeeDeptResponses.add(employeeDeptResponse);
            }
            return null;
        }).collect(Collectors.toList());
        return employeeDeptResponses;
    }
}
