package com.example.springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg, false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
 class ApiResponse {

    private String message;
    private Boolean success;

}