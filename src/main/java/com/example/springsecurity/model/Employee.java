package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long monum;
    private String city;
    private String state;
    private String country;
    private String isDeleted;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<EmployeeHistory> history = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
