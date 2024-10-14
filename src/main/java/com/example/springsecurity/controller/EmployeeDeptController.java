package com.example.springsecurity.controller;

import com.example.springsecurity.response.EmployeeDeptResponse;
import com.example.springsecurity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeDeptController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/user/getAllDepartment")
    public ResponseEntity<List<EmployeeDeptResponse>> getAllDepartmentEmployee(){
        List<EmployeeDeptResponse> allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.status(HttpStatus.OK).body(allDepartment);
    }

    @GetMapping("/user/getDeptByDeptName/{departName}")
    public ResponseEntity<List<EmployeeDeptResponse>> getAllDeptByDeptName(@PathVariable String departName){
        List<EmployeeDeptResponse> allEmployeeByDept = departmentService.getAllEmployeeByDept(departName);
        return ResponseEntity.status(HttpStatus.OK).body(allEmployeeByDept);
    }

}
