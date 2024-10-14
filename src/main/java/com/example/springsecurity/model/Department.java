package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Employee> employee;

}
