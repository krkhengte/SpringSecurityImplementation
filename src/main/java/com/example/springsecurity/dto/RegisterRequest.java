package com.example.springsecurity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
public class RegisterRequest {
    public String username;
    public String password;
    private Set<String> roles;
}
