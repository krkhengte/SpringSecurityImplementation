package com.example.springsecurity.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private Long id;

    public ResourceNotFoundException(String msg, Long id) {
        super(String.format("%s %d", msg, id));
        this.id = id;
    }

}
