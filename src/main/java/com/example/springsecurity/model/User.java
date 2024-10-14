package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private boolean enabled;

    @Column(nullable=false)
    private String activeStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

}
