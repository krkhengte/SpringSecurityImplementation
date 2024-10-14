package com.example.springsecurity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdminRequest {

    private boolean roleGranted;
    private String requestId;
    private String username;
}
