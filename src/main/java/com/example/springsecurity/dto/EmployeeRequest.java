package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class EmployeeRequest {
    private Long id;
    private String name;
    private String email;
    private Long monum;
    private String city;
    private String state;
    private String country;
    private String departmentName;
}
