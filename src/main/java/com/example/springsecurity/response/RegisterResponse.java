package com.example.springsecurity.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterResponse {
    private String msg;
    private String requestId;
    private String username;
}
