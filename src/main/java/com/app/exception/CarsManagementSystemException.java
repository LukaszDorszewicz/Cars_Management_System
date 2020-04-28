package com.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarsManagementSystemException extends RuntimeException {
    public CarsManagementSystemException(String message) {
        super(message);
    }
}
