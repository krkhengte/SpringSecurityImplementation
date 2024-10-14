package com.example.springsecurity.response;

import com.example.springsecurity.model.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class EmployeeHistoryResponse {
    private Long hysId;
    private LocalDateTime updatedDateAndTime;
    private String updatedDetails;
    private Long id;
    private String name;
    private String email;
    private Long monum;
    private String city;
    private String state;
    private String country;
    private String isDeleted;
}
