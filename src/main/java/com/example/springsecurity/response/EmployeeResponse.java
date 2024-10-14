package com.example.springsecurity.response;

import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private Long monum;
    private String city;
    private String state;
    private String country;
    private String isDeleted;
    private String departmentName;
}
