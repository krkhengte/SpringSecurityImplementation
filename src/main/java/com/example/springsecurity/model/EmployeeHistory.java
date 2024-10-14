package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class EmployeeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hysId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @UpdateTimestamp
    private LocalDateTime updatedDateAndTime;

    private String updatedDetails;
}
