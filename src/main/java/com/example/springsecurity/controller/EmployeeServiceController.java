package com.example.springsecurity.controller;

import com.example.springsecurity.dto.EmployeeRequest;
import com.example.springsecurity.response.EmployeeResponse;
import com.example.springsecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeServiceController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/saveEmployee")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest employee){
        EmployeeResponse employeeResponse = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/GetEmployeeById/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id){
        EmployeeResponse employeeById = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeById);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/getAllEmployee")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee(){
        List<EmployeeResponse> allEmployee = employeeService.getAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployee);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/updateEmployee")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest employee){
        EmployeeResponse employeeResponse = employeeService.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/deleteEMployeeByID/{id}")
    public ResponseEntity<String> deleteEMployeeByID(@PathVariable Long id){
        String deletedEmployeeById = employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedEmployeeById);
    }
}
