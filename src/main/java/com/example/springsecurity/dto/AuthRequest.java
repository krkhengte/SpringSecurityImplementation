package com.example.springsecurity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthRequest {
    public String username;
    public String password;
}
